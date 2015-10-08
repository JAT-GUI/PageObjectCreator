/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author danzel
 */
public class JatFilesManager {
    static public final String PO_Link = "PO_Link";
    static public final String Factory_Link = "Factory_Link";
    static public final String Test_Link = "Test_Link";
    private String rootPath;
    private String configFile = ".jat.txt";
    private String logSource = "src\\Data\\log.txt";
    
    public JatFilesManager(String rootPath) {
        this.rootPath = rootPath;
    }

    public void reWriteConfigFile(String pageObjectFactory, String pageObject, String test) {
        try {
            PrintWriter writer = new PrintWriter(this.rootPath+"/"+this.configFile);
            writer.println(
            JatFilesManager.Factory_Link+":"+pageObjectFactory+";"+
            JatFilesManager.PO_Link+":"+pageObject+";"+
            JatFilesManager.Test_Link+":"+test+";");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String readConfigFile(){
        String file="";
        try {
            FileReader lector = new FileReader(this.rootPath+"/"+this.configFile);
            BufferedReader contenido = new BufferedReader(lector);
            file = file + contenido.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        return file;
    }

    public String readLog(String linkType) {
        String texto = "";
        try {
            FileReader lector = new FileReader(logSource);
            BufferedReader contenido = new BufferedReader(lector);
            String link = "";
            while ((texto = contenido.readLine()) != null) {
                if (texto.contains(linkType)) {
                    link = contenido.readLine();
                }
            }
            System.out.println(texto + ": to send");
            return link;
        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            System.out.println("Error al leer");
            System.out.println(e.toString());
            return "";
        }
    }

    public boolean createJATText() {
        try {
            PrintWriter writer = new PrintWriter(rootPath+"/"+configFile, "UTF-8");
            writer.println(JatFilesManager.Factory_Link+":;"+JatFilesManager.PO_Link+":;"+JatFilesManager.Test_Link+":;");
            writer.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public int getLenght() {
        int i = 0;
        try {
            FileReader lector = new FileReader(logSource);
            BufferedReader contenido = new BufferedReader(lector);
            while ((contenido.readLine()) != null) {
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error al leer");
            System.out.println(e.toString());
            return 0;
        }
        System.out.println(i + ": in the log");
        return i;
    }
    
    
    // 2 
    public void writeNewLog(String rootPath) {
        try {
            this.rootPath = rootPath;
            String path = this.rootPath.split(configFile)[0] + '\n';
            File archivo = new File(logSource);
            System.out.println("log Updated");
            try (FileWriter escribir = new FileWriter(archivo, true)) {
                escribir.write(path);
            }
        } catch (Exception e) {
            System.out.println("Error al escribir");
            System.out.println(e.toString());
        }
    }

    public String readLog(int logPos) {
        String texto="";
        try {
            FileReader lector = new FileReader(logSource);
            BufferedReader contenido = new BufferedReader(lector);
            int i=0;
            while ((texto = contenido.readLine()) != null) {
                i++;
                if (i==logPos) {
                    System.out.println(texto+": to send");
                    return texto;
                }
                System.out.println(texto+": to avoid");
            }
        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            System.out.println("Error al leer");
            System.out.println(e.toString());
            return "";
        }
        return "";
    }
}
