package com.example.springcontentsample3.rest;

import com.example.springcontentsample3.entity.FileRef;
import com.example.springcontentsample3.store.FileRefFsStore;
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
    private FileRefFsStore fileRefFsStore;

    @PostMapping("/upload")
    public FileRef uploadFileRef(@RequestParam MultipartFile file) {
        FileRef fsFileRef = new FileRef();
        fsFileRef.setContentMimeType(file.getContentType());
        fsFileRef.setContentOriginalFileName(file.getOriginalFilename());
        return fileRefFsStore.setContent(fsFileRef, file.getResource());
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFileRef(@RequestBody FileRef fsFileRef) {
        return ResponseEntity.ok()
                .contentLength(fsFileRef.getContentLength())
                .contentType(MediaType.parseMediaType(fsFileRef.getContentMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fsFileRef.getContentOriginalFileName() + "\"")
                .body(fileRefFsStore.getResource(fsFileRef));
    }
}
