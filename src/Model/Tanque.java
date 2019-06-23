package Model;

import Behaviour.Disparo;
import Behaviour.Movimiento;
import jade.core.Agent;

/**
 * @author Gustavo
 */

// CLASE HEREDADA DE JADE AGENTE
public class Tanque extends Agent {
    
    public Movimiento movimiento;
    public Disparo disparo;
    public boolean disparar;
    public int hp;
    public String orientación;
    
    // CONSTRUCTOR QUE RECIBE PARÁMETROS PARÁMETROS DE POSICIÓN PARA EL FRAME (Sincronización con la Clase TanqueViewl)
    public Tanque(int x, int y){
        super();
        movimiento = new Movimiento(x, y);
        disparo = new Disparo(this);
        disparar = false;
        hp = 100;
    }
    
    // FUNCIÓN DE JADE SIMILAR A UN CONSTRUCTOR PARA ASIGNAR CLASES DE COMPORTAMIENTO AL AGENTE
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
