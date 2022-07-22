package com.lizhi.service.impl;


import com.lizhi.dao.DownloadDao;
import com.lizhi.dao.ModelDao;
import com.lizhi.dao.RecordDao;
import com.lizhi.dao.UserDao;
import com.lizhi.dao.impl.DownloadDaoImpl;
import com.lizhi.dao.impl.ModelDaoImpl;
import com.lizhi.dao.impl.RecordDaoImpl;
import com.lizhi.dao.impl.UserDaoImpl;
import com.lizhi.pojo.Download;
import com.lizhi.pojo.Model;
import com.lizhi.pojo.Record;
import com.lizhi.pojo.User;
import com.lizhi.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();


    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.queryUserByUserId(userId);
    }

    @Override
    public List<Download> getDownloadByUser(User user) {
        DownloadDao downloadDao = new DownloadDaoImpl();
        ModelDao modelDao = new ModelDaoImpl();
        List<Download> downloads = downloadDao.queryDownloadsByUserId(user.getId());
        for (int i = 0; i < downloads.size(); i++) {
            Model model = modelDao.queryModelById(downloads.get(i).getModelId());
            downloads.get(i).setModelName(model.getModelName());
            downloads.get(i).setModelType(model.getModelType());
            downloads.get(i).setReleaseDate(model.getReleaseDate());
        }
        return downloads;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.queryAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    @Override
    public void deleteUser(int userId) {
        //删除所有跟用户有关的记录、如果该用户为开发者，则将其开发模型的registerId置为null
        RecordDao recordDao = new RecordDaoImpl();
        DownloadDao downloadDao = new DownloadDaoImpl();
        ModelDao modelDao = new ModelDaoImpl();

        downloadDao.deleteByUserId(userId);
        recordDao.deleteByUserId(userId);
        modelDao.updateByRegisterId(userId);
        userDao.deleteByUserId(userId);
    }


}
