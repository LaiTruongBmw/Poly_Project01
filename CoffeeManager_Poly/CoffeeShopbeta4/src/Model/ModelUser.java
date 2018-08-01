/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Computer
 */
public class ModelUser {

    private String username, password, hoten, sdt, cmnd, diachi, gioitinh;

    private boolean roles;
    Connection con = null;
    PreparedStatement pst = null;
    Statement st = null;
    ResultSet rs = null;

    public ModelUser() {
    }

    public ModelUser(String username, String password, String hoten, String sdt, String cmnd, String diachi, String gioitinh, boolean roles) {
        this.username = username;
        this.password = password;
        this.hoten = hoten;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.roles = roles;
    }

    public ModelUser(String username, String hoten, String gioitinh, String cmnd, String sdt, String diachi) {
        this.username = username;

        this.hoten = hoten;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.diachi = diachi;
        this.gioitinh = gioitinh;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public boolean isRoles() {
        return roles;
    }

    public void setRoles(boolean roles) {
        this.roles = roles;
    }

    // Show danh sach nhan vien
    public List<ModelUser> getList() {
        List<ModelUser> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from ChiTietNV INNER JOIN NhanVien ON ChiTietNV.Username = NhanVien.Username where Roles=0 ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelUser user = new ModelUser(rs.getString("Username"), rs.getString("HoTen"), rs.getString("GioiTinh"), rs.getString("CMND"), rs.getString("SDT"), rs.getString("DiaChi"));
                ds.add(user);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return ds;
    }
    public static void main(String[] args) {
        ModelUser us=new ModelUser();
        List<ModelUser> ds=us.getList();
        for(int i=0;i<ds.size();i++){
            System.out.println(ds.get(i).hoten);
        }
    }
}
