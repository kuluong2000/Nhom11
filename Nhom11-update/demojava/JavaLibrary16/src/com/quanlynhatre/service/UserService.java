/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.service;

import com.quanlynhatre.Dao.UserDao;
import com.quanlynhatre.model.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserService {
    private UserDao userDao;
    public UserService(){
        userDao =  new UserDao();
    }
    public List<User> getAllUser(String SQL){
 
        return userDao.getAllUser(SQL);
}
    public void insertSp(User user){
        userDao.insertSp(user);
    }
     public void updateUser(User user){
        userDao.updateUser(user);
    }
    public void deleteUser(String mahs){
        userDao.deleteUser(mahs);
    }
}
