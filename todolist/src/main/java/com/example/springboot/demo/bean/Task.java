package com.example.springboot.demo.bean;

public class Task {
    private int id;
    private String content;
    private String createdTime;
    

    public Task(){

    }
    public Task(int id, String content, String createdTime){
        this.id = id;
        this.content = content;
        this.createdTime=createdTime;
    }
    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String createdTime() {
        return createdTime;
    }

    public void setContent(String name) {
        this.content = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getcreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
