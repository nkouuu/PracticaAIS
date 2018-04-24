/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.io.Serializable;

/**
 *
 * @author Diegomendez1997
 */


/* 

    La clase Usuario no es más que una clase simple donde almacenamos el Nombre y el Tiempo que ha tardado un usuario en GANAR una partida.
    Nos es muy util para crear Objetos de tipo Usuario y poder manejarlos a la hora de guardarlo en los ficheros.

*/
public class Usuario implements Serializable{
    private String nombre;
    private int tiempo;
    
    public Usuario(String nombre, int tiempo){
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    @Override
    public String toString(){
        String resultado;
        if(this.tiempo > 60){
            resultado = this.tiempo/60 + " minutos " + this.tiempo % 60 + " segundos";
        } else {
            resultado = this.tiempo + " segundos";
        }
        return "Nombre: " + this.nombre + ". Tiempo: " + resultado;
    }
}
