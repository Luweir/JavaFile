package com.lizhi.pojo;

public class Method {
    private Integer id;
    private String methodName;
    private String methodCallName;
    private Integer modelId;
    private String returnType;
    private String methodDescription;
    private Integer parameterCount;
    private String expectedResult;
    private String implementationClass;

    @Override
    public String toString() {
        return "Method{" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                ", methodCallName='" + methodCallName + '\'' +
                ", modelId=" + modelId +
                ", returnType='" + returnType + '\'' +
                ", methodDescription='" + methodDescription + '\'' +
                ", parameterCount=" + parameterCount +
                ", expectedResult='" + expectedResult + '\'' +
                ", implementationClass='" + implementationClass + '\'' +
                '}';
    }

    public Method(Integer id, String methodName, String methodCallName, Integer modelId, String returnType, String methodDescription, Integer parameterCount, String expectedResult, String implementationClass) {
        this.id = id;
        this.methodName = methodName;
        this.methodCallName = methodCallName;
        this.modelId = modelId;
        this.returnType = returnType;
        this.methodDescription = methodDescription;
        this.parameterCount = parameterCount;
        this.expectedResult = expectedResult;
        this.implementationClass = implementationClass;
    }

    public String getImplementationClass() {
        return implementationClass;
    }

    public void setImplementationClass(String implementationClass) {
        this.implementationClass = implementationClass;
    }

    public Method() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodCallName() {
        return methodCallName;
    }

    public void setMethodCallName(String methodCallName) {
        this.methodCallName = methodCallName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public Integer getParameterCount() {
        return parameterCount;
    }

    public void setParameterCount(Integer parameterCount) {
        this.parameterCount = parameterCount;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
}
