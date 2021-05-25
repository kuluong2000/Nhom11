/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.service;

import com.quanlynhatre.Dao.SucKhoeDao;
import com.quanlynhatre.model.suckhoe;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SuckhoeService {
    private SucKhoeDao skDao;
    public SuckhoeService(){
        skDao = new SucKhoeDao();
    }
    public List<suckhoe>getAllSuckhoe(){
        return skDao.getAllSuckhoe();
    }
     public void insertsk(suckhoe sk){
        skDao.insertsk(sk);
    }
    public void updatesk(suckhoe sk){
        skDao.updatesk(sk);
    }
     public void deletesk(String mahs){
        skDao.deletesk(mahs);
    }
     
}
