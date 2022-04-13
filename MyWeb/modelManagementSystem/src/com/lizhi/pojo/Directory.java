package com.lizhi.pojo;

public class Directory {
    private Integer id;
    private String curDirectory;
    private String parentDirectory;
    private String haveChild;

    @Override
    public String toString() {
        return "Directory{" +
                "curDirectory='" + curDirectory + '\'' +
                ", parentDirectory='" + parentDirectory + '\'' +
                '}';
    }

    public Directory(String curDirectory, String parentDirectory) {
        this.curDirectory = curDirectory;
        this.parentDirectory = parentDirectory;

    }

    public Directory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHaveChild() {
        return haveChild;
    }

    public void setHaveChild(String haveChild) {
        this.haveChild = haveChild;
    }

    public String getCurDirectory() {
        return curDirectory;
    }

    public void setCurDirectory(String curDirectory) {
        this.curDirectory = curDirectory;
    }

    public String getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(String parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

}
