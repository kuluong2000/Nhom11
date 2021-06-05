/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.Dao;

import com.quanlynhatre.model.Thongke;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author PC
 */
public class ThongKe {
    public List<Thongke> getAlltk(String SQL){
        List<Thongke> thongkes = new ArrayList<Thongke>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = SQL;
//        select * from tongdiem,hocsinh where tongdiem.mahs = hocsinh.mahs
        try{
            PreparedStatement preparedStement =con.prepareStatement(sql);
            ResultSet rs = preparedStement.executeQuery();
            while(rs.next()){
                Thongke tk = new Thongke();
               tk.setMahs(rs.getString("mahs"));
               tk.setTenhs(rs.getString("hoten"));
                tk.setLop(rs.getString("malop"));
                tk.setGioitinh(rs.getString("giotinh"));
                tk.setNgaysinh(rs.getString("ngaysinh"));
                tk.setDiem(rs.getFloat("tongdiem"));
                tk.setXeploai(rs.getString("hocluc"));
                thongkes.add(tk);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return thongkes;
    }
    
}
