/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Computer
 */
public class ModelKho {

    private String tenhang, dvt, gia, ngay;
    private int soluong, id;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;

    public ModelKho() {
    }

    public ModelKho(int id, String tenhang, String dvt, String gia, String ngay, int soluong, ResultSet rs, Statement st, PreparedStatement pst) {
        this.id = id;
        this.tenhang = tenhang;
        this.dvt = dvt;
        this.gia = gia;
        this.ngay = ngay;
        this.soluong = soluong;
        this.rs = rs;
        this.st = st;
        this.pst = pst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    // Lay danh sach kho
    public List<ModelKho> laydskho() {
        Connection con = null;
        List<ModelKho> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from Kho";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelKho sp = new ModelKho();
                sp.setId(rs.getInt("ID"));
                sp.setTenhang(rs.getString("TenHang"));
                sp.setSoluong(rs.getInt("SoLuong"));
                sp.setGia(rs.getString("Gia"));
                sp.setDvt(rs.getString("DVT"));
                sp.setNgay(rs.getString("Ngay"));
                ds.add(sp);
            }
        } catch (Exception ex) {
        }
        return ds;
    }

    // Lay gia tri ID cuoi trong tableKho
    public List<ModelKho> layID() {
        Connection con = null;
        List<ModelKho> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select ID from Kho ORDER BY ID asc";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelKho sp = new ModelKho();
                sp.setId(rs.getInt("ID"));
                ds.add(sp);
            }
        } catch (Exception ex) {
        }
        return ds;
    }

    //Kiem tra san pham co ton tai trong Kho dua vao ID
    public boolean kiemtrakho(int id) {
        Connection con = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from Kho where ID=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            boolean result = rs.next();
            if (result) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }
// Loc ra cac san pham

    public List<ModelKho> locspkho(String tenhang) {
        Connection con = null;
        List<ModelKho> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from Kho where TenHang like ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, tenhang + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                ModelKho sp = new ModelKho();
                sp.setId(rs.getInt("ID"));
                sp.setTenhang(rs.getString("TenHang"));
                sp.setSoluong(rs.getInt("SoLuong"));
                sp.setGia(rs.getString("Gia"));
                sp.setDvt(rs.getString("DVT"));
                sp.setNgay(rs.getString("Ngay"));
                ds.add(sp);
            }
        } catch (Exception ex) {
        }
        return ds;
    }
    // Lay SoLuong theo TenSP

    public ModelKho laysoluong(String tensp) {
        Connection con = null;
        ModelKho sp = null;
//        List<ModelKho> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select SoLuong from Kho where TenHang=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, tensp);
            rs = pst.executeQuery();
            sp = new ModelKho();
            if (rs.next()) {
                sp.setSoluong(rs.getInt("SoLuong"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return sp;
    }

    public static void main(String[] args) throws ParseException {
        ModelKho sp = new ModelKho();
        List<ModelKho> ds = sp.locspkho("coca");
        for (int i = 0; i < ds.size(); i++) {
            System.out.println(ds.get(i).tenhang);

        }

    }
}
