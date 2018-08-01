package Controller;

import Model.User;
import Model.getData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JLabel;

public class UserData {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Connection con = null;
    public static Statement st;

    public User dangNhap(String taiKhoan, String pass) {
        User us = null;
        try {
            getData data = new getData();
            con = data.getConnect();
            String sql = "SELECT * FROM NhanVien WHERE Username = ? and Password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            us = new User();
            if (rs.next()) {
                us.setUsername(rs.getString("Username"));
                us.setPassword(rs.getString("Password"));
                us.setRoles(rs.getBoolean("Roles"));
            } else{
                us=null;
            }
        } catch (Exception e) {

        }
        return us;
    }
//
    public static void main(String[] args) {
        UserData data = new UserData();
        User us = data.dangNhap("admin", "admin");
    }
}
