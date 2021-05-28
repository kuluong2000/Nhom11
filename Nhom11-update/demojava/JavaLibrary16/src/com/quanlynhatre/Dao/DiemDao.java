/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.Dao;

import com.quanlynhatre.model.QL_DIEM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DiemDao {
  public List<QL_DIEM> getAllDiem(){
        List<QL_DIEM> diems = new ArrayList<QL_DIEM>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = " select * from tongdiem,hocsinh where tongdiem.mahs=hocsinh.mahs";
        try{
            PreparedStatement preparedStement =con.prepareStatement(sql);
            ResultSet rs = preparedStement.executeQuery();
            while(rs.next()){
                QL_DIEM diem = new QL_DIEM();
                diem.setMahs(rs.getString("mahs"));
                diem.setHoten(rs.getString("hoten"));
                diem.setToan(rs.getFloat("toan"));
                diem.setVannghe(rs.getFloat("vannghe"));
                diem.setTheduc(rs.getFloat("theduc"));
                diem.setMythuat(rs.getFloat("mythuat"));
                diem.setTongdiem(rs.getFloat("tongdiem"));
                diem.setHocluc(rs.getString("hocluc"));
                diems.add(diem);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return diems;
    }
  public void  insertdiem(QL_DIEM ql_diem){
          Connection con= JDBCConnection.getJDBCConnection();
          String sql ="insert into tongdiem values (?,?,?,?,?,?,?)";
          try{
                PreparedStatement preparedStatement= con.prepareStatement(sql);
                preparedStatement.setString(1,ql_diem.getMahs());
                preparedStatement.setFloat(2,ql_diem.getToan());
                preparedStatement.setFloat(3,ql_diem.getVannghe());
                preparedStatement.setFloat(4,ql_diem.getTheduc());
                preparedStatement.setFloat(5,ql_diem.getMythuat());
                preparedStatement.setFloat(6,ql_diem.getTongdiem());
                preparedStatement.setString(7,ql_diem.getHocluc());
                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
          }catch(SQLException e){
              e.printStackTrace();
          }}
  public void  updateDiem(QL_DIEM ql_diem){
          Connection con= JDBCConnection.getJDBCConnection();
          String sql ="update tongdiem set mahs = ?, toan= ?, vannghe = ?, theduc = ?, mythuat = ?, tongdiem = ?,hocluc=? where mahs = ? ";
          try{
                PreparedStatement preparedStatement= con.prepareStatement(sql);
                preparedStatement.setString(1,ql_diem.getMahs());
                preparedStatement.setFloat(2,ql_diem.getToan());
                preparedStatement.setFloat(3,ql_diem.getVannghe());
                preparedStatement.setFloat(4,ql_diem.getTheduc());
                preparedStatement.setFloat(5,ql_diem.getMythuat());
                preparedStatement.setFloat(6,ql_diem.getTongdiem());
                preparedStatement.setString(7,ql_diem.getHocluc());
                preparedStatement.setString(8,ql_diem.getMahs());
                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
          }catch(SQLException e){
              e.printStackTrace();
          }
    }
  public void deleteDiem(String mahs){
         Connection con= JDBCConnection.getJDBCConnection();
         String sql = " delete from tongdiem where mahs = ?";
         try{
              PreparedStatement preparedStatement= con.prepareStatement(sql);
              preparedStatement.setString(1, mahs);
              int rs = preparedStatement.executeUpdate();
              System.out.println(rs);
         }catch(SQLException e){
             e.printStackTrace();
         }
    }
}