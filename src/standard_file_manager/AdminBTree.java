/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Mvrivera
 */
public class AdminBTree {

    private ArrayList<BTree> lista = new ArrayList();
    private File archivo = null;
    private BTree tree;
    
    public AdminBTree() {
    }

    public AdminBTree(String path) {
        archivo = new File(path);
    }

    public ArrayList<BTree> getListaPersonas() {
        return lista;
    }

    public void setLista(ArrayList<BTree> listaAlumnos) {
        this.lista = listaAlumnos;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public void setBtree(BTree a) {
        lista.add(a);
    }

    public BTree getTree() {
        return tree;
    }

    public void setTree(BTree tree) {
        this.tree = tree;
    }
    
    public void cargarArchivo() {
        try {
            lista = new ArrayList();
            if (archivo.exists()) {
                FileInputStream entrada = new FileInputStream(archivo);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {
//                    while ((temp = (BTree) objeto.readObject()) != null) {
//                        lista.add(temp);
//                    }
                    tree = (BTree) objeto.readObject();
                } catch (EOFException e) {
                }
                objeto.close();
                entrada.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void escribirArchivo() {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
//            for (BTree t : lista) {
//                bw.writeObject(t);
//            }
            bw.writeObject(this.tree);
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }
}