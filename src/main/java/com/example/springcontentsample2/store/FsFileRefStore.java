package com.example.springcontentsample2.store;

import com.example.springcontentsample2.entity.FsFileRef;
import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.stereotype.Component;

@Component
public interface FsFileRefStore extends FilesystemContentStore<FsFileRef, String> {
}
