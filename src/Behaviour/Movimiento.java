/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviour;

import jade.core.behaviours.Behaviour;
import java.util.Random;

/**
 *
 * @author Bernardo
 */
public class Movimiento extends Behaviour{

    private boolean turno;
    private boolean detener;
    private int x;
    private int y;
    private long contadorTiempo;
    private int keep;
    public int dirX, dirY;
    
    public Movimiento(int x, int y){
        super();
        keep = 0;
        detener = false;
        turno = false;
        this.x = x;
        this.y = y;
        contadorTiempo = System.currentTimeMillis();
    }
    
    @Override
    public void action() {
        System.out.println(myAgent.getLocalName() + " moviéndose");
        if(System.currentTimeMillis() - contadorTiempo >= 50){
            if(keep <= 0){
                Random r = new Random(System.currentTimeMillis() / (x * y + 1));
                
                dirX = r.nextInt(3) - 1;
                r = new Random(System.nanoTime());
                dirY = r.nextInt(3) - 1;
                keep = 20;
            }
            else
                keep--;
            if((dirX == -1 && x <= 0) || (dirX == 1 && x >= 1080))
                dirX = dirX * -1;
            if((dirY == -1 && y <= 0) || (dirY == 1 && y >= 480))
                dirY = dirY *= -1;
            
            x += 5 * dirX;
            y += 5 * dirY;
            
            if(dirX != 0)
                dirY = 0;
            if(dirY != 0)
                dirX = 0;
            
            turno = !turno;
            contadorTiempo = System.currentTimeMillis();
        }
    }

    @Override
    public boolean done() {
        return detener;
    }
    
    public void detenerMovimiento(){
        detener = true;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
