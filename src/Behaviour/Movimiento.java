package Behaviour;

import jade.core.behaviours.Behaviour;
import java.util.Random;

/**
 * @author Gustavo
 */

// CLASE HEREDADA DE COMPORTAMIENTO DE JADE
public class Movimiento extends Behaviour{
    private boolean turno;
    private boolean detener;
    private int x;
    private int y;
    private long contadorTiempo;
    private int keep;
    public int dirX, dirY;
    
    // CONSTRUCTOR QUE RECIBE PARÁMETROS DESDE CREACIÓN DEL AGENTE Tanque (en MainController)
    public Movimiento(int x, int y){
        super();
        keep = 0;
        detener = false;
        turno = false;
        this.x = x;
        this.y = y;
        contadorTiempo = System.currentTimeMillis();
    }
    
    // FUNCIÓN REPETITIVA DE Jade Behaviour
    @Override
    public void action() {
        // Movimiento del tanque de manera Aleatoria en cuatro direcciones
        if(System.currentTimeMillis() - contadorTiempo >= 80){
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

    // Otra función de jade con variable boolean para detener el tanque (El action de arriba verifica esta variable para ejecutarse o no)
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
