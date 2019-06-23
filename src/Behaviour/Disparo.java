package Behaviour;

import Model.Tanque;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

/**
 * @author Gustavo
 */
// CLASE HEREDADA DE COMPORTAMIENTO DE JADE
public class Disparo extends CyclicBehaviour{

    public int x, y;
    private int dirX;
    private int dirY;
    private long contadorTiempo;
    private Tanque tanque;
    public int VELOCIDAD = 4;
    
    // CONSTRUCTOR QUE RECIBE PARÁMETROS DESDE LA CREACIÓN DEL AGENTE Tanque (en MainController)
    public Disparo(Tanque tanque){
        super();
        this.tanque = tanque;
        x = tanque.getX();
        y = tanque.getY();
        contadorTiempo = System.currentTimeMillis();
    }
    
    // FUNCIÓN DE Behaviour QUE SE EJECUTA PARA SIEMPRE
    @Override
    public void action() {
        // SI TANQUE NO ESTÁ DISPARANDO RETORNA LA POSICIÓN DE LA BALA CON LA DEL TANQUE
        if(!tanque.disparar){
            x = tanque.getX();
            y = tanque.getY();
            dirX = tanque.movimiento.dirX;
            dirY = tanque.movimiento.dirY;
            contadorTiempo = System.currentTimeMillis();
        }
        else{ // SI ESTÁ DISPARANDO ACTUALIZA LA POSICIÓN DE LA BALA CON RESPECTO A LA DIRECCIÓN EN LA QUE FUÉ LANZADA
            if(System.currentTimeMillis() - contadorTiempo >= VELOCIDAD){
                contadorTiempo = System.currentTimeMillis();
                x += dirX;
                y += dirY;
                if(dirX == 0 && dirY == 0)
                    tanque.disparar = false;
                if(x < 0 || x > 1150 || y < 0 || y > 550)
                    tanque.disparar = false;
            }
        }
        
        
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
