package com.example.springcontentsample3.store;

import com.example.springcontentsample3.entity.FileRef;
import org.springframework.content.jpa.store.JpaContentStore;
import org.springframework.stereotype.Component;

@Component
public interface FileRefJpaStore extends JpaContentStore<FileRef, String> {
}
