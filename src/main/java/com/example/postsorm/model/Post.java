package com.example.postsorm.model;


import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private String description;
    private String image;

    public Post() {}
    public Post(String title, String content, String description, String image) {
        this.title = title;
        this.content = content;
        this.description = description;
        this.image = image;
    }

    public Post(int id, String title, String content, String description, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
