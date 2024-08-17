package com.example.postsorm.model;

import org.springframework.web.multipart.MultipartFile;


public class PostForm {
    private int id;

    private String title;
    private String content;
    private String description;
    private MultipartFile image;

    public PostForm() {
    }

    public PostForm(int id, String title, String content, String description, MultipartFile image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
