/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cafor
 */
public class Archivo {
    private File archivo;
    private File index;
    private ArrayList<Campo> campos = new ArrayList();
    private ArrayList<Registro> registros = new ArrayList();
    private int contRegis;
    private int sizeRegis;
    private BufferedReader br;
    private int primaryKeyIndex;
    private int secondaryKeyIndex;

    public int getPrimaryKeyIndex() {
        return primaryKeyIndex;
    }

    public void setPrimaryKeyIndex(int primaryKeyIndex) {
        this.primaryKeyIndex = primaryKeyIndex;
    }

    public int getSecondaryKeyIndex() {
        return secondaryKeyIndex;
    }

    public void setSecondaryKeyIndex(int secondaryKeyIndex) {
        this.secondaryKeyIndex = secondaryKeyIndex;
    }
    

    public Archivo() {
    }

    public Archivo(File archivo) throws FileNotFoundException {
        this.archivo = archivo;
        br = new BufferedReader(new FileReader(archivo));
        try {
            String camposString = br.readLine();
            System.out.println(camposString);
            String[] cps = camposString.substring(1,camposString.length()-1).split(", ");
            
            for (String cp : cps) {
                
                campos.add(new Campo(cp));
            }
            sizeRegis = Integer.parseInt(br.readLine());
            contRegis = Integer.parseInt(br.readLine());
            System.out.println(contRegis);
            int pos = 1;
            while((camposString = br.readLine()) != null){
                System.out.println(camposString);
                registros.add(new Registro(camposString, sizeRegis, pos));
                pos++;
            }
            System.out.println(contRegis);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Archivo(File archivo, File index, int contRegis, int sizeRegis) {
        this.archivo = archivo;
        this.index = index;
        this.contRegis = contRegis;
        this.sizeRegis = sizeRegis;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public File getIndex() {
        return index;
    }

    public void setIndex(File index) {
        this.index = index;
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }
    public void addCampo(Campo camp){
        this.campos.add(camp);
    }  
    public void addRegistro(Registro regis){
        regis.setRRN(Integer.toString(contRegis+1));
        contRegis++;
        this.registros.add(regis);
        
    }
    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    public int getContRegis() {
        return contRegis;
    }

    public void setContRegis(int contRegis) {
        this.contRegis = contRegis;
    }

    public int getSizeRegis() {
        return sizeRegis;
    }

    public void setSizeRegis(int sizeRegis) {
        this.sizeRegis = sizeRegis;
    }
    
    public void calcSizeRegis(){
        sizeRegis = 0;
        for(int i = 0;i < campos.size();i++){
            sizeRegis += campos.get(i).getSize() + 1;
        }
    }
}
