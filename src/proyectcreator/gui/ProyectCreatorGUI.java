/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectcreator.gui;

import Bussines.JatFilesManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author Danzelord
 */
public class ProyectCreatorGUI extends javax.swing.JFrame {
    JFileChooser buscador;
    //private String configFile = ".jat.txt";
    private static final int BUFFER_SIZE = 1024;
    /**
     * Creates new form ProyectCreatorGUI
     */
    public ProyectCreatorGUI(/*String configFile*/) {
        initComponents();
        buscador= new JFileChooser();
        buscador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        buscador.setDialogType(JFileChooser.SAVE_DIALOG);
        //this.configFile = configFile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        savePathButton = new javax.swing.JButton();
        pathInputText = new javax.swing.JTextField();
        pathLabel = new javax.swing.JLabel();
        appNameInputText = new javax.swing.JTextField();
        appLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        savePathButton.setText("SAVE PATH");
        savePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePathButtonActionPerformed(evt);
            }
        });

        pathLabel.setText("Choose a Path");

        appNameInputText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appNameInputTextActionPerformed(evt);
            }
        });

        appLabel.setText("Name of the app Web");

        searchButton.setText("SEARCH");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        closeButton.setText("CLOSE");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(appLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(appNameInputText, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(savePathButton))
                            .addComponent(pathInputText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appNameInputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathInputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathLabel)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savePathButton)
                    .addComponent(closeButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void savePathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePathButtonActionPerformed
        JatFilesManager declarationHistory = new JatFilesManager(pathInputText.getText());
        declarationHistory.createJATText();
        JatFilesManager repositoyHistory = new JatFilesManager(null);
        repositoyHistory.writeNewLog(pathInputText.getText());
        MultiCreatorGUI multiCreatorGUI =  new MultiCreatorGUI(pathInputText.getText()/*, configFile*/);
        multiCreatorGUI.setVisible(true);
        dispose();
    }//GEN-LAST:event_savePathButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        int seleccion = buscador.showSaveDialog(buscador);
        if(seleccion==JFileChooser.APPROVE_OPTION){
            File origen = buscador.getSelectedFile();
            pathInputText.setText(origen.getAbsolutePath());
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        JATGUI jatgui = new JATGUI();
        jatgui.setVisible(true);
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void appNameInputTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appNameInputTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appNameInputTextActionPerformed

    public static void CopiarDirectorio(File dirOrigen, File dirDestino) throws Exception { 
	try {
		if (dirOrigen.isDirectory()) { 
			if (!dirDestino.exists())
				dirDestino.mkdir(); 
 
			String[] hijos = dirOrigen.list(); 
			for (int i=0; i < hijos.length; i++) { 
				CopiarDirectorio(new File(dirOrigen, hijos[i]), 
					new File(dirDestino, hijos[i])); 
			} // end for
		} else { 
			Copiar(dirOrigen, dirDestino); 
		} // end if
	} catch (Exception e) {
		throw e;
	} // end try
} // end CopiarDirectorio
 
public static void Copiar(File dirOrigen, File dirDestino) throws Exception { 
 
	InputStream in = new FileInputStream(dirOrigen); 
	OutputStream out = new FileOutputStream(dirDestino); 
 
	byte[] buffer = new byte[1024];
	int len;
 
	try {
		// recorrer el array de bytes y recomponerlo
		while ((len = in.read(buffer)) > 0) { 
			out.write(buffer, 0, len); 
		} // end while
		out.flush();
	} catch (Exception e) {
		throw e;
	} finally {
		in.close(); 
		out.close(); 
	} // end ty
} // end Copiar

public static void Copiar(String dirOrigen, String dirDestino) throws Exception { 
	InputStream in = new FileInputStream(dirOrigen); 
	OutputStream out = new FileOutputStream(dirDestino); 
 
	byte[] buffer = new byte[1024];
	int len;
 
	try {
		// recorrer el array de bytes y recomponerlo
		while ((len = in.read(buffer)) > 0) { 
			out.write(buffer, 0, len); 
		} // end while
		out.flush();
	} catch (Exception e) {
		throw e;
	} finally {
		in.close(); 
		out.close(); 
	} // end ty
} // end Copiar

 
        public void Zippear(String pFile, String pZipFile) throws Exception {
		// objetos en memoria
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ZipOutputStream zipos = null;
 
		// buffer
		byte[] buffer = new byte[BUFFER_SIZE];
		try {
			// fichero a comprimir
			fis = new FileInputStream(pFile);
			// fichero contenedor del zip
			fos = new FileOutputStream(pZipFile);
			// fichero comprimido
			zipos = new ZipOutputStream(fos);
			ZipEntry zipEntry = new ZipEntry(pFile);
			zipos.putNextEntry(zipEntry);
			int len = 0;
			// zippear
			while ((len = fis.read(buffer, 0, BUFFER_SIZE)) != -1)
				zipos.write(buffer, 0, len);
			// volcar la memoria al disco
			zipos.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			// cerramos los files
			zipos.close();
			fis.close();
			fos.close();
		} // end try
	} // end Zippear
 
	public void UnZip(String pZipFile, String pFile) throws Exception {
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		ZipInputStream zipis = null;
		FileOutputStream fos = null;
 
		try {
			fis = new FileInputStream(pZipFile);
			zipis = new ZipInputStream(new BufferedInputStream(fis));
			if (zipis.getNextEntry() != null) {
				int len = 0;
				byte[] buffer = new byte[BUFFER_SIZE];
				fos = new FileOutputStream(pFile);
				bos = new BufferedOutputStream(fos, BUFFER_SIZE);
 
				while  ((len = zipis.read(buffer, 0, BUFFER_SIZE)) != -1)
					bos.write(buffer, 0, len);
				bos.flush();
			} else {
				throw new Exception("El zip no contenia fichero alguno");
			} // end if
		} catch (Exception e) {
                    System.out.println(e.toString());
                    throw e;                     
		} finally {
			bos.close();
			zipis.close();
			fos.close();
			fis.close();
		} // end try
	} // end UnZip
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProyectCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProyectCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProyectCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProyectCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProyectCreatorGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appLabel;
    private javax.swing.JTextField appNameInputText;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField pathInputText;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JButton savePathButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}