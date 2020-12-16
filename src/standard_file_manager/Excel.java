/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    private ArrayList<Campo> campos;
    private ArrayList<Registro> registro;
    private String ruta;
    private String nombre;

    public Excel(ArrayList<Campo> campos, ArrayList<Registro> registros, String nombre, String ruta) {
        this.campos = campos;
        this.registro = registros;
        this.ruta = ruta;
        this.nombre = nombre;
    }

    public void CrearArchivo() {
        
        
        String rutaArchivo = ruta + "/"+ nombre + ".xlsx";
        String hoja = "Hoja1";
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        //cabecera de la hoja de excel
        String[] header = new String[campos.size() + 1];
        header[0] = "ID";
        for (int i = 0; i < campos.size(); i++) {
            header[i + 1] = campos.get(i).getNombre();
        }
        //contenido de la hoja de excel
        String[][] document = new String[registro.size()][campos.size()+1];
        System.out.println("Document Fila: "+registro.size());
        System.out.println("Document Columna: "+campos.size()+1);
        for (int i = 0; i < document.length; i++) {
            for (int j = 0; j < document[0].length; j++) {
                if (j == 0) {
                    document[i][j] = registro.get(i).getRRN();
                } else {
                    try{
                        document[i][j] = registro.get(i).getPablo().get(j-1);
                    }catch(Exception e){
                        document[i][j] = "";
                    }
                }
            }
        }
        for (int i = 0; i < document.length; i++) {
            for (int j = 0; j < document[0].length; j++) {
                System.out.print("["+document[i][j]+"]");
            }
            System.out.println("");
        }
        //poner negrita a la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);

        try {
            //generar los datos para el documento
            for (int i = 0; i <= document.length; i++) {
                XSSFRow row = hoja1.createRow(i);//se crea las filas
                for (int j = 0; j < header.length; j++) {
                    if (i == 0) {//para la cabecera
                        XSSFCell cell = row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                        cell.setCellStyle(style); // se añade el style crea anteriormente 
                        cell.setCellValue(header[j]);//se añade el contenido					
                    } else {//para el contenido
                        XSSFCell cell = row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                        cell.setCellValue(document[i - 1][j]); //se añade el contenido
                    }
                }
            }
        } catch (Exception e) {

        }

        File file;
        file = new File(rutaArchivo);
        try (FileOutputStream fileOuS = new FileOutputStream(file)) {
            if (file.exists()) {// si el archivo existe se elimina
                file.delete();
                System.out.println("Archivo eliminado");
            }
            libro.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            System.out.println("Archivo Creado");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
