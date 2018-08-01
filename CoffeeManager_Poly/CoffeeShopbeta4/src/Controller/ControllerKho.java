/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelKho;
import Model.ModelSanPham;
import Model.getData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Computer
 */
public class ControllerKho {

    ModelKho modelkho;
    PreparedStatement spt = null;
    ResultSet rs = null;
    DefaultTableModel model;
    Statement st;

    // Lay gia tri Column dua vao table SP
    public Vector vcotlistkho() {
        Vector vcot = new Vector();
        vcot.add("STT");
        vcot.add("Tên Hàng");
        vcot.add("Số Lượng");
        vcot.add("Đơn vị tính");
        vcot.add("Ngày Nhập");
        vcot.add("Giá");
        return vcot;
    }

    // Lay gia tri Row dua vao table SP
    public Vector vdonglistkho() {

        Vector vdong = new Vector();
        modelkho = new ModelKho();
        List<ModelKho> ds = modelkho.laydskho();
        for (int i = 0; i < ds.size(); i++) {
            Vector v = new Vector();
            v.add(ds.get(i).getId());
            v.add(ds.get(i).getTenhang());
            v.add(ds.get(i).getSoluong());
            v.add(ds.get(i).getDvt());
            v.add(ds.get(i).getNgay());
            v.add(ds.get(i).getGia());
            vdong.addElement(v);
        }
        return vdong;
    }
// Lay gia tri Column dua vao ID table SP

    public Vector vcotIDkho() {
        Vector vcot = new Vector();
        vcot.add("STT");
        vcot.add("Tên Hàng");
        vcot.add("Số Lượng");
        vcot.add("Đơn vị tính");
        vcot.add("Ngày Nhập");
        vcot.add("Giá");
        return vcot;
    }

    // Lay gia tri Row dua vao ID table Kho
    public Vector vdongIDkho(String tenhang) {

        Vector vdong = new Vector();
        modelkho = new ModelKho();
        List<ModelKho> ds = modelkho.locspkho(tenhang);
        for (int i = 0; i < ds.size(); i++) {
            Vector v = new Vector();
            v.add(ds.get(i).getId());
            v.add(ds.get(i).getTenhang());
            v.add(ds.get(i).getSoluong());
            v.add(ds.get(i).getDvt());
            v.add(ds.get(i).getNgay());
            v.add(ds.get(i).getGia());
            vdong.addElement(v);
        }
        return vdong;
    }

    //In gia tri toan bo san pham trong TableKho
    public void loadTablelistKho(JTable table) {
        model = new DefaultTableModel();
        model.setDataVector(vdonglistkho(), vcotlistkho());
        table.setModel(model);

    }

    //In gia tri san pham tuong ung voi gia tri tim kiem 
    public void loadTableKho(JTable table, String tenhang) {
        model = new DefaultTableModel();
        model.setDataVector(vdongIDkho(tenhang), vcotIDkho());
        table.setModel(model);

    }

    //Xoa 1 mat hang trong tableKho
    public boolean xoa(int id) {
        Connection con = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Delete Kho where ID=?";
            spt = con.prepareStatement(sql);
            spt.setInt(1, id);
            int result = spt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    //Them san pham vao tableKho
    public boolean themkho(String tenhang, int soluong, String dvt, String gia, String ngay) {
        Connection con = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Insert into Kho(TenHang,SoLuong,DVT,Gia,Ngay) values(?,?,?,?,?)";
            spt = con.prepareStatement(sql);
            spt.setString(1, tenhang);
            spt.setInt(2, soluong);
            spt.setString(3, dvt);
            spt.setString(4, gia);
            spt.setString(5, ngay);
            int result = spt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    //Cap nhat thong tin san pham 
    public boolean capnhatkho(int id, String tenhang, int soluong, String dvt, String gia, String ngay) {
        Connection con = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            modelkho = new ModelKho();
            String sql = "Update Kho set TenHang=?, SoLuong=?, DVT=?, Gia=?, Ngay=? where ID=?";
            spt = con.prepareStatement(sql);
            spt.setString(1, tenhang);
            spt.setInt(2, soluong);
            spt.setString(3, dvt);
            spt.setString(4, gia);
            spt.setString(5, ngay);
            spt.setInt(6, id);
            int result = spt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    //Tiem kiem
    public boolean timkiem(String tenhang, JTable table) {
        modelkho = new ModelKho();
        List<ModelKho> ds = modelkho.locspkho(tenhang);
        if (ds.size() > 0) {
            loadTableKho(table, tenhang);
            return true;
        } else {
            return false;
        }
    }
    // Update SoLuong cua 1 san pham trong Kho
    public boolean capnhatsoluong1sp(String tensp, int soluong) {
        Connection con = null;
        try {
            con = new getData().getConnect();
            String sql = "Update Kho set SoLuong=? where TenHang=?";
            spt = con.prepareStatement(sql);
            spt.setInt(1, soluong);
            spt.setString(2, tensp);
            int result = spt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        ControllerKho kho=new ControllerKho();
        boolean result=kho.capnhatsoluong1sp("lip", 21);
        System.out.println(result);
    }
}
