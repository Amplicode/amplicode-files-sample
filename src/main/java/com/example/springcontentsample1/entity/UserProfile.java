package com.example.springcontentsample1.entity;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;
import org.springframework.content.commons.annotations.OriginalFileName;

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

    @ContentId
    @Column(name = "photo_id")
    private String photoId;

    @ContentLength
    @Column(name = "photo_length")
    private Long photoLength;

    @MimeType
    @Column(name = "photo_mime_type")
    private String photoMimeType;

    @OriginalFileName
    @Column(name = "photo_original_file_name")
    private String photoOriginalFileName;


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

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Long getPhotoLength() {
        return photoLength;
    }

    public void setPhotoLength(Long photoLength) {
        this.photoLength = photoLength;
    }

    public String getPhotoMimeType() {
        return photoMimeType;
    }

    public void setPhotoMimeType(String photoMimeType) {
        this.photoMimeType = photoMimeType;
    }

    public String getPhotoOriginalFileName() {
        return photoOriginalFileName;
    }

    public void setPhotoOriginalFileName(String photoOriginalFileName) {
        this.photoOriginalFileName = photoOriginalFileName;
    }
}