/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

/**
 *
 * @author Carlos Fortin
 */
public class Campo {
    private String nombre;
    private boolean isChar;
    private boolean isKey1,isKey2;
    private int size;

    public Campo() {
    }

    public Campo(String info) {
        String[] infoStr = info.split(": ");
        nombre = infoStr[0];
        if(infoStr[1].charAt(0) == 'c'){
            isChar = true;
            size = Integer.parseInt(infoStr[1].substring(5, infoStr[1].length()-2));
            char aux=infoStr[1].charAt(infoStr[1].length()-1);
            setLLaves(aux);
        }else{
            isChar = false;
            size = Integer.parseInt(infoStr[1].substring(4, infoStr[1].length()-2));
            char aux=infoStr[1].charAt(infoStr[1].length()-1);
            setLLaves(aux);
        }
    }
    
    public Campo(String nombre, boolean isChar, int size,boolean key1,boolean key2) {
        this.nombre = nombre;
        this.isChar = isChar;
        this.size = size;
        this.isKey1=key1;
        this.isKey2=key2;
        System.out.println(this.nombre + ' ' + this.isChar + ' ' + this.size);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isIsChar() {
        return isChar;
    }

    public void setIsChar(boolean isChar) {
        this.isChar = isChar;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public String getIsChar(){
        if(isChar)
            return "char";
        else
            return "int";
    }

    public boolean isIsKey1() {
        return isKey1;
    }

    public boolean isIsKey2() {
        return isKey2;
    }

    public void setIsKey1(boolean isKey1) {
        this.isKey1 = isKey1;
    }

    public void setIsKey2(boolean isKey2) {
        this.isKey2 = isKey2;
    }
    
    public void setLLaves(char aux ){
        switch(aux){
                case 'p':
                    isKey1=true;
                    isKey2=false;
                    break;
                case 'c':
                    isKey1=false;
                    isKey2=true;
                    break;
                case 'n':
                    isKey1=false;
                    isKey2=false;
                    break;  
            }
    }
    
    @Override
    public String toString(){
        if (isKey1==true){
             return nombre + ": " + getIsChar() + '[' + Integer.toString(size) +  "]p";
        }else if (isKey2==true){
             return nombre + ": " + getIsChar() + '[' + Integer.toString(size) +  "]c";
        }else{
            return nombre + ": " + getIsChar() + '[' + Integer.toString(size) +  "]n";
        }
    }
    
}
