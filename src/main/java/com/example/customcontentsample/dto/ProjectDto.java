package com.example.customcontentsample.dto;

import com.example.customcontentsample.entity.LocalFileRef;

import java.io.Serializable;
import java.util.List;

public class ProjectDto implements Serializable {
    private final Long id;
    private final String name;
    private List<LocalFileRef> attachments;

    public ProjectDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<LocalFileRef> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<LocalFileRef> attachments) {
        this.attachments = attachments;
    }
}
