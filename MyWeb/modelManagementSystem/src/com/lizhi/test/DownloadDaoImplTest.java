package com.lizhi.test;

import com.lizhi.dao.DownloadDao;
import com.lizhi.dao.impl.DownloadDaoImpl;
import com.lizhi.pojo.Download;
import com.lizhi.service.DownloadService;
import com.lizhi.service.impl.DownloadServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DownloadDaoImplTest {

    @Test
    public void queryAllDownloadsByModelId() {
        DownloadService downloadService = new DownloadServiceImpl();
        List<Download> downloads = downloadService.getAllDownloadsByModelId(6);
        System.out.println(downloads);
    }
}