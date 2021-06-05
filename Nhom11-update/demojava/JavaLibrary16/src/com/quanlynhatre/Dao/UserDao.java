/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.Dao;

import com.quanlynhatre.model.User;
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
public class UserDao {
     public List<User> getAllUser(String SQL){
        List<User> users = new ArrayList<User>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = SQL;
        try{
            PreparedStatement preparedStement =con.prepareStatement(sql);
            ResultSet rs = preparedStement.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setMahs(rs.getString("mahs"));
                user.setMalop(rs.getString("malop"));
                user.setHoten(rs.getString("hoten"));
                user.setNgaysinh(rs.getString("ngaysinh"));
                user.setGiotinh(rs.getString("giotinh"));
                user.setDiachi(rs.getString("diachi"));
                user.setDantoc(rs.getString("dantoc"));
                user.setTongiao(rs.getString("tongiao"));
                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }
     public void  insertSp(User user){
          Connection con= JDBCConnection.getJDBCConnection();
          String sql ="insert into hocsinh values (?,?,?,?,?,?,?,?)";
          try{
                PreparedStatement preparedStatement= con.prepareStatement(sql);
                preparedStatement.setString(1,user.getMahs());
                preparedStatement.setString(2,user.getMalop());
                preparedStatement.setString(3,user.getHoten());
                preparedStatement.setString(4,user.getNgaysinh());
                preparedStatement.setString(5,user.getGiotinh());
                preparedStatement.setString(6,user.getDiachi());
                preparedStatement.setString(7,user.getDantoc());
                preparedStatement.setString(8,user.getTongiao());
                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
          }catch(SQLException e){
              e.printStackTrace();
          }}
     public void  updateUser(User user){
          Connection con= JDBCConnection.getJDBCConnection();
          String sql ="update hocsinh set malop = ?, hoten= ?, ngaysinh = ?, giotinh = ?, diachi = ?, dantoc = ?,tongiao=? where mahs = ? ";
          try{
                PreparedStatement preparedStatement= con.prepareStatement(sql);
                preparedStatement.setString(1,user.getMalop());
                preparedStatement.setString(2,user.getHoten());
                preparedStatement.setString(3,user.getNgaysinh());
                preparedStatement.setString(4,user.getGiotinh());
                preparedStatement.setString(5,user.getDiachi());
                preparedStatement.setString(6,user.getDantoc());
                preparedStatement.setString(7,user.getTongiao());
                preparedStatement.setString(8,user.getMahs());
                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
          }catch(SQLException e){
              e.printStackTrace();
          }
    }
         public void deleteUser(String mahs){
         Connection con= JDBCConnection.getJDBCConnection();
         String sql = " delete from hocsinh where mahs = ?";
         try{
              PreparedStatement preparedStatement= con.prepareStatement(sql);
              preparedStatement.setString(1, mahs);
              int rs = preparedStatement.executeUpdate();
              System.out.println(rs);
         }catch(SQLException e){
             e.printStackTrace();
         }
    }
         
    public static void main(String[] args) {
        
    }
}
