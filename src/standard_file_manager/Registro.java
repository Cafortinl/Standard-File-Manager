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
    private String RRN;
    private int size;

    public Registro() {
    }

    public Registro(int size) {
        this.size = size;
    }

    public Registro(String info, int rSize, int pos){
        String[] campos = info.split("\\|");
        int s = 8;
        //System.out.println(campos.length);
        //ID=campos[9];
        
        for(int i = 0; i < campos.length;i++){
            pablo.add(campos[i]);
            s += campos[i].length() + 1;
        }
        size = rSize;
        //ID=campos[0];
        RRN = Integer.toString(pos);
        System.out.println(RRN);
    }
    
    public ArrayList<String> getPablo() {
        return pablo;
    }

    public int getSize() {
        return size;
    }

    public String getRRN() {
        return RRN;
    }

    public void setRRN(String ID) {
        this.RRN = ID;
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
        String output = "";
        for (String st : pablo) {
            output += st + '|';
        }
        while(output.length() < size){
            output += '*';
        }
        return output;
    }

}