/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasgit;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
 
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
public class BuscaminasGit extends JFrame implements ActionListener, MouseListener, Serializable{
    Thread hilo;
    JMenuBar barraMenu;
    JMenu menu;
    JMenuItem mi1,mi2,mi3;
    JTextField minasRestantes;
    JLabel tiempoTranscurrido ;
    JLabel numeroMinas;
    Cronometro cronometro;
    int nomines = 80;//number of mines
    int nominesAux;
    int perm[][];
    String tmp;
    BuscaminasGit buscaminas;
    boolean found = false;
    int row;
    int column;
    int guesses[][];
    JButton b[][];
    int[][] mines;
    boolean allmines;
    int n = 30;
    int m = 30;
    int deltax[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    int deltay[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    double starttime;
    double endtime;
    double actualtime = System.nanoTime();
    public BuscaminasGit(int n, int m, int nomines){
        this.n = n;
        this.m = m;
        this.nomines = nomines;
        nominesAux = nomines;
        //new BuscaminasGit();
        numeroMinas= new JLabel("Minas Restantes: ");
        tiempoTranscurrido =new JLabel("Tiempo de juego: ");
        menu = new JMenu("Menu");
        Font font = new Font("Calibri", 3, 15);
        menu.setFont(font);
        //tiempoTranscurrido.setBackground(OPAQUE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        minasRestantes = new JTextField(nomines);
        minasRestantes.setEditable(false);
        cronometro=new Cronometro();
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        menu.setBackground(Color.black);
        barraMenu.add(menu);
        mi1 = new JMenuItem("Reiniciar partida");
        //mi1.addActionListener(this);
        mi1.addActionListener((ActionEvent evento) -> {
            this.setVisible(false);
            new BuscaminasGit(n,m,nominesAux);
            
        });
        menu.add(mi1);
        mi1.addActionListener((ActionEvent evento) -> {
            this.setVisible(false);
            new BuscaminasGit(n,m,nominesAux);
            
        });
        mi2 = new JMenuItem("Guardar partida");

        
        mi2.addActionListener((ActionEvent evento) -> {
            //hilo.destroy();
            cronometro.pararCronometro();

            JFileChooser selectorFichero = new JFileChooser();
            selectorFichero.setDialogTitle("Selecciona Fichero BackUp");
            selectorFichero.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
            int resultado = selectorFichero.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {

            boolean resultadoOK = this.hacerBackUp(selectorFichero.getSelectedFile().getAbsolutePath());
            if (resultadoOK) {
                JOptionPane.showMessageDialog(this, "Fichero guardado correctamente", "Guardar Fichero", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Fichero NO guardado", "Guardar Fichero", JOptionPane.ERROR_MESSAGE);
            }
        }  
        });
        menu.add(mi2);
        mi3 = new JMenuItem("Cargar partida");
        //mi3.addActionListener(this);
        mi3.addActionListener((ActionEvent evento) -> {
            JFileChooser selectorFichero = new JFileChooser();
            selectorFichero.setDialogTitle("Selecciona Fichero BackUp");
            selectorFichero.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
            int resultado = selectorFichero.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                boolean resultadoOK = this.restaurarBackUp(selectorFichero.getSelectedFile().getAbsolutePath());
                if (resultadoOK) {
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Fichero cargado correctamente", "Cargar Fichero", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Fichero NO cargado", "Cargar Fichero", JOptionPane.ERROR_MESSAGE);
            }
        }
        }
            
        );
        menu.add(mi3);
        menu.setMinimumSize(new Dimension(50,0));
        barraMenu.setLayout(new BoxLayout(barraMenu, BoxLayout.X_AXIS));
        cronometro.iniciarCronometro();
        hilo=new Thread(cronometro);
        hilo.start();
        barraMenu.add(Box.createRigidArea(new Dimension(400,0)));  
        barraMenu.add(tiempoTranscurrido);
        cronometro.getTiempo().setOpaque(false);
        barraMenu.add(cronometro.getTiempo());
        barraMenu.add(Box.createRigidArea(new Dimension(450,0)));
        barraMenu.add(numeroMinas);
        barraMenu.add(minasRestantes);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        perm = new int[n][m];
        boolean allmines = false;
        guesses = new int [n+2][m+2];
        mines = new int[n+2][m+2];
        b = new JButton [n][m];
        setLayout(new GridLayout(n,m));
        for (int y = 0;y<m+2;y++){
            mines[0][y] = 3;
            mines[n+1][y] = 3;
            guesses[0][y] = 3;
            guesses[n+1][y] = 3;
        }
        for (int x = 0;x<n+2;x++){
            mines[x][0] = 3;
            mines[x][m+1] = 3;
            guesses[x][0] = 3;
            guesses[x][m+1] = 3;
        }
        do {
            int check = 0;
            for (int y = 1;y<m+1;y++){
                for (int x = 1;x<n+1;x++){
                    mines[x][y] = 0;
                    guesses[x][y] = 0;
                }
            }
            for (int x = 0;x<nomines;x++){
                mines [(int) (Math.random()*(n)+1)][(int) (Math.random()*(m)+1)] = 1;
            }
            for (int x = 0;x<n;x++){
                for (int y = 0;y<m;y++){
                if (mines[x+1][y+1] == 1){
                        check++;
                    }
                }
            }
            if (check == nomines){
                allmines = true;
            }
        }while (allmines == false);
        for (int y = 0;y<m;y++){
            for (int x = 0;x<n;x++){
                if ((mines[x+1][y+1] == 0) || (mines[x+1][y+1] == 1)){
                    perm[x][y] = perimcheck(x,y);
                }
                b[x][y] = new JButton("?");
                b[x][y].addActionListener(this);
                b[x][y].addMouseListener(this);
                add(b[x][y]);
                b[x][y].setEnabled(true);
                minasRestantes.setText(String.valueOf(nomines));
            }//end inner for
        }//end for
        pack();
        setVisible(true);
        for (int y = 0;y<m+2;y++){
            for (int x = 0;x<n+2;x++){
                System.out.print(mines[x][y]);
            }
        System.out.println("");}
        starttime = System.nanoTime();
        
        /*add(prueba);
        prueba.addActionListener(this);*/
    }//end constructor Mine()
    
    public BuscaminasGit(int n, int m, int nomines, int [][] mines, int guesses[][], int perm[][]){
        this.n = n;
        this.m = m;
        this.nomines = nomines;
        nominesAux = nomines;
        //new BuscaminasGit();
        numeroMinas= new JLabel("Minas Restantes: ");
        tiempoTranscurrido =new JLabel("Tiempo de juego: ");
        menu = new JMenu("Menu");
        Font font = new Font("Calibri", 3, 15);
        menu.setFont(font);
        //tiempoTranscurrido.setBackground(OPAQUE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        minasRestantes = new JTextField(nomines);
        minasRestantes.setEditable(false);
        cronometro=new Cronometro();
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        menu.setBackground(Color.black);
        barraMenu.add(menu);
        mi1 = new JMenuItem("Reiniciar partida");
        //mi1.addActionListener(this);
        mi1.addActionListener((ActionEvent evento) -> {
            this.setVisible(false);
            new BuscaminasGit(n,m,nominesAux);
            
        });
        menu.add(mi1);
        mi1.addActionListener((ActionEvent evento) -> {
            this.setVisible(false);
            new BuscaminasGit(n,m,nominesAux);
            
        });
        mi2 = new JMenuItem("Guardar partida");

        
        mi2.addActionListener((ActionEvent evento) -> {
            //hilo.destroy();
            cronometro.pararCronometro();

            JFileChooser selectorFichero = new JFileChooser();
            selectorFichero.setDialogTitle("Selecciona Fichero BackUp");
            selectorFichero.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
            int resultado = selectorFichero.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {

            boolean resultadoOK = this.hacerBackUp(selectorFichero.getSelectedFile().getAbsolutePath());
            if (resultadoOK) {
                JOptionPane.showMessageDialog(this, "Fichero guardado correctamente", "Guardar Fichero", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Fichero NO guardado", "Guardar Fichero", JOptionPane.ERROR_MESSAGE);
            }
        }  
        });
        menu.add(mi2);
        mi3 = new JMenuItem("Cargar partida");
        //mi3.addActionListener(this);
        mi3.addActionListener((ActionEvent evento) -> {
            JFileChooser selectorFichero = new JFileChooser();
            selectorFichero.setDialogTitle("Selecciona Fichero BackUp");
            selectorFichero.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
            int resultado = selectorFichero.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                boolean resultadoOK = this.restaurarBackUp(selectorFichero.getSelectedFile().getAbsolutePath());
                if (resultadoOK) {
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Fichero cargado correctamente", "Cargar Fichero", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Fichero NO cargado", "Cargar Fichero", JOptionPane.ERROR_MESSAGE);
            }
        }
        }
            
        );
        menu.add(mi3);
        menu.setMinimumSize(new Dimension(50,0));
        barraMenu.setLayout(new BoxLayout(barraMenu, BoxLayout.X_AXIS));
        cronometro.iniciarCronometro();
        hilo=new Thread(cronometro);
        hilo.start();
        barraMenu.add(Box.createRigidArea(new Dimension(400,0)));  
        barraMenu.add(tiempoTranscurrido);
        cronometro.getTiempo().setOpaque(false);
        barraMenu.add(cronometro.getTiempo());
        barraMenu.add(Box.createRigidArea(new Dimension(450,0)));
        barraMenu.add(numeroMinas);
        barraMenu.add(minasRestantes);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        perm = new int[n][m];
        boolean allmines = false;
        this.guesses = guesses;
        this.mines = mines;
        b = new JButton [n][m];
        setLayout(new GridLayout(n,m));
        /*for (int y = 0;y<m+2;y++){
            mines[0][y] = 3;
            mines[n+1][y] = 3;
            guesses[0][y] = 3;
            guesses[n+1][y] = 3;
        }
        for (int x = 0;x<n+2;x++){
            mines[x][0] = 3;
            mines[x][m+1] = 3;
            guesses[x][0] = 3;
            guesses[x][m+1] = 3;
        } */
        /*do {
            int check = 0;
            for (int y = 1;y<m+1;y++){
                for (int x = 1;x<n+1;x++){
                    mines[x][y] = 0;
                    guesses[x][y] = 0;
                }
            }
            for (int x = 0;x<nomines;x++){
                mines [(int) (Math.random()*(n)+1)][(int) (Math.random()*(m)+1)] = 1;
            }
            for (int x = 0;x<n;x++){
                for (int y = 0;y<m;y++){
                if (mines[x+1][y+1] == 1){
                        check++;
                    }
                }
            }
            if (check == nomines){
                allmines = true;
            }
        }while (allmines == false); */
        for (int y = 0;y<m;y++){
            for (int x = 0;x<n;x++){
                if ((mines[x+1][y+1] == 0) || (mines[x+1][y+1] == 1)){
                    perm[x][y] = perimcheck(x,y);
                }
                b[x][y] = new JButton("?");
                b[x][y].addActionListener(this);
                b[x][y].addMouseListener(this);
                add(b[x][y]);
                b[x][y].setEnabled(true);
                minasRestantes.setText(String.valueOf(nomines));
            }//end inner for
        }//end for
        pack();
        setVisible(true);
        for (int y = 0;y<m+2;y++){
            for (int x = 0;x<n+2;x++){
                System.out.print(mines[x][y]);
            }
        System.out.println("");}
        starttime = System.nanoTime();
        
        /*add(prueba);
        prueba.addActionListener(this);*/
    }//end constructor Mine()
    public void actionPerformed(ActionEvent e){
         
        
            found =  false;
            JButton current = (JButton)e.getSource();
            for (int y = 0;y<m;y++){
                for (int x = 0;x<n;x++){
                    JButton t = b[x][y];
                    if(t == current){
                        row=x;column=y; found =true;
                    }
                }//end inner for
            }//end for
            if(!found) {
                System.out.println("didn't find the button, there was an error "); System.exit(-1);
            }
            Component temporaryLostComponent = null;
            if (b[row][column].getBackground() == Color.orange){
                /*if((mines[row+1][column+1] == 1)){
                    nomines = nomines -1 ;
                    mines[row+1][column+1] = 0;
                    minasRestantes.setText(String.valueOf(nomines));
                }*/
                return;
            }else if (mines[row+1][column+1] == 1){
                    cronometro.pararCronometro();
                    JOptionPane.showMessageDialog(temporaryLostComponent, "You set off a Mine!!!!.");
                    //System.exit(0);
                    this.setVisible(false);
            } else {
                tmp = Integer.toString(perm[row][column]);
                if (perm[row][column] == 0){
                        tmp = " ";

                }
                b[row][column].setText(tmp);
                b[row][column].setEnabled(false);
                checkifend();
                if (perm[row][column] == 0){
                    scan(row, column);
                    checkifend();
                }
            }
        
        
    }
 
