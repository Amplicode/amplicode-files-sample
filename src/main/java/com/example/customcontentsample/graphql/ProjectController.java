package com.example.customcontentsample.graphql;

import com.example.customcontentsample.dto.ProjectDto;
import com.example.customcontentsample.entity.Project;
import com.example.customcontentsample.mapper.ProjectMapper;
import com.example.customcontentsample.repository.ProjectRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProjectController {
    private final ProjectRepository crudRepository;
    private final ProjectMapper mapper;

    public ProjectController(ProjectRepository crudRepository, ProjectMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @MutationMapping(name = "deleteProject")
    @Transactional
    public void delete(@Argument Long id) {
        Project entity = crudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }

    @QueryMapping(name = "listProjects")
    @Transactional
    public List<ProjectDto> findAll() {
        return crudRepository.findAll().stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @QueryMapping(name = "findProject")
    @Transactional
    public ProjectDto findById(@Argument Long id) {
        return crudRepository.findById(id)
                .map(mapper::projectToProjectDto)
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find entity by id: %s ", id)));
    }

    @MutationMapping(name = "updateProject")
    @Transactional
    public ProjectDto update(@Argument ProjectDto input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new RuntimeException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }
        Project entity = new Project();
        mapper.updateProjectFromProjectDto(input, entity);
        entity = crudRepository.save(entity);
        return mapper.projectToProjectDto(entity);
    }

    @QueryMapping(name = "countProjects")
    @Transactional
    public long count() {
        return crudRepository.count();
    }
}