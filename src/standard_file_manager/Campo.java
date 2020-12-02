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
    private int size;

    public Campo() {
    }

    public Campo(String info) {
        String[] infoStr = info.split(": ");
        nombre = infoStr[0];
        if(infoStr[1].charAt(0) == 'c'){
            isChar = true;
            size = Integer.parseInt(infoStr[1].substring(5, infoStr[1].length()-1));
        }else{
            isChar = false;
            size = Integer.parseInt(infoStr[1].substring(4, infoStr[1].length()-1));
        }
    }
    
    public Campo(String nombre, boolean isChar, int size) {
        this.nombre = nombre;
        this.isChar = isChar;
        this.size = size;
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
    
    @Override
    public String toString(){
        return nombre + ": " + getIsChar() + '[' + Integer.toString(size) + ']';
    }
    
}
