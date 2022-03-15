package com.example.springcontentsample3.entity;


import com.example.springcontentsample3.converter.FileRefConverter;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "photo_ref", length = 500)
    @Convert(converter = FileRefConverter.class)
    private FileRef photo;

    @Convert(converter = FileRefConverter.class)
    @Column(name = "document_ref", length = 500)
    private FileRef document;

    public FileRef getDocument() {
        return document;
    }

    public void setDocument(FileRef signTemplate) {
        this.document = signTemplate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public FileRef getPhoto() {
        return photo;
    }

    public void setPhoto(FileRef photo) {
        this.photo = photo;
    }


}