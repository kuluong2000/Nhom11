/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.service;

import com.quanlynhatre.Dao.ThongKe;
import com.quanlynhatre.model.Thongke;
import java.util.List;

/**
 *
 * @author PC
 */
public class ThongkeService {
    private ThongKe tk;
    public ThongkeService(){
        tk = new ThongKe();
    }
    public List<Thongke>getAlltk(String sql){
        return tk.getAlltk(sql);
    }
}