    public void checkifend(){
        int check= 0;
        for (int y = 0; y<m;y++){
            for (int x = 0;x<n;x++){
        if (b[x][y].isEnabled()){
            check++;
        }
            }}
        if (check == nomines){
            endtime = System.nanoTime();
            Component temporaryLostComponent = null;
            cronometro.pararCronometro();
            JOptionPane.showMessageDialog(temporaryLostComponent, "Congratulations you won!!! It took you "+(int)((endtime-starttime)/1000000000)+" seconds!");
            this.setVisible(false);
        }
    }
    
 
    public void scan(int x, int y){
        for (int a = 0;a<8;a++){
            if (mines[x+1+deltax[a]][y+1+deltay[a]] == 3){
 
            } else if ((perm[x+deltax[a]][y+deltay[a]] == 0) && (mines[x+1+deltax[a]][y+1+deltay[a]] == 0) && (guesses[x+deltax[a]+1][y+deltay[a]+1] == 0)){
                if (b[x+deltax[a]][y+deltay[a]].isEnabled()){
                    b[x+deltax[a]][y+deltay[a]].setText(" ");
                    b[x+deltax[a]][y+deltay[a]].setEnabled(false);
                    scan(x+deltax[a], y+deltay[a]);
                }
            } else if ((perm[x+deltax[a]][y+deltay[a]] != 0) && (mines[x+1+deltax[a]][y+1+deltay[a]] == 0)  && (guesses[x+deltax[a]+1][y+deltay[a]+1] == 0)){
                tmp = new Integer(perm[x+deltax[a]][y+deltay[a]]).toString();
                b[x+deltax[a]][y+deltay[a]].setText(Integer.toString(perm[x+deltax[a]][y+deltay[a]]));
                b[x+deltax[a]][y+deltay[a]].setEnabled(false);
            }
        }
    }
 
