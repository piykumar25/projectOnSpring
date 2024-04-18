package com.coder.gitViewer.model;

public class Commit {
    private String id;
    private String message;
    private String author;

    public Commit(String number, String initialCommit, String johnDoe) {
    }

    // getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}