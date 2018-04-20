/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasgit;

//import Modelo.Empresa;

import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Diegomendez1997
 */
public class PrincipalAlternativa extends javax.swing.JFrame {
    private static final int ANCHO = 350;
    private static final int ALTO = 350;
    
     private static String separadorGlobal = System.getProperty("file.separator");
     private static String directorioGlobal = System.getProperty("user.home");
    
    static BuscaminasGit buscaminas = new BuscaminasGit();
    static ArrayList<Usuario> partidasPrincipiante = new ArrayList<Usuario>();
    static ArrayList<Usuario> partidasIntermedio = new ArrayList<Usuario>();
    static ArrayList<Usuario> partidasExperto = new ArrayList<Usuario>();
    
    String nombreFichero = this.directorioGlobal+this.separadorGlobal+"mejoresTiemposPrincipiante";
    String nombreFichero2 = this.directorioGlobal+this.separadorGlobal+"mejoresTiemposIntermedio";
    String nombreFichero3 = this.directorioGlobal+this.separadorGlobal+"mejoresTiemposExperto";
    /**
     * Creates new form PrincipalAlternativa
     */
    public PrincipalAlternativa() {
        initComponents();
        
        //this.buscaminas = new BuscaminasGit();
        
        this.setSize(ANCHO, ALTO);
        this.setLocationRelativeTo(null);
         File ficheroPrincipiante = new File(this.nombreFichero);
       
       File ficheroIntermedio= new File(this.nombreFichero2);
       
       File ficheroExperto = new File(this.nombreFichero3);
       
       if(!ficheroPrincipiante.exists()){
          try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.directorioGlobal+this.separadorGlobal+"mejoresTiemposPrincipiante"));
            file.close();
        } catch (IOException ex) {
            System.out.println(ex);
         }
        }
       
       if(!ficheroIntermedio.exists()){
            try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.directorioGlobal+this.separadorGlobal+"mejoresTiemposIntermedio"));
            file.close();
        } catch (IOException ex) {
            System.out.println(ex);
         }
        }
       
       if(!ficheroExperto.exists()){
            try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.directorioGlobal+this.separadorGlobal+"mejoresTiemposExperto"));
            file.close();
        } catch (IOException ex) {
            System.out.println(ex);
         }
        }
        
    
    }
    public boolean restaurarBackUpMejoresTiemposPrincipiante() {
        try {
            // Apertura
            
                
    
            FileInputStream fis = new FileInputStream(nombreFichero);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            
            // Lectura
            partidasPrincipiante.clear();
            for(int i = 0; i<10; i++){
                Usuario usuario = (Usuario) ois.readObject();
                if(usuario == null){
                    i = 10;
                } else {
                    partidasPrincipiante.add(usuario);
                }
            }
            
            //this.setVisible(false);
            // Cierre
            ois.close();
            return true;
        } catch (EOFException ex) {
            //System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "'. Fin de fichero inesperado");
            
        } catch (ClassNotFoundException | IOException ex) {
            System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "' " + ex.getMessage());
        }
        return false;
    }
    public boolean restaurarBackUpMejoresTiemposIntermedio() {
        try {
            // Apertura
            
                
    
            FileInputStream fis = new FileInputStream(nombreFichero2);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            
            // Lectura
            partidasIntermedio.clear();
            for(int i = 0; i<10; i++){
                Usuario usuario = (Usuario) ois.readObject();
                if(usuario == null){
                    i = 10;
                } else {
                    partidasIntermedio.add(usuario);
                }
            }
            
            //this.setVisible(false);
            // Cierre
            ois.close();
            return true;
        } catch (EOFException ex) {
            //System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "'. Fin de fichero inesperado");
            
        } catch (ClassNotFoundException | IOException ex) {
            System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "' " + ex.getMessage());
        }
        return false;
    }
    public boolean restaurarBackUpMejoresTiemposExperto() {
        try {
            // Apertura
            
                
    
            FileInputStream fis = new FileInputStream(nombreFichero3);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            
            // Lectura
            partidasExperto.clear();
            for(int i = 0; i<10; i++){
                Usuario usuario = (Usuario) ois.readObject();
                if(usuario == null){
                    i = 10;
                } else {
                    partidasExperto.add(usuario);
                }
            }
            
            //this.setVisible(false);
            // Cierre
            ois.close();
            return true;
        } catch (EOFException ex) {
            //System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "'. Fin de fichero inesperado");
            
        } catch (ClassNotFoundException | IOException ex) {
            System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "' " + ex.getMessage());
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nuevaPartidaButton = new javax.swing.JButton();
        cargarPartidaButton = new javax.swing.JButton();
        verMejoresTiemposButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        getContentPane().setLayout(new java.awt.GridLayout(2, 2));

        nuevaPartidaButton.setText("Nueva partida");
        nuevaPartidaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaPartidaButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nuevaPartidaButton);

        cargarPartidaButton.setText("Cargar partida");
        cargarPartidaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarPartidaButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cargarPartidaButton);

        verMejoresTiemposButton.setText("Ver mejores tiempos");
        verMejoresTiemposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verMejoresTiemposButtonActionPerformed(evt);
            }
        });
        getContentPane().add(verMejoresTiemposButton);

        jButton1.setText("Ayuda");
        getContentPane().add(jButton1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaPartidaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaPartidaButtonActionPerformed
          //buscaminas = new BuscaminasGit();
          VistaNiveles vistaNiveles = new VistaNiveles();
          vistaNiveles.setVisible(true);
          //buscaminas.setVisible(true);
          //this.setVisible(false);
    }//GEN-LAST:event_nuevaPartidaButtonActionPerformed

    private void cargarPartidaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarPartidaButtonActionPerformed
            JFileChooser selectorFichero = new JFileChooser();
            selectorFichero.setDialogTitle("Selecciona Fichero BackUp");
            selectorFichero.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
            int resultado = selectorFichero.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                boolean resultadoOK = buscaminas.restaurarBackUp(selectorFichero.getSelectedFile().getAbsolutePath());
                if (resultadoOK) {
                    
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Fichero cargado correctamente", "Cargar Fichero", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Fichero NO cargado", "Cargar Fichero", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            this.setVisible(true);
            this.toBack();
    }//GEN-LAST:event_cargarPartidaButtonActionPerformed

    private void verMejoresTiemposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verMejoresTiemposButtonActionPerformed
        
            
            // Apertura
            this.restaurarBackUpMejoresTiemposPrincipiante();
            this.restaurarBackUpMejoresTiemposIntermedio();
            this.restaurarBackUpMejoresTiemposExperto();
            /*FileInputStream fis2 = new FileInputStream(nombreFichero2);
            BufferedInputStream bis2 = new BufferedInputStream(fis2);
            ObjectInputStream ois2 = new ObjectInputStream(bis2);*/
            
            // Lectura
            
            /*for(int i = 0; i<10; i++){
                Usuario usuario = (Usuario) ois2.readObject();
                if(usuario == null){
                    i = 10;
                } else {
                    partidasIntermedio.add(usuario);
                }
            }*/
            
            
            // Cierre
            
            //ois2.close();
            //VistaPartidas vistaPartidas = new VistaPartidas(partidasPrincipiante, partidasIntermedio);
            
            //vistaPartidas.setVisible(true);
        
            //System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "'. Fin de fichero inesperado");
            VistaPartidas vistaPartidas = new VistaPartidas(partidasPrincipiante, partidasIntermedio, partidasExperto);
            vistaPartidas.setVisible(true);
        
            
        
        
    }//GEN-LAST:event_verMejoresTiemposButtonActionPerformed
/**/
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
            java.util.logging.Logger.getLogger(PrincipalAlternativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalAlternativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalAlternativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalAlternativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalAlternativa().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargarPartidaButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton nuevaPartidaButton;
    private javax.swing.JButton verMejoresTiemposButton;
    // End of variables declaration//GEN-END:variables
}
