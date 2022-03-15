package com.example.springcontentsample2.rest;

import com.example.springcontentsample2.entity.JpaFileRef;
import com.example.springcontentsample2.store.JpaFileRefStore;
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
    private JpaFileRefStore jpaFileRefStore;

    @PostMapping("/upload")
    public JpaFileRef uploadJpaFileRef(@RequestParam MultipartFile file) {
        JpaFileRef jpaFileRef = new JpaFileRef();
        jpaFileRef.setContentMimeType(file.getContentType());
        jpaFileRef.setContentOriginalFileName(file.getOriginalFilename());
        return jpaFileRefStore.setContent(jpaFileRef, file.getResource());
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadJpaFileRef(@RequestBody JpaFileRef jpaFileRef) {
        return ResponseEntity.ok()
                .contentLength(jpaFileRef.getContentLength())
                .contentType(MediaType.parseMediaType(jpaFileRef.getContentMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + jpaFileRef.getContentOriginalFileName() + "\"")
                .body(jpaFileRefStore.getResource(jpaFileRef));
    }
}
