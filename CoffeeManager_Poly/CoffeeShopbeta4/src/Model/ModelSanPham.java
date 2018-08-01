
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ModelSanPham {

    private String masp, tensp, giasp, dvt, url, ghichu, maban;
    private int soluong, tongtien, size, magoi;
    PreparedStatement spt = null;
    Statement st = null;
    ResultSet rs = null;

    public ModelSanPham() {
    }

    public ModelSanPham(String masp, String tensp, String giasp, String dvt, String url, String ghichu, String maban, int soluong, int tongtien, int size, int magoi) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.dvt = dvt;
        this.url = url;
        this.ghichu = ghichu;
        this.maban = maban;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.size = size;
        this.magoi = magoi;
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

    /**
     * @return the maban
     */
    public String getMaban() {
        return maban;
    }

    /**
     * @param maban the maban to set
     */
    public void setMaban(String maban) {
        this.maban = maban;
    }

    /**
     * @return the soluong
     */
    public int getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    /**
     * @return the tongtien
     */
    public int getTongtien() {
        return tongtien;
    }

    /**
     * @param tongtien the tongtien to set
     */
    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the magoi
     */
    public int getMagoi() {
        return magoi;
    }

    /**
     * @param magoi the magoi to set
     */
    public void setMagoi(int magoi) {
        this.magoi = magoi;
    }

    // Kiem tra SanPham co ton tai hay khong?
    // Sử dụng cho Search
    public boolean kiemtraSP(String masp) {
        Connection con = null;
        ModelSanPham sp = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from SanPham where MaSP like ?";
            spt = con.prepareStatement(sql);
            spt.setString(1, masp + "%");
            rs = spt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    // Kiem tra Ban co ton tai hay khong?
    public boolean kiemtraBan(String maban) {
        Connection con = null;
        ModelSanPham sp = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from GoiSP where MaBan=?";
            spt = con.prepareStatement(sql);
            spt.setString(1, maban);
            rs = spt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }
// Kiem tra xem Table GoiSP co du lieu hay khong?
    public boolean kiemtraTableGoiSP() {
        Connection con = null;
        ModelSanPham sp = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from GoiSP";
            st=con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }
    //Xuat 1 SanPham theo MaSP
    public List<ModelSanPham> timSP(String masp) {
        Connection con = null;
        ModelSanPham sp = null;
        List<ModelSanPham> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from SanPham where MaSP like ?";
            spt = con.prepareStatement(sql);
            spt.setString(1, masp + "%");
            rs = spt.executeQuery();

            while (rs.next()) {
                sp = new ModelSanPham();
                sp.setMasp(rs.getString("MaSP"));
                sp.setTensp(rs.getString("TenSP"));
                sp.setGiasp(rs.getString("GiaSP"));
                sp.setDvt(rs.getString("DVT"));
                ds.add(sp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ds;
    }

    //Xuat 1 SanPham theo TenSP
    //------Đang sử dụng cho Nhập
    public ModelSanPham gettenSP(String tensp) {
        Connection con = null;
        ModelSanPham sp = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from SanPham where TenSP=?";
            spt = con.prepareStatement(sql);
            spt.setString(1, tensp);
            rs = spt.executeQuery();
            sp = new ModelSanPham();
            while (rs.next()) {
                sp.setMasp(rs.getString("MaSP"));
                sp.setTensp(rs.getString("TenSP"));
                sp.setGiasp(rs.getString("GiaSP"));
                sp.setDvt(rs.getString("DVT"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return sp;
    }

    //Xuat SanPham theo MaBan
    //Đang sử dụng để Load Table GoiSP
    public List<ModelSanPham> gettheomaBan(String maban) {
        Connection con = null;
        List<ModelSanPham> ds = new ArrayList<>();
        int tongtien;
        String sum = "";

        String[] chuoi;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select SanPham.TenSP , SanPham.MaSP, GiaSP, GoiSP.SoLuong,(GiaSP * GoiSP.Soluong)AS TongTien\n"
                    + "from SanPham join GoiSP on SanPham.MaSP = GoiSP.MaSP\n"
                    + "where MaBan=? ";
            spt = con.prepareStatement(sql);
            spt.setString(1, maban);
            rs = spt.executeQuery();
            while (rs.next()) {
                ModelSanPham sp = new ModelSanPham();
                sp.setTensp(rs.getString("TenSP"));
                sp.setSoluong(rs.getInt("SoLuong"));
                sp.setGiasp(rs.getString("GiaSP"));
                sp.setTongtien(rs.getInt("TongTien"));

                ds.add(sp);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return ds;
    }

    //Xuat list SanPham 
    public List<ModelSanPham> getlistsp() {
        Connection con = null;
        List<ModelSanPham> ds = new ArrayList<ModelSanPham>();
        int tongtien;
        String sum = "";

        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from SanPham ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelSanPham sp = new ModelSanPham();
                sp.setMasp(rs.getString("MaSP"));
                sp.setTensp(rs.getString("TenSP"));
                sp.setGiasp(rs.getString("GiaSP"));
                sp.setDvt(rs.getString("DVT"));
                ds.add(sp);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return ds;
    }
//Xuat list SanPham theo MaSP trong bang GoiSP

    public List<ModelSanPham> getlistGoisp() {
        Connection con = null;
        List<ModelSanPham> ds = new ArrayList<ModelSanPham>();
        int tongtien;
        String sum = "";

        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from GoiSP ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelSanPham sp = new ModelSanPham();
                sp.setMasp(rs.getString("MaSP"));
                sp.setMaban(rs.getString("MaBan"));
                sp.setDvt(rs.getString("SoLuong"));
                ds.add(sp);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return ds;
    }

    //Lay MaBan
    //Đang sử dụng dùng để lấy danh sách Mã Bàn đưa vào List để Chuyển Bàn
    public List<ModelSanPham> getdsBan() {
        Connection con = null;
        List<ModelSanPham> ds = new ArrayList<ModelSanPham>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select MaBan from Ban order by MaBan asc ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelSanPham sp = new ModelSanPham();
                sp.setMaban(rs.getString("MaBan"));
                ds.add(sp);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return ds;
    }

//    //Lay MaGoi trong bang GoiSP
    public ModelSanPham laymagoi(String maban, String masp) {
        Connection con = null;
        ModelSanPham sp = null;
        int magoi = 0;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select MaGoi from GoiSP where MaBan=? and MaSP=?";
            spt = con.prepareStatement(sql);
            spt.setString(1, maban);
            spt.setString(2, masp);
            rs = spt.executeQuery();
            sp = new ModelSanPham();
            if (rs.next()) {
                sp.setMagoi(rs.getInt("MaGoi"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return sp;
    }

    //Lay MaGoi cua san pham cuoi cung trong bang
    public List<ModelSanPham> laymagoi() {
        Connection con = null;
        ModelSanPham sp = null;
        List<ModelSanPham> ds = new ArrayList<>();
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select MaGoi from GoiSP order by MaGoi asc";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                sp = new ModelSanPham();
                sp.setMagoi(rs.getInt("MaGoi"));
                ds.add(sp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ds;
    }

    //Xoa SanPham dua vao MaBan
    public boolean xoasp(String maban) {
        Connection con = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Delete from GoiSP where MaBan=?";
            PreparedStatement spt = con.prepareStatement(sql);
            spt.setString(1, maban);
            int result = spt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
// ***@***
    // QUẢN LÝ SẢN PHẨM - PHẠM QUỐC KHÁNH
    // ***@***
    public List<ModelSanPham> getlistqlsp() {
        Connection con = null;
        List<ModelSanPham> ds = new ArrayList<ModelSanPham>();

        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "Select * from SanPham ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ModelSanPham sp = new ModelSanPham();
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
    // END QUẢN LÝ SẢN PHẨM
    public static void main(String[] args) {
        ModelSanPham sp = new ModelSanPham();
        List<ModelSanPham> ds = sp.laymagoi();
        String tensp=sp.gettenSP("cocacola").getTensp();
        System.out.println(tensp);
//        ModelSanPham ds = sp.laymagoi("b2","coca");
//        int size = ds.get(ds.size() - 1).magoi + 1;
//        boolean rs=sp.xoasp("b6");
//        System.out.println(rs);

    }
}