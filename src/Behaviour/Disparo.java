/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviour;

import Model.Tanque;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

/**
 * @author Gustavo
 */
public class Disparo extends CyclicBehaviour{

    public int x, y;
    private int dirX;
    private int dirY;
    private long contadorTiempo;
    private Tanque agente;
    
    public Disparo(Tanque agente){
        super();
        this.agente = agente;
        //x = agente.getX();
        //y = agente.getY();
        contadorTiempo = System.currentTimeMillis();
    }
    
    @Override
    public void action() {
        
        //System.out.println("Disparo de: " + myAgent.getLocalName());
        
        /*
        if(!agente.disparar){
            x = agente.getX();
            y = agente.getY();
            dirX = agente.movimiento.dirX;
            dirY = agente.movimiento.dirY;
            contadorTiempo = System.currentTimeMillis();
        }
        else{
            if(System.currentTimeMillis() - contadorTiempo >= 10){
                contadorTiempo = System.currentTimeMillis();
                x += dirX;
                y += dirY;
                if(dirX == 0 && dirY == 0)
                    agente.disparar = false;
                if(x < 0 || x > 1150 || y < 0 || y > 550)
                    agente.disparar = false;
            }
        }
        */
        
    }
    
}
