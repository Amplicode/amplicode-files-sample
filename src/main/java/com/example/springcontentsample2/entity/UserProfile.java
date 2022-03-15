package com.example.springcontentsample2.entity;


import com.example.springcontentsample2.converter.FsFileRefConverter;
import com.example.springcontentsample2.converter.JpaFileRefConverter;

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
    @Convert(converter = FsFileRefConverter.class)
    private FsFileRef photo;

    @Convert(converter = JpaFileRefConverter.class)
    @Column(name = "document_ref", length = 500)
    private JpaFileRef document;

    public JpaFileRef getDocument() {
        return document;
    }

    public void setDocument(JpaFileRef signTemplate) {
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

    public FsFileRef getPhoto() {
        return photo;
    }

    public void setPhoto(FsFileRef photo) {
        this.photo = photo;
    }


}