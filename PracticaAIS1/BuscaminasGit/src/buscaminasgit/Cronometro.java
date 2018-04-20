/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasgit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/**
 *
 * @author nicolaealexe
 */

 /* Problema 2 : En este problema, el problema lo tuvimos a la hora de ajustar un cronómetro. Lo complicado no fue insertarlo dentro 
    de la barra del menú, si no ponerlo en marcha y guardar con detenimiento los segundos y/o minutos que tardaba el usuario en finalizar o guardar una partida.   
    
    Para ello, nos creamos una clase individual dentro del proyecto llamada Cronometro, en la que guardamos el tiempo y arrancamos el cronometro lanzando un thread.
    El thread lo podemos controlar de tal forma que lo podíamos pararlo cuando el usuario guarde o cargue una partida.  */

public class Cronometro  implements Runnable 
{ 
    JLabel tiempo;

    public JLabel getTiempo() {
        return tiempo;
    }

    public void setTiempo(JLabel tiempo) {
        this.tiempo = tiempo;
    }
    
    boolean cronometroActivo=true;
    
    public Cronometro()
    {
        
        
 
        //Etiqueta donde se colocara el tiempo 
        tiempo = new JLabel( "00:00:000" );
        tiempo.setFont( new Font( Font.SERIF, Font.BOLD, 15 ) );
        tiempo.setHorizontalAlignment( JLabel.RIGHT );
        tiempo.setForeground( Color.BLACK );
        //tiempo.setBackground( Color. );
        tiempo.setOpaque( true );
 
        
 
        //Boton iniciar
        
    }
  
    public void run(){
        
        
         /* Problema 2 : En este problema, el problema lo tuvimos a la hora de ajustar un cronómetro. Lo complicado no fue insertarlo dentro 
    de la barra del menú, si no ponerlo en marcha y guardar con detenimiento los segundos y/o minutos que tardaba el usuario en finalizar o guardar una partida.   
    
    Para ello, nos creamos una clase individual dentro del proyecto llamada Cronometro, en la que guardamos el tiempo y arrancamos el cronometro lanzando un thread.
    El thread lo podemos controlar de tal forma que lo podíamos pararlo cuando el usuario guarde o cargue una partida.  */
        
        String[] s=tiempo.getText().split(":");
        Integer minutos = Integer.valueOf(s[0] ), segundos = Integer.valueOf(s[1] ), milesimas = Integer.valueOf(s[2] );
        //min es minutos, seg es segundos y mil es milesimas de segundo
        String min="", seg="", mil="";
        try
        {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while( cronometroActivo )
            {
                sleep( 4 );
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;
                 
                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if( milesimas == 1000 )
                {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                    }
                }
 
                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if( minutos < 10 ) min = "0" + minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();
                 
                if( milesimas < 10 ) mil = "00" + milesimas;
                else if( milesimas < 100 ) mil = "0" + milesimas;
                else mil = milesimas.toString();
                 
                //Colocamos en la etiqueta la informacion
                tiempo.setText( min + ":" + seg + ":" + mil );                
            }
        }catch(Exception e){}
        //Cuando se reincie se coloca nuevamente en 00:00:000
        //tiempo.setText( "00:00:000" );
    }
  
   
    
  
    //Iniciar el cronometro poniendo cronometroActivo 
    //en verdadero para que entre en el while
    public void iniciarCronometro() {
        cronometroActivo = true;
        
    }
  
    //Esto es para parar el cronometro
    public void pararCronometro(){
        cronometroActivo = false;
    }
  
    
    
  
   
}