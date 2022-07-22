package com.lizhi.pojo;

public class Parameter {
    private Integer id;
    private String paramName;
    private Integer modelId;
    private Integer methodId;
    // 参数类型
    private String paramType;
    // 参数描述
    private String paramDescription;
    private Integer isJson;
    // 数组长度  非必选
    private Integer arrayLength;
    // 参数示例
    private String paramSample;


    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", paramName='" + paramName + '\'' +
                ", modelId=" + modelId +
                ", methodId=" + methodId +
                ", paramType='" + paramType + '\'' +
                ", paramDescription='" + paramDescription + '\'' +
                ", isJson=" + isJson +
                ", arrayLength=" + arrayLength +
                ", paramSample='" + paramSample + '\'' +
                '}';
    }

    public Parameter(Integer id, String paramName, Integer modelId, Integer methodId, String paramType, String paramDescription, Integer isJson, Integer arrayLength, String paramSample) {
        this.id = id;
        this.paramName = paramName;
        this.modelId = modelId;
        this.methodId = methodId;
        this.paramType = paramType;
        this.paramDescription = paramDescription;
        this.isJson = isJson;
        this.arrayLength = arrayLength;
        this.paramSample = paramSample;
    }

    public Parameter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamDescription() {
        return paramDescription;
    }

    public void setParamDescription(String paramDescription) {
        this.paramDescription = paramDescription;
    }

    public Integer getIsJson() {
        return isJson;
    }

    public void setIsJson(Integer isJson) {
        this.isJson = isJson;
    }

    public Integer getArrayLength() {
        return arrayLength;
    }

    public void setArrayLength(Integer arrayLength) {
        this.arrayLength = arrayLength;
    }

    public String getParamSample() {
        return paramSample;
    }

    public void setParamSample(String paramSample) {
        this.paramSample = paramSample;
    }
}
