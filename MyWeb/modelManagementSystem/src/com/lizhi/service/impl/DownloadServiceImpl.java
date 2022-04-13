package com.lizhi.service.impl;

import com.lizhi.dao.DownloadDao;
import com.lizhi.dao.impl.DownloadDaoImpl;
import com.lizhi.pojo.Download;
import com.lizhi.service.DownloadService;

import java.util.List;

public class DownloadServiceImpl implements DownloadService {
    private DownloadDao downloadDao = new DownloadDaoImpl();

    @Override
    public void saveDownloadItem(Download download) {
        downloadDao.saveDownloadItem(download);
    }

    @Override
    public List<Download> queryDownloadsByUserId(int userId) {
        return downloadDao.queryDownloadsByUserId(userId);
    }

    @Override
    public Download getDownloadById(int id) {
        return downloadDao.queryDownloadById(id);
    }

    @Override
    public void updateDownload(Download download) {
        downloadDao.updateDownload(download);
    }

    @Override
    public List<Download> getAllDownloadsByModelId(int modelId) {
        return downloadDao.queryAllDownloadsByModelId(modelId);

    }
}
