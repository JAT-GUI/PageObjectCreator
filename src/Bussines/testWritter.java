/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussines;

import Data.values;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Brayam Machicado
 */
public class testWritter implements MyFile {

    String type;
    String name;
    String header;
    private String body;
    private String footer;
    private String author;
    private final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    private ScriptManager file;
    File archivo;
    FileWriter escribir;
    String path;
    String titulo;
    String packeage;
    public testWritter(String titulo, String rootPath, String testSource, String author) {
        //this.path = javapath;
        //file = new ScriptManager(titulo, "java", rootPath, javapath, author);
        //archivo = new File(javapath + File.separator + file.absoluteName());
        //abrirArchivo();
        //write_header();
        this.author= author;
        this.titulo= titulo;
        this.packeage = obtener_package(rootPath, testSource);
    }

    public final void abrirArchivo() {
        try {
            escribir = new FileWriter(archivo, true);
        } catch (IOException ex) {
            Logger.getLogger("error al abrir en archivo");
        }
    }

    public void escribirArchivo(String texto) {
        try {
            escribir.write(texto + values.br);

        } catch (IOException ex) {
            Logger.getLogger("error al escribir en archivo");
        }

    }

//    public final void write_header() {
//        try {
//            escribir.write(file.getHeader() + values.br);
//
//        } catch (IOException ex) {
//            Logger.getLogger("error al escribir cabecera en archivo: " + file.absoluteName());
//        }
//
//    }
    public String writeHeader() {
        String className = this.titulo.replace(" ", "_");
        this.header = 
"package "+this.packeage+";\n" +
"\n" +
"import org.junit.Assert;\n" +
"import mfp.factory.webFactoryPattern;\n" +
"import org.openqa.selenium.WebDriver;\n" +
"import org.testng.annotations.Test;\n" +
"public class "+className+"\n" +
"\n" +
"{\n" +
"	public String baseUrl=\"\";//add your base Url\n" +
"	webFactoryPattern webDriver = new webFactoryPattern(baseUrl);\n" +
"	public int waitTime = 3000;\n" +
"	WebDriver driver = webFactoryPattern.buildBrowser(webFactoryPattern.Browser_Chrome);\n";
        String toReturn = this.header;
        return toReturn;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getFooter() {
        return footer;
    }

    @Override
    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public String absoluteName() {
        return name + values.pto + type;
    }

    @Override
    public String build_line(String name_property, String target, String type, boolean key, boolean button, boolean select) {

//        String methods = getEnabledFunction(name_property) + values.br + getDisplayedFunction(name_property) + values.br;
//        if (button) {
//            methods = methods + getClickMethod(name_property) + values.br;
//        }
//        if (key) {
//            methods = methods + getPutMethod(name_property) + values.br;
//        }
//        return methods;
        return "";
    }

    private String obtener_package(String path) {
//        String packg = "";
//        int r = path.indexOf("src")+4;
//        packg = path.substring(r);
//        packg = packg.replace('\\', '.');
//
//        return " " + packg + ";"+values.br;
        path = path.replace('\\', '.');
        return path;
    }

    public String iniciar_clase() {
        return values.kwPublic + values.kwClass + name + values.lla + values.br;

    }

    public String cerrar_clase() {
        return values.br + " " + values.llc;
    }

    public String createStep(String stepName, String Description, String Expected) {
        stepName = stepName.replace(" ", "_");
        String allString = 
"\n       public void "+stepName+"(){\n" +
"		try {\n" +
"			//all code here\n" +
"			Thread.sleep(waitTime);\n" +
"			System.out.println(\""+Description+" : pass\");\n";
        
        if(!"".equals(Expected)){
        allString = allString +
"			//all assertions group for "+Expected +"\n"+
"			//Assert.assertTrue(true);\n";
        }
                
        allString = allString+
"		} catch (InterruptedException e) {\n" +
"			System.out.println(\""+Description+" : fail\");\n" +
"			e.printStackTrace();\n" +
"		}\n" +
"	}\n";
        return allString;
    }

    
    
    public String armarTest() {
        String testName = this.titulo;
        testName = "run"+testName.replace(" ", "_");
        String testBody = //header+
"	@Test\n" +
"	// This is TestNG annotation\n" +
"	public void "+testName+"() \n" +
"	{	\n" +
"		System.out.println(webDriver.getGooglePO().getBaseUrl());\n" +
"		//setup all steps of the test in order\n" +
"		\n" +
"		driver.close();\n" +
"	}"
                +cerrar_clase();
        return testBody;
    }
    
    private String obtener_package(String rootPath, String destinyPath) {
        String packageDestiny;
        //packageDestiny = destinyPath.split(rootPath)[1];
        packageDestiny = destinyPath.substring(rootPath.length());
        char ch=System.getProperty("os.name").contains("Mac")?'/':'\\';
        packageDestiny = packageDestiny.replace(ch, '.');
        //String pk[] = packageDestiny.split(".");
        String toReturn = "";
        for (int i = 2; i < 100; i++) {
            try {
                toReturn = toReturn+"."+packageDestiny.split("\\.")[i];
            } catch (Exception e) {
            }
        }
        toReturn = toReturn.substring(1);
        if (toReturn.contains("java")) {
            //toReturn = toReturn.split("\\.java")[0];
            toReturn = toReturn.substring(0, toReturn.lastIndexOf("."));
            toReturn = toReturn.substring(0, toReturn.lastIndexOf("."));
        }
        return toReturn;
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JATGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JATGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JATGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JATGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String toWrite = "";
                //testWritter testt = new testWritter("", "", "");
                //toWrite = toWrite +testt.writeHeader("wrong login to google");
                //toWrite = toWrite +testt.createStep("step 1", "user go to google", "user should be able to reach theinput element to search something");
                //toWrite = toWrite +testt.armarTest("wrong login to google", "", "", "", "");
                System.out.println(toWrite);
            }
        });
    }

}
