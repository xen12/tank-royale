/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import jade.util.leap.Serializable;

/**
 *
 * @author Bernardo
 */
public class ValoresTanque implements Serializable {
    
    private int posX, posY;
    private String nombre;
    
    public ValoresTanque(int x, int y, String nombre){
        posX = x;
        posY = y;
        this.nombre = nombre;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public String getNombre(){
        return nombre;
    }
}
