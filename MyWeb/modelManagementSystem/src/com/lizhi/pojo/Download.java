package com.lizhi.pojo;

import java.util.Date;

public class Download {
    private Integer id;
    private Integer modelId;
    private String modelName; // not sql
    private String modelType; // not sql
    private Date releaseDate;//not sql
    private Integer userId;
    private String userName; // not sql
    private Integer downloads;
    private Integer score;


    @Override
    public String toString() {
        return "Download{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", downloads=" + downloads +
                ", score=" + score +
                '}';
    }

    public Download(Integer id, Integer modelId, Integer userId, Integer downloads, Integer score) {
        this.id = id;
        this.modelId = modelId;
        this.userId = userId;
        this.downloads = downloads;
        this.score = score;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public Download() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }
}
