/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author Computer
 */
public class test {
    public static void main(String[] args) {
        ModelUser user=new ModelUser();
        Vector vdong=new Vector();
        
        List<ModelUser> ds=user.getList();
        for(ModelUser us:ds){
            Vector v=new Vector();
            v.add(us);
            vdong.addElement(v);
            System.out.println(v.firstElement().toString());
        }
    }
}
