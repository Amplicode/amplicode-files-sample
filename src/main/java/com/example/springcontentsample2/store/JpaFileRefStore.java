package com.example.springcontentsample2.store;

import com.example.springcontentsample2.entity.JpaFileRef;
import org.springframework.content.jpa.store.JpaContentStore;
import org.springframework.stereotype.Component;

@Component
public interface JpaFileRefStore extends JpaContentStore<JpaFileRef, String> {
}
