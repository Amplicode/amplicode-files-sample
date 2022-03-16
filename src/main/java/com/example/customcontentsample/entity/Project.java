package com.example.customcontentsample.entity;

import com.example.customcontentsample.converter.LocalFileRefConverter;
import com.example.customcontentsample.converter.LocalFileRefListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon", length = 500)
    @Convert(converter = LocalFileRefConverter.class)
    private LocalFileRef icon;

    @Lob
    @Column(name = "attachments")
    @Convert(converter = LocalFileRefListConverter.class)
    private List<LocalFileRef> attachments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalFileRef getIcon() {
        return icon;
    }

    public void setIcon(LocalFileRef icon) {
        this.icon = icon;
    }

    public List<LocalFileRef> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<LocalFileRef> attachments) {
        this.attachments = attachments;
    }
}