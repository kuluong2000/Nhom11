/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.service;

import com.quanlynhatre.Dao.DiemDao;
import com.quanlynhatre.model.QL_DIEM;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DiemService{
    private DiemDao diemDao;

    public DiemService() {
        diemDao= new DiemDao();
    }
    public List<QL_DIEM>getAllDiem(){
        return diemDao.getAllDiem();
    }
    public void insertdiem(QL_DIEM ql_diem){
        diemDao.insertdiem (ql_diem);
    }
    public void updateDiem(QL_DIEM ql_diem){
        diemDao.updateDiem(ql_diem);
    }
     public void deleteDiem(String mahs){
        diemDao.deleteDiem(mahs);
    }
}
