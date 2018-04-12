/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasgit;

import java.io.Serializable;

/**
 *
 * @author Diegomendez1997
 */
public class Datos implements Serializable{
    private int n;
    private int m;
    int nomines;
    int [][] mines;
    int guesses[][];
    int perm[][];
    Botones botones [][];
    
    public Datos(){
        
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getNomines() {
        return nomines;
    }

    public void setNomines(int nomines) {
        this.nomines = nomines;
    }

    public int[][] getMines() {
        return mines;
    }

    public void setMines(int[][] mines) {
        this.mines = mines;
    }

    public int[][] getGuesses() {
        return guesses;
    }

    public void setGuesses(int[][] guesses) {
        this.guesses = guesses;
    }

    public int[][] getPerm() {
        return perm;
    }

    public void setPerm(int[][] perm) {
        this.perm = perm;
    }

    public Botones[][] getBotones() {
        return botones;
    }

    public void setBotones(Botones[][] botones) {
        this.botones = botones;
    }

    
    
}
