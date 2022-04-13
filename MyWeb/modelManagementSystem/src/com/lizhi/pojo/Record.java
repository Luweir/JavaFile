package com.lizhi.pojo;


import java.util.Date;

public class Record {
    private Integer id;
    private Integer modelId;
    private String modelName;
    private String recordType;
    private Integer applicantId;
    private String applicantName;// 提供属性，但不提供它的初始化，数据库也不存，每次使用需要根据applicantId查找t_user 再赋值
    private Date applyDate;
    private String applyReason;
    private String recordState;
    private Date reviewDate;
    private Integer reviewerId;
    private String reviewerName; // 提供属性，但不提供它的初始化，数据库也不存，每次使用需要根据reviewerId查找t_user 再赋值
    private String reviewOpinion;

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", recordType='" + recordType + '\'' +
                ", applicantId=" + applicantId +
                ", applyDate=" + applyDate +
                ", applyReason='" + applyReason + '\'' +
                ", recordState='" + recordState + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                ", reviewerId=" + reviewerId +
                ", reviewOpinion='" + reviewOpinion + '\'' +
                '}';
    }

    public Record(Integer id, Integer modelId, String modelName, String recordType, Integer applicantId, Date applyDate, String applyReason, String recordState, Date reviewDate, Integer reviewerId, String reviewOpinion) {
        this.id = id;
        this.modelId = modelId;
        this.modelName = modelName;
        this.recordType = recordType;
        this.applicantId = applicantId;
        this.applyDate = applyDate;
        this.applyReason = applyReason;
        this.recordState = recordState;
        this.reviewDate = reviewDate;
        this.reviewerId = reviewerId;
        this.reviewOpinion = reviewOpinion;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Record() {
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getRecordState() {
        return recordState;
    }

    public void setRecordState(String applyState) {
        this.recordState = applyState;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }
}
