/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

import java.util.ArrayList;

/**
 *
 * @author HP
 */

public class Registro {
    private ArrayList<String> pablo=new ArrayList();
    private int size;

    public Registro() {
    }

    public Registro(int size) {
        this.size = size;
    }

    public ArrayList<String> getPablo() {
        return pablo;
    }

    public int getSize() {
        return size;
    }

    public void setPablo(ArrayList<String> pablo) {
        this.pablo = pablo;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void Agregar(String aux ){
        pablo.add(aux);
    }

    public void CalcSize(){
        int temp=0;
        for (int i = 0; i < pablo.size()-1; i++) {
            temp+=((String)pablo.get(i)).length()+1;
        }
        size=temp;
    }
    
    
    @Override
    public String toString() {
        return "Registro{" + "pablo=" + pablo + ", size=" + size + '}';
    }

}