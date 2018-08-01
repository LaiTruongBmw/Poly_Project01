/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MQLSP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CQLSP {
    MQLSP Mqlsp;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel DTM;
    
    public Vector vtCol(){
        Vector vtCol = new Vector();
        vtCol.add("Mã Sản Phẩm");
        vtCol.add("Tên Sản Phẩm");
        vtCol.add("Giá");
        vtCol.add("Đơn Vị Tính");
        vtCol.add("URL");
        vtCol.add("Ghi Chú");
        return vtCol;
    }
    
    public Vector vtColList() {
        Vector vcot = new Vector();
        vcot.add("STT");
        vcot.add("Mã Sản Phẩm");
        vcot.add("Tên Sản Phẩm");
        vcot.add("Giá");
        vcot.add("Đơn vị tính");
        vcot.add("URL");
        vcot.add("Ghi Chú");
        return vcot;
    }
    
    public Vector vtRow(){
        Vector vtRow = new Vector();
        Mqlsp = new MQLSP();
        List<MQLSP> list = Mqlsp.getlistsp();
        for (int i = 0; i < list.size(); i++) {
            Vector v = new Vector();
            v.add(list.get(i).getMasp());
            v.add(list.get(i).getTensp());
            v.add(list.get(i).getGiasp());
            v.add(list.get(i).getDvt());
            v.add(list.get(i).getUrl());
            v.add(list.get(i).getGhichu());
            vtRow.addElement(v);
        }
        return vtRow;
    }
    
    public Vector vtRowList() {

        Vector vtRow = new Vector();
        Mqlsp = new MQLSP();
        List<MQLSP> list = Mqlsp.getlistsp();
        for (int i = 0; i < list.size(); i++) {
            int t=i;
            t++;
            Vector v = new Vector();
            v.add(t);
            v.add(list.get(i).getMasp());
            v.add(list.get(i).getTensp());
            v.add(list.get(i).getGiasp());
            v.add(list.get(i).getDvt());
            v.add(list.get(i).getUrl());
            v.add(list.get(i).getGhichu());
            vtRow.addElement(v);
        }
        return vtRow;
    }
    
    public void tbSP(JTable table, String masp){
        DTM = new DefaultTableModel();
        DTM.setDataVector(vtRow(), vtCol());
        table.setModel(DTM);

    }

    public void tbListSP(JTable table) {
        DTM = new DefaultTableModel();
        DTM.setDataVector(vtRowList(), vtColList());
        table.setModel(DTM);

    }
    public static void main(String[] args) {
        CQLSP CSP = new CQLSP();
    }
}
