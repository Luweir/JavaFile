package com.lizhi.dao.impl;

import com.lizhi.dao.DirectoryDao;
import com.lizhi.pojo.Directory;

import java.util.List;

public class DirectoryDaoImpl extends BaseDao implements DirectoryDao {

    @Override
    public List<Directory> queryAllDirectory() {
        String sql = "select `id`,`curDirectory`,`parentDirectory` from t_directory order by `id` asc";
        return queryForList(sql, Directory.class);
    }

    @Override
    public void updateDirectoryNameByName(String oldName, String newName) {
        String sql = "update t_directory set curDirectory=? where curDirectory=?";
        update(sql, newName, oldName);
        sql = "update t_directory set parentDirectory=? where parentDirectory=?";
        update(sql, newName, oldName);
    }

    @Override
    public void dropDirectory(String directoryName) {
        String sql = "delete from t_directory where parentDirectory=?";
        update(sql, directoryName);
        sql = "delete from t_directory where curDirectory=?";
        update(sql, directoryName);
    }

    @Override
    public void addDirectory(Directory directory) {
        String sql = "insert into t_directory(`curDirectory`,`parentDirectory`) values(?,?)";
        update(sql, directory.getCurDirectory(), directory.getParentDirectory());
    }
}