    public int perimcheck(int a, int y){
        int minecount = 0;
        for (int x = 0;x<8;x++){
            if (mines[a+deltax[x]+1][y+deltay[x]+1] == 1){
                minecount++;
            }
        }
        return minecount;
    }
 
    public void windowIconified(WindowEvent e){
 
    }
    /*
    public static void main(String[] args){
        new BuscaminasGit();
    }*/
    
    public void mouseClicked(MouseEvent e) {
 
    }
 
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            found =  false;
            Object current = e.getSource();
            for (int y = 0;y<m;y++){
                    for (int x = 0;x<n;x++){
                            JButton t = b[x][y];
                            if(t == current){
                                    row=x;column=y; found =true;
                            }
                    }//end inner for
            }//end for
            if(!found) {
                System.out.println("didn't find the button, there was an error "); System.exit(-1);
            }
            if ((guesses[row+1][column+1] == 0) && (b[row][column].isEnabled())){
                b[row][column].setText("x");
                guesses[row+1][column+1] = 1;
                b[row][column].setBackground(Color.orange);
                if (mines[row+1][column+1] == 1){
                    nomines=nomines-1;
                    minasRestantes.setText(String.valueOf(nomines));
                }
            } else if (guesses[row+1][column+1] == 1){
                b[row][column].setText("?");
                guesses[row+1][column+1] = 0;
                b[row][column].setBackground(null);
                if (mines[row+1][column+1] == 1){
                    nomines=nomines+1;
                    minasRestantes.setText(String.valueOf(nomines));
                }
            }
        }
    }
    public boolean hacerBackUp(String nombreFichero) {
        try {
            // Apertura
            FileOutputStream fos = new FileOutputStream(nombreFichero);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            
            // Escritura
            //cronometro.pararCronometro();
            Datos datos = new Datos();
            datos.setN(n);
            datos.setM(m);
            datos.setNomines(nomines);
            datos.setMines(mines);
            datos.setGuesses(guesses);
            datos.setPerm(perm);
            Botones botones[][]=datos.getBotones();
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    //datos.getBotones()[n][m].setColor(b[n][m].getBackground());
                    botones[n][m].setText(b[n][m].getText());
                    botones[n][m].setEstado(b[n][m].isEnabled());
                }
            }
            datos.setBotones(botones);
            
            oos.writeObject(datos);
            // Cierre
            oos.flush();
            oos.close();
            return true;
        } catch (IOException ex) {
            System.err.println("ERROR: No ha sido posible hacer el backup del fichero  '" + nombreFichero + "'");
        }
        return false;
    }
    
    public boolean restaurarBackUp(String nombreFichero) {
        try {
            // Apertura
            FileInputStream fis = new FileInputStream(nombreFichero);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            
            // Lectura
            Datos datos = (Datos) ois.readObject();
            BuscaminasGit buscaminas = new BuscaminasGit(datos.getN(), datos.getM(), datos.getNomines(), datos.getMines(), datos.getGuesses(),datos.getPerm());
            
            // Cierre
            ois.close();
            return true;
        } catch (EOFException ex) {
            System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "'. Fin de fichero inesperado");
        } catch (ClassNotFoundException | IOException ex) {
            System.err.println("ERROR: No ha sido posible recuperar el backup del fichero '" + nombreFichero + "' " + ex.getMessage());
        }
        return false;
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
}//end class
