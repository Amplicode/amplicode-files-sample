package com.example.springcontentsample2.entity;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

public class JpaFileRef {
    @ContentId
    private String contentId;

    @ContentLength
    private Long contentLength = 0L;

    private String contentMimeType;
    private String contentOriginalFileName;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentMimeType() {
        return contentMimeType;
    }

    public void setContentMimeType(String contentMimeType) {
        this.contentMimeType = contentMimeType;
    }

    public String getContentOriginalFileName() {
        return contentOriginalFileName;
    }

    public void setContentOriginalFileName(String contentOriginalFileName) {
        this.contentOriginalFileName = contentOriginalFileName;
    }


    @Override
    public String toString() {
        return "JpaFileRef{" +
                "contentId='" + contentId + '\'' +
                ", contentLength=" + contentLength +
                ", contentMimeType='" + contentMimeType + '\'' +
                ", contentOriginalFileName='" + contentOriginalFileName + '\'' +
                '}';
    }
}
