/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussines;

import Data.values;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Marco
 */
public class JavaFileManager {
    
    
    private ScriptManager file;
    private ScriptManager fileFactory;
    File archivo;
    File archivoFactory;
    FileWriter escribir;
    FileWriter escribirFactory;
    JTable table;
    String URL;
    
    private String titulo;
    public JavaFileManager(String titulo,String rootPath,String javapath,String factoryPath,String author,String URL,JTable table ) {
       this.titulo = (titulo.substring(0,1).toUpperCase()+titulo.substring(1));
       file= new ScriptManager(this.titulo, "java", rootPath, javapath,author);
       fileFactory= new ScriptManager(this.titulo, "java", rootPath, factoryPath,author); 
       archivo = new File(javapath+File.separator+file.absoluteName());
       archivoFactory = new File(factoryPath/*+File.separator+fileFactory+".java"*/);
       this.URL = URL;
       this.table = table;
       abrirArchivo();
       write_header();
    }
    
    public final void abrirFactory() {
        try {
            escribirFactory = new FileWriter(archivoFactory, true);
        } catch (IOException ex) {
            Logger.getLogger("error al abrir en archivo");
            System.out.println(ex);
        }
    }

    public void escribirFactory(String texto) {
        String texto2="";
        String txt="";
        try {
            FileReader lector = new FileReader(archivoFactory.getAbsolutePath());
            BufferedReader contenido = new BufferedReader(lector);
            int i=0;
            while ((txt = contenido.readLine()) != null) {
                i++;
                if (!"}".equals(txt)) {
                    texto2 = texto2 + txt+'\n';
                }    
            }
            texto2 = texto2+ texto;
            texto2 = fileFactory.addImport(texto2,"import "+file.getPackeage()+"."+getTitulo()+";");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivoFactory.getAbsolutePath()));
                bw.write("");
                bw.write(texto2);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger("error al escribir en archivo");
            }
        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            Logger.getLogger("error al leer y borrar archivo");
        }
        
        
    }
    
    public void cerrarFactory() {
        try {
            //escribirFactory.write(fileFactory.cerrar_clase());
            escribirFactory.flush();
            escribirFactory.close();
        } catch (IOException ex) {
            Logger.getLogger("error al cerrar el archivo");
        }
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
                boolean key=(boolean)table.getValueAt(i, 4); 
                boolean button=(boolean)table.getValueAt(i, 3);
                boolean select;
                try {
                    select=(boolean)table.getValueAt(i,5);
                } catch (Exception e) {
                    select=false;
                }
                escribir.write(file.build_line(name, target, type, key, button, select));
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

    public ScriptManager getFile() {
        return file;
    }

    public void setFile(ScriptManager file) {
        this.file = file;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }
    public String declarar_variable(String var_name, String var_type, String var_path) {
        String var = "private ElementPath " + var_name + " = new ElementPath(ElementPath."+var_type+",\"" + var_path + "\");" + values.br;
        return var;
    }

    public void pushToFactory() {
        abrirFactory();
        escribirFactory(fileFactory.declararEnPageFactory());
        cerrarFactory();
    }

    public void pushToPageObject() {
        abrirArchivo();
        write_header();
        write_variables(table);
        write_constructor(URL);
        write_metodos(table);
        write_validatePage(table);
        cerrarArchivo();
    }
    
    
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

    
}
