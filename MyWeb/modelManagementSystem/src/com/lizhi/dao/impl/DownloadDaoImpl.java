package com.lizhi.dao.impl;

import com.lizhi.dao.DownloadDao;
import com.lizhi.pojo.Download;

import java.util.List;

public class DownloadDaoImpl extends BaseDao implements DownloadDao {

    @Override
    public void saveDownloadItem(Download download) {
        String sql = "insert into t_download(`modelId`,`userId`,`downloads`,`score`) values(?,?,?,?)";
        update(sql, download.getModelId(), download.getUserId(), download.getDownloads(), download.getScore());
    }

    @Override
    public List<Download> queryDownloadsByUserId(int userId) {
        String sql = "select `id`,`modelId`,`userId`,`downloads`,`score` from t_download where `userId`=? ORDER BY `id` DESC";
        return queryForList(sql, Download.class, userId);
    }

    @Override
    public Download queryDownloadById(int id) {
        String sql = "select `id`,`modelId`,`userId`,`downloads`,`score` from t_download where `id`=?";
        return queryForOne(sql, Download.class, id);
    }

    @Override
    public void updateDownload(Download download) {
        String sql = "update t_download set `id`=?,`modelId`=?,`userId`=?,`downloads`=?,`score`=? where `id` = ?";
        update(sql, download.getId(), download.getModelId(), download.getUserId(), download.getDownloads(), download.getScore(), download.getId());
    }

    @Override
    public List<Download> queryAllDownloadsByModelId(int modelId) {
        String sql = "select `id`,`modelId`,`userId`,`downloads`,`score` from t_download where `modelId`=? ORDER BY `id` DESC";
        return queryForList(sql, Download.class, modelId);
    }

    @Override
    public void deleteByModelId(int modelId) {
        String sql = "delete from t_download where `modelId`=?";
        update(sql, modelId);
    }

    @Override
    public void deleteByUserId(int userId) {
        String sql="delete from t_download where `userId`=?";
        update(sql, userId);
    }
}
