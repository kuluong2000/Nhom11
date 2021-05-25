package com.quanlynhatre.Dao;

import com.quanlynhatre.model.QL_DIEM;
import com.quanlynhatre.model.suckhoe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucKhoeDao {
 public List<suckhoe> getAllSuckhoe(){
        List<suckhoe> sks = new ArrayList<suckhoe>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = " select * from suckhoe,hocsinh where suckhoe.mahs = hocsinh.mahs";
        try{
            PreparedStatement preparedStement =con.prepareStatement(sql);
            ResultSet rs = preparedStement.executeQuery();
            while(rs.next()){
                suckhoe sk = new suckhoe();
               sk.setMahs(rs.getString("mahs"));
               sk.setTenhs(rs.getString("hoten"));
               sk.setChieucao(rs.getFloat("chieucao"));
               sk.setCannang(rs.getFloat("cannang"));
               sk.setTrangthai(rs.getString("trangthai"));
                sks.add(sk);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sks;
    }
 public void  insertsk(suckhoe sk){
          Connection con= JDBCConnection.getJDBCConnection();
          String sql ="insert into suckhoe values (?,?,?,?)";
          try{
                PreparedStatement preparedStatement= con.prepareStatement(sql);
                preparedStatement.setString(1,sk.getMahs());
                preparedStatement.setFloat(2,sk.getChieucao());
                preparedStatement.setFloat(3,sk.getCannang());
                preparedStatement.setString(4,sk.getTrangthai());
                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
          }catch(SQLException e){
              e.printStackTrace();
          }}
  public void  updatesk(suckhoe sk){
          Connection con= JDBCConnection.getJDBCConnection();
          String sql ="update suckhoe set mahs = ?, chieucao= ?, cannang = ?, trangthai = ? where mahs = ? ";
          try{
                PreparedStatement preparedStatement= con.prepareStatement(sql);
                preparedStatement.setString(1,sk.getMahs());
                preparedStatement.setFloat(2,sk.getChieucao());
                preparedStatement.setFloat(3,sk.getCannang());
                preparedStatement.setString(4,sk.getTrangthai());
                preparedStatement.setString(5,sk.getMahs());
               
                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
          }catch(SQLException e){
              e.printStackTrace();
          }
    }
  public void deletesk(String mahs){
         Connection con= JDBCConnection.getJDBCConnection();
         String sql = " delete from suckhoe where mahs = ?";
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
