package com.lizhi.dao;

import com.lizhi.pojo.Download;

import java.util.List;

public interface DownloadDao {
    public void saveDownloadItem(Download download);

    public List<Download> queryDownloadsByUserId(int userId);

    public Download queryDownloadById(int id);

    public void updateDownload(Download download);

    public List<Download> queryAllDownloadsByModelId(int modelId);

    public void deleteByModelId(int modelId);

    public void deleteByUserId(int userId);
}
