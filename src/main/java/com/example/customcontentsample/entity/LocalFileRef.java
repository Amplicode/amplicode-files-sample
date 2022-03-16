package com.example.customcontentsample.entity;

public class LocalFileRef {
    private String path;
    private String originalFileName;
    private Long size = 0L;
    private String mimeType;

    public LocalFileRef() {
    }

    public LocalFileRef(String originalFileName, Long size, String mimeType) {
        this.originalFileName = originalFileName;
        this.size = size;
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

}
