package com.example.springcontentsample2.rest;

import com.example.springcontentsample2.entity.FsFileRef;
import com.example.springcontentsample2.store.FsFileRefStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fs")
public class FsFileRefController {

    @Autowired
    private FsFileRefStore fsFileRefStore;

    @PostMapping("/upload")
    public FsFileRef uploadFsFileRef(@RequestParam MultipartFile file) {
        FsFileRef fsFileRef = new FsFileRef();
        fsFileRef.setContentMimeType(file.getContentType());
        fsFileRef.setContentOriginalFileName(file.getOriginalFilename());
        return fsFileRefStore.setContent(fsFileRef, file.getResource());
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFsFileRef(@RequestBody FsFileRef fsFileRef) {
        return ResponseEntity.ok()
                .contentLength(fsFileRef.getContentLength())
                .contentType(MediaType.parseMediaType(fsFileRef.getContentMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fsFileRef.getContentOriginalFileName() + "\"")
                .body(fsFileRefStore.getResource(fsFileRef));
    }
}
