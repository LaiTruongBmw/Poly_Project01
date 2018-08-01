package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKe {

    private String masp, tongtien, ngay, tensp, dongia;
    private int soluong;
    public static ArrayList<ThongKe> tk = new ArrayList<>();

    public ThongKe() {
    }

    public ThongKe(String masp, String tongtien, String ngay, String tensp, String dongia, int soluong) {
        this.masp = masp;
        this.tongtien = tongtien;
        this.ngay = ngay;
        this.tensp = tensp;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public static ArrayList<ThongKe> getTk() {
        return tk;
    }

    public static void setTk(ArrayList<ThongKe> tk) {
        ThongKe.tk = tk;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    //Update SoLuong vao bang ThongKe
    public boolean capnhatsoluong(String masp, int soluong, String ngay) {
        Connection con = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Insert into ThongKe(MaSP,SL,Ngay) values(?,?,?)";
            PreparedStatement spt = con.prepareStatement(sql);
            spt.setString(1, masp);
            spt.setInt(2, soluong);
            spt.setString(3, ngay);
            int result = spt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
    // Xuat danh sach san pham theo ngay

    public List<ThongKe> xuatdstheongay(String ngaydau, String ngaycuoi) {
        Connection con = null;
        List<ThongKe> ds = new ArrayList<>();
        try {
            con = new getData().getConnect();
            String sql = "SELECT SanPham.TenSP, SUM(ThongKe.SL) AS 'SL', SanPham.GiaSP\n"
                    + "FROM SanPham JOIN ThongKe ON SanPham.MaSP = ThongKe.MaSP\n"
                    + "WHERE ThongKe.Ngay BETWEEN ? AND ?\n"
                    + "GROUP BY SanPham.TenSP, SanPham.GiaSP\n"
                    + "ORDER BY SUM(ThongKe.SL) DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ngaydau);
            pst.setString(2, ngaycuoi);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setTensp(rs.getString("TenSP"));
                tk.setSoluong(rs.getInt("SL"));
                tk.setDongia(rs.getString("GiaSP"));
                ds.add(tk);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ds;
    }

    public static void main(String[] args) {
        ThongKe tk = new ThongKe();
//        boolean rs = tk.capnhatsoluong("100", 1);
//        System.out.println(rs);
        List<ThongKe> ds = tk.xuatdstheongay("21/12/2017", "24/12/2017");
        for (int i = 0; i < ds.size(); i++) {
            System.out.println(ds.get(i).getTensp());
        }
    }
}
