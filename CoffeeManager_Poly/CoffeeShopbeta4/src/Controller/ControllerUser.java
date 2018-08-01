/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelUser;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Computer
 */
public class ControllerUser {
    ModelUser modeluser;
    public Vector vcot() {
        Vector vCols = new Vector();
       
//        vCols.add("STT");
        vCols.add("ID");
        vCols.add("Họ Tên");
        vCols.add("Giới Tính");
        vCols.add("CMND");
        vCols.add("Số điện thoại");
        vCols.add("Địa chỉ");
        return vCols;
    }
    public Vector vdong(){
        Vector vdong=new Vector();
        modeluser=new ModelUser();
        List<ModelUser> ds=modeluser.getList();
        for(ModelUser us:ds){
            Vector v=new Vector();
            v.add(us.getUsername());
            v.add(us.getHoten());
            v.add(us.getGioitinh());
            v.add(us.getCmnd());
            v.add(us.getSdt());
            v.add(us.getDiachi());
            
            vdong.addElement(v);
        }
        return vdong;
    }
}
