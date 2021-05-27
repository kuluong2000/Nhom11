/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.model;

/**
 *
 * @author PC
 */
public class suckhoe {
    String mahs;
    String tenhs;
    float chieucao;
     float cannang;
    float BIM;

    public void setTenhs(String tenhs) {
        this.tenhs = tenhs;
    }

    public String getTenhs() {
        return tenhs;
    }
   
    public void setBIM(float BIM) {
        this.BIM = BIM;
    }

    public float getBIM() {
        return BIM;
    }
    String trangthai;

    public String getMahs() {
        return mahs;
    }

    public float getChieucao() {
        return chieucao;
    }

    public float getCannang() {
        return cannang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setMahs(String mahs) {
        this.mahs = mahs;
    }

    public void setChieucao(float chieucao) {
        this.chieucao = chieucao;
    }

    public void setCannang(float cannang) {
        this.cannang = cannang;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

   
}
