package com.example.springcontentsample3.store;

import com.example.springcontentsample3.entity.FileRef;
import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.stereotype.Component;

@Component
public interface FileRefFsStore extends FilesystemContentStore<FileRef, String> {
}
