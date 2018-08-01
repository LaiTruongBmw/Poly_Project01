/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MQLSP {
    private String masp;
    private String tensp;
    private String giasp;
    private String dvt;
    private String url;
    private String ghichu;
    PreparedStatement ps = null;
    Statement s = null;
    ResultSet rs = null;

    public MQLSP() {
    }

    public MQLSP(String masp, String tensp, String giasp, String dvt, String url, String ghichu) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.dvt = dvt;
        this.url = url;
        this.ghichu = ghichu;
    }

    /**
     * @return the masp
     */
    public String getMasp() {
        return masp;
    }

    /**
     * @param masp the masp to set
     */
    public void setMasp(String masp) {
        this.masp = masp;
    }

    /**
     * @return the tensp
     */
    public String getTensp() {
        return tensp;
    }

    /**
     * @param tensp the tensp to set
     */
    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    /**
     * @return the giasp
     */
    public String getGiasp() {
        return giasp;
    }

    /**
     * @param giasp the giasp to set
     */
    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    /**
     * @return the dvt
     */
    public String getDvt() {
        return dvt;
    }

    /**
     * @param dvt the dvt to set
     */
    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the ghichu
     */
    public String getGhichu() {
        return ghichu;
    }

    /**
     * @param ghichu the ghichu to set
     */
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
    public List<MQLSP> getlistsp() {
        Connection con = null;
        List<MQLSP> ds = new ArrayList<MQLSP>();

        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from SanPham ";
            s = con.createStatement();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                MQLSP sp = new MQLSP();
                sp.setMasp(rs.getString("MaSP"));
                sp.setTensp(rs.getString("TenSP"));
                sp.setGiasp(rs.getString("GiaSP"));
                sp.setDvt(rs.getString("DVT"));
                sp.setUrl(rs.getString("URL"));
                sp.setGhichu(rs.getString("GhiChu"));
                ds.add(sp);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return ds;
    }
    public static void main(String[] args) {
        MQLSP sp=new MQLSP();
        List<MQLSP> ds=sp.getlistsp();
        for(int i=0;i<ds.size();i++){
            System.out.println(ds.get(i).giasp);
            }
    }
}
