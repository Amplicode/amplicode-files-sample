package com.example.springcontentsample3.rest;

import com.example.springcontentsample3.entity.FileRef;
import com.example.springcontentsample3.store.FileRefJpaStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/jpa")
public class JpaFileRefController {

    @Autowired
    private FileRefJpaStore fileRefJpaStore;

    @PostMapping("/upload")
    public FileRef uploadFileRef(@RequestParam MultipartFile file) {
        FileRef jpaFileRef = new FileRef();
        jpaFileRef.setContentMimeType(file.getContentType());
        jpaFileRef.setContentOriginalFileName(file.getOriginalFilename());
        return fileRefJpaStore.setContent(jpaFileRef, file.getResource());
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFileRef(@RequestBody FileRef jpaFileRef) {
        return ResponseEntity.ok()
                .contentLength(jpaFileRef.getContentLength())
                .contentType(MediaType.parseMediaType(jpaFileRef.getContentMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + jpaFileRef.getContentOriginalFileName() + "\"")
                .body(fileRefJpaStore.getResource(jpaFileRef));
    }
}
