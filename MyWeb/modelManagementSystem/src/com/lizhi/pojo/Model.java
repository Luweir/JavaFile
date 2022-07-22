package com.lizhi.pojo;

import java.util.Date;

public class Model {
    private Integer id;
    private String modelName;
    private String modelType;
    private String modelDescription;
    private String version;
    private String modelDirectoryCode;
    private String runtimeEnvironment;
    private String modelTags;
    private String developmentLanguage;
    private String applicationField;
    private String modelFileName;
    private String modelFilePath;
    private String createDepartment;
    private String createDepartmentCode;
    private String completionDepartment;
    private Date completionDate;
    private String testDescription;
    private String attachmentPath;
    private String attachmentDescription;
    private String modelState; // 未发布、已发布、拒绝发布、已弃用
    private Integer downloads;
    private Integer score;
    private Date registerDate;
    private Date releaseDate;
    private Integer registerUserId; // 注册人ID
    private String registerName; // no sql

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", modelType='" + modelType + '\'' +
                ", modelDescription='" + modelDescription + '\'' +
                ", version='" + version + '\'' +
                ", modelDirectoryCode='" + modelDirectoryCode + '\'' +
                ", runtimeEnvironment='" + runtimeEnvironment + '\'' +
                ", modelTags='" + modelTags + '\'' +
                ", developmentLanguage='" + developmentLanguage + '\'' +
                ", applicationField='" + applicationField + '\'' +
                ", modelFileName='" + modelFileName + '\'' +
                ", modelFilePath='" + modelFilePath + '\'' +
                ", createDepartment='" + createDepartment + '\'' +
                ", createDepartmentCode='" + createDepartmentCode + '\'' +
                ", completionDepartment='" + completionDepartment + '\'' +
                ", completionDate=" + completionDate +
                ", testDescription='" + testDescription + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", attachmentDescription='" + attachmentDescription + '\'' +
                ", modelState='" + modelState + '\'' +
                ", downloads=" + downloads +
                ", score=" + score +
                ", registerDate=" + registerDate +
                ", releaseDate=" + releaseDate +
                ", registerUserId=" + registerUserId +
                '}';
    }

    public Model(Integer id, String modelName, String modelType, String modelDescription, String version, String modelDirectoryCode, String runtimeEnvironment, String modelTags, String developmentLanguage, String applicationField, String modelFileName, String modelFilePath, String createDepartment, String createDepartmentCode, String completionDepartment, Date completionDate, String testDescription, String attachmentPath, String attachmentDescription, String modelState, Integer downloads, Integer score, Date registerDate, Date releaseDate, Integer registerUserId) {
        this.id = id;
        this.modelName = modelName;
        this.modelType = modelType;
        this.modelDescription = modelDescription;
        this.version = version;
        this.modelDirectoryCode = modelDirectoryCode;
        this.runtimeEnvironment = runtimeEnvironment;
        this.modelTags = modelTags;
        this.developmentLanguage = developmentLanguage;
        this.applicationField = applicationField;
        this.modelFileName = modelFileName;
        this.modelFilePath = modelFilePath;
        this.createDepartment = createDepartment;
        this.createDepartmentCode = createDepartmentCode;
        this.completionDepartment = completionDepartment;
        this.completionDate = completionDate;
        this.testDescription = testDescription;
        this.attachmentPath = attachmentPath;
        this.attachmentDescription = attachmentDescription;
        this.modelState = modelState;
        this.downloads = downloads;
        this.score = score;
        this.registerDate = registerDate;
        this.releaseDate = releaseDate;
        this.registerUserId = registerUserId;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public Model() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModelDirectoryCode() {
        return modelDirectoryCode;
    }

    public void setModelDirectoryCode(String modelDirectoryCode) {
        this.modelDirectoryCode = modelDirectoryCode;
    }

    public String getRuntimeEnvironment() {
        return runtimeEnvironment;
    }

    public void setRuntimeEnvironment(String runtimeEnvironment) {
        this.runtimeEnvironment = runtimeEnvironment;
    }

    public String getModelTags() {
        return modelTags;
    }

    public void setModelTags(String modelTags) {
        this.modelTags = modelTags;
    }

    public String getDevelopmentLanguage() {
        return developmentLanguage;
    }

    public void setDevelopmentLanguage(String developmentLanguage) {
        this.developmentLanguage = developmentLanguage;
    }

    public String getApplicationField() {
        return applicationField;
    }

    public void setApplicationField(String applicationField) {
        this.applicationField = applicationField;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public String getCreateDepartment() {
        return createDepartment;
    }

    public void setCreateDepartment(String createDepartment) {
        this.createDepartment = createDepartment;
    }

    public String getCreateDepartmentCode() {
        return createDepartmentCode;
    }

    public void setCreateDepartmentCode(String createDepartmentCode) {
        this.createDepartmentCode = createDepartmentCode;
    }

    public String getCompletionDepartment() {
        return completionDepartment;
    }

    public void setCompletionDepartment(String completionDepartment) {
        this.completionDepartment = completionDepartment;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentDescription() {
        return attachmentDescription;
    }

    public void setAttachmentDescription(String attachmentDescription) {
        this.attachmentDescription = attachmentDescription;
    }

    public String getModelState() {
        return modelState;
    }

    public void setModelState(String modelState) {
        this.modelState = modelState;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(Integer registerUserId) {
        this.registerUserId = registerUserId;
    }
}
