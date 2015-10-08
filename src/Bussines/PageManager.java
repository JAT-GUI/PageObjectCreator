/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussines;

import Data.values;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Marco
 */
public class PageManager {
    
    
    private JavaFile file;
    File archivo;
    FileWriter escribir;
    
    public PageManager(String titulo,String rootPath,String javapath,String propertiesPath,String author) {
       file= new JavaFile(titulo, "java", rootPath, javapath,author);
       archivo = new File(javapath+File.separator+file.absoluteName());
       abrirArchivo();
       write_header();
    }
    
    
    public final void abrirArchivo() {
        try {
            escribir = new FileWriter(archivo, true);
        } catch (IOException ex) {
            Logger.getLogger("error al abrir en archivo");
            System.out.println(ex);
        }
    }

    public void escribirArchivo(String texto) {
        try {
            escribir.write(texto + values.br);

        } catch (IOException ex) {
            Logger.getLogger("error al escribir en archivo");
        }

    }

    public final void write_header() {
        try {
            escribir.write(file.getHeader() + values.br);

        } catch (IOException ex) {
            Logger.getLogger("error al escribir cabecera en archivo: " + file.absoluteName());
            System.out.println(ex);
        }

    }
    

    public final void write_variables(JTable table) {
        try {
            for (int i = 0; i < table.getRowCount(); i++) {
                
                escribir.write( declarar_variable((String)table.getValueAt(i, 0),(String)table.getValueAt(i, 2),(String)table.getValueAt(i, 1)));
                
            }
            

        } catch (IOException ex) {
            Logger.getLogger("error al escribir cabecera en archivo: " + file.absoluteName());
        }

    }
    
    public final void write_constructor(String URL){
        try {
            escribir.write(file.armar_constructor(URL));
        } catch (IOException ex) {
            Logger.getLogger("error al escribir constructor: " + file.absoluteName());
        }
        
    }

     public void write_validatePage(JTable table){
        try {
               escribir.write(file.getValidateFunction(table));
                
            
            
        } catch (IOException ex) {
            Logger.getLogger("error al escribir constructor: " + file.absoluteName());
        }
         
     }
     public final void write_metodos(JTable table){
        try {
            for (int i = 0; i < table.getRowCount(); i++) {
                String name=(String)table.getValueAt(i, 0);String target=(String)table.getValueAt(i, 1);String type=(String)table.getValueAt(i, 2);
                boolean key=(boolean)table.getValueAt(i, 4); boolean button=(boolean)table.getValueAt(i, 3);
                escribir.write(file.build_line(name, target, type, key, button));
                escribir.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger("error al escribir constructor: " + file.absoluteName());
        }
        
    }
    public void cerrarArchivo() {
        try {
            escribir.write(file.cerrar_clase());
            escribir.flush();
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger("error al cerrar el archivo");
        }
    }

    public JavaFile getFile() {
        return file;
    }

    public void setFile(JavaFile file) {
        this.file = file;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }
    public String declarar_variable(String var_name, String var_type, String var_path) {
        String var = "private ElementPath " + var_name + "= new ElementPath(ElementPath."+var_type+",\"" + var_path + "\");" + values.br;
        return var;
    }
    

    
}
