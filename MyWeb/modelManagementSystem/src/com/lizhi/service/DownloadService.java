package com.lizhi.service;

import com.lizhi.pojo.Download;

import java.util.List;

public interface DownloadService {
    // 保存用户-可下载模型条目
    public void saveDownloadItem(Download download);

    public List<Download> queryDownloadsByUserId(int userId);

    public Download getDownloadById(int id);

    public void updateDownload(Download download);

     public List<Download> getAllDownloadsByModelId(int modelId);
}
