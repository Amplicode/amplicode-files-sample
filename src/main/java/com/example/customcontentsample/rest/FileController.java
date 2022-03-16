package com.example.customcontentsample.rest;

import com.example.customcontentsample.storage.LocalFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @Autowired
    private LocalFileStorage localFileStorage;

    @GetMapping("/api/files")
    public ResponseEntity<?> download(@RequestParam String path) {
        return ResponseEntity.ok()
                // .contentLength(photo.getSize())
                // .contentType(MediaType.parseMediaType(photo.getMimeType()))
                // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getOriginalFileName() + "\"")
                .body(localFileStorage.getResource(path));
    }
}
