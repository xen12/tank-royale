package Model;

import Behaviour.Disparo;
import Behaviour.Movimiento;
import jade.core.Agent;

/**
 * @author Gustavo
 */
public class Tanque extends Agent {
    
    public Movimiento movimiento;
    public Disparo disparo;
    public boolean disparar;
    
    public Tanque(int x, int y){
        super();
        disparo = new Disparo(this);
        movimiento = new Movimiento(x, y);
        
        disparar = false;
    }
    
    @Override
    protected void setup(){
        addBehaviour(movimiento);
        addBehaviour(disparo);
    }
    
    public void detener(){
        movimiento.detenerMovimiento();
    }
    
    public int getX(){
        return movimiento.getX();
    }
    
    public int getY(){
        return movimiento.getY();
    }
}
