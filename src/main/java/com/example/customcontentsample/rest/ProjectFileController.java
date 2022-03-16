package com.example.customcontentsample.rest;

import com.example.customcontentsample.dto.ProjectDto;
import com.example.customcontentsample.entity.LocalFileRef;
import com.example.customcontentsample.entity.Project;
import com.example.customcontentsample.mapper.ProjectMapper;
import com.example.customcontentsample.repository.ProjectRepository;
import com.example.customcontentsample.storage.LocalFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects") // ?
public class ProjectFileController {
    @Autowired
    private LocalFileStorage localFileStorage;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @PostMapping("/{projectId}/icon") //?
    public void upload(@PathVariable Long projectId, @RequestParam String filename, HttpServletRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Unable to find entity by id: " + projectId));
        try {
            LocalFileRef iconRef = localFileStorage.saveStream(new LocalFileRef(filename, request.getContentLengthLong(), request.getContentType()), request.getInputStream());
            project.setIcon(iconRef);
            projectRepository.save(project);
        } catch (IOException e) {
            throw new RuntimeException("I/O exception");
        }
    }

    @GetMapping("/{projectId}/icon")
    public ResponseEntity<?> download(@PathVariable Long projectId) { //possible parameters: size
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Unable to find entity by id: " + projectId));
        LocalFileRef icon = project.getIcon();
        if (icon != null) {
            return ResponseEntity.ok()
                    .contentLength(icon.getSize())
                    .contentType(MediaType.parseMediaType(icon.getMimeType()))
                    .body(localFileStorage.getResource(icon.getPath()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{projectId}/attachments") //?
    public ProjectDto uploadAttachments(@PathVariable Long projectId, MultipartFile[] files) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Unable to find entity by id: " + projectId));
        List<LocalFileRef> attachments = Arrays.stream(files)
                .map(file -> {
                    try {
                        return localFileStorage.saveStream(new LocalFileRef(file.getOriginalFilename(), file.getSize(), file.getContentType()), new ByteArrayInputStream(file.getBytes()));
                    } catch (IOException e) {
                        throw new RuntimeException("I/O exception");
                    }

                }).collect(Collectors.toList());
        project.setAttachments(attachments);
        return projectMapper.projectToProjectDto(projectRepository.save(project));
    }
}
