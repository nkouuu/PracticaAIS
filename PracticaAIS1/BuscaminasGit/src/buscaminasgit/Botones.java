/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasgit;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Diegomendez1997
 */


/* La clase botones se creó para poder guardar una partida. De esta forma, detectamos que botones ha pulsado el usuario, cuales están en modo "mina" (amarillo).
        A la hora de cargar una partida, es mucho mas sencillo leer de esta clase.
*/

public class Botones implements Serializable{
    String text;
    boolean estado;
    Color color;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
