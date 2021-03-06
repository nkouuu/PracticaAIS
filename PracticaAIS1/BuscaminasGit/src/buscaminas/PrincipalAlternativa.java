/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

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
    
    /* Problema 3: Guardar las 10 mejores partidas con rutas que valgan para cualquier Sistema Operativo.
    
    3-	Guardar las 10 mejores partidas de entre todas las jugadas no fue difícil. El problema lo teníamos a la hora de 
    guardar dichas partidas en un fichero binario. Hemos tenido que comprobar con detenimiento la ruta de cada sistema
    operativo, prestándole más atención al directorio de trabajo del usuario, y la barra “absoluta” para crear ahí los ficheros
    una vez comienza el juego. O generarlos si estos ficheros no estuvieran ya creados.
    
    
    */
    
    /* Usamos una variable que detecte el separador Global del sistema operativo actual junto con otra variable que sea el directorio de
    trabajo del usuario.
    */
     private static String separadorGlobal = System.getProperty("file.separator");
     private static String directorioGlobal = System.getProperty("user.home");
    
    static Buscaminas buscaminas = new Buscaminas();
    
    /*Declaración de arrayLists para almacenar los mejores tiempos de los usuarios.*/
    
    static ArrayList<Usuario> partidasPrincipiante = new ArrayList<Usuario>();
    static ArrayList<Usuario> partidasIntermedio = new ArrayList<Usuario>();
    static ArrayList<Usuario> partidasExperto = new ArrayList<Usuario>();
    
    /*
    
    Declaramos 3 variables para los 3 ficheros con sus rutas ya cogidas de cualquiera sistema operativo del que disponga el usuario.
    Usaremos las variables mas abajo para poder hacer una copia de seguridad de los mejores tiempos.
    
    */
    
    String nombreFichero = this.directorioGlobal+this.separadorGlobal+"mejoresTiemposPrincipiante"; /*Partida principiante */
    String nombreFichero2 = this.directorioGlobal+this.separadorGlobal+"mejoresTiemposIntermedio"; /*Partida intermedio*/
    String nombreFichero3 = this.directorioGlobal+this.separadorGlobal+"mejoresTiemposExperto"; /*Partida experto*/
    /**
     * Creates new form PrincipalAlternativa
     */
    public PrincipalAlternativa() {
        initComponents();
        
        this.setSize(ANCHO, ALTO);
        this.setLocationRelativeTo(null);
        
        /*Hay que darse cuenta que si el usuario no tiene partidas ya hechas, ni tiempos que cargar en los ArrayList, el programa cuando se inicia
        crea por defecto los 3 ficheros VACÍOS, para que no de errores cuando se quiera crearlos mas adelante.
        */
        
         File ficheroPrincipiante = new File(this.nombreFichero);
       
       File ficheroIntermedio= new File(this.nombreFichero2);
       
       File ficheroExperto = new File(this.nombreFichero3);
       
       /*Lo que se realiza en esta parte del código, son 3 comprobaciones, si no existe el fichero, lo creo nada mas empezar*/
       
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
    
    /* A continuación , usamos 3 métodos para restaurar una partida en los ArrayList de VistaPartidas.
    
        y utilizamos las varibles declaras al principio del código para no tener que volver a repetir rutas.
    
    */
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaPartidaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaPartidaButtonActionPerformed
          //buscaminas = new Buscaminas();
          VistaNiveles vistaNiveles = new VistaNiveles();
          vistaNiveles.setVisible(true);
          //buscaminas.setVisible(true);
          //this.setVisible(false);
    }//GEN-LAST:event_nuevaPartidaButtonActionPerformed

    private void cargarPartidaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarPartidaButtonActionPerformed
           
        /*A la hora de cargar cualquier partida, dejamos la opción al usuario de que pueda seleccionar, dentro de su ordenador,
        que partida desea cargar, para ello habilitamos un selector de ficheros, el cual lee el archivo que se selecciona, y nos encargamos de 
        restaurar la partida llamando al método restaurarBackUp.
        */
        
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
        
            
            /*A la hora de ver los mejores tiempos, simplemente estamos llamando a la ventana VistaPartidas, donde le vamos a pasar los Arraylist declarados arriba
            VistaPartidas se encargará de recoger los 3 arrayLists y utilizarlos para rellenar los JList convenientes.
        */
            
            this.restaurarBackUpMejoresTiemposPrincipiante();
            this.restaurarBackUpMejoresTiemposIntermedio();
            this.restaurarBackUpMejoresTiemposExperto();
            
            VistaPartidas vistaPartidas = new VistaPartidas(partidasPrincipiante, partidasIntermedio, partidasExperto);
            vistaPartidas.setVisible(true);
        
            
        
        
    }//GEN-LAST:event_verMejoresTiemposButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       VistaAyuda vistaAyuda = new VistaAyuda();
       vistaAyuda.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
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
