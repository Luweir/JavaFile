package com.lizhi.dao;

import com.lizhi.pojo.Directory;

import java.util.List;

public interface DirectoryDao {
    public List<Directory> queryAllDirectory();

    public void updateDirectoryNameByName(String oldName, String newName);

    void dropDirectory(String directoryName);

    void addDirectory(Directory directory);
}
