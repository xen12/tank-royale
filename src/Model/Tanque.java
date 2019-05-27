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
    public int hp;
    public String orientación;
    
    public Tanque(int x, int y){
        super();
        movimiento = new Movimiento(x, y);
        disparo = new Disparo(this);
        disparar = false;
        hp = 100;
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
    
    public void recibirDaño(){
        hp = hp -10;
    }
}
