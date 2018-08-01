package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class getData {

    public Connection getConnect() {
        Connection con=null;
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Coffee;";
            con = DriverManager.getConnection(url, "sa", "123");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Chưa kết nối được với Database!");
        }
        return con;
    }
}
