/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Tanque;
import Extra.RotatedIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author Bernardo
 */

// CLASE PARA LA PARTE INTERFAZ DEL TANQUE, QUE RECIBE LOS MISMOS PARÁMETROS DE POSICIÓN QUE LA CLASE AGENTE TANQUE
public class TanqueView extends JLabel {
    
    private static String NOMBRE_IMAGEN; 
    private static final int ANCHO = 50;
    private static final int ALTO = 50;
    private static final int VELOCIDAD = 5;
    public String orientacion;
    
    private Image imagen;
    private String name;
    private FramePrincipal frame;
    public JLabel label;
    
    // RECIBIENDO PARÁMETROS DE POSICIÓN DESDE LA CRECIÓN DEL TANQUE EN MainController, MÁS LA IMAGEN GENERADA PARA EL TANQUE
    public TanqueView(int x, int y, int img, FramePrincipal frame, String name){
        NOMBRE_IMAGEN = "img/tank"+ img + ".png";
        this.frame = frame;
        this.name = name;
        label = new JLabel("<html>" + name + "<br>" + " 100/100" + "<html>", JLabel.CENTER );
        label.setFont(new Font("Serif", Font.BOLD, 13));
        label.setForeground(Color.black);
        init(x, y);
    }
    
    // INICIALIZACIÓN DEL OBJETO EN INTERFAZ
    private void init(int posX, int posY){
        URL dir = getClass().getClassLoader().getResource(NOMBRE_IMAGEN);
        imagen = null;
        try {
            imagen = ImageIO.read(new File(dir.getPath()))
                    .getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
        } 
        catch (IOException e) {
            System.out.println(e.toString());
        }
        setBounds(posX, posY, ANCHO, ALTO);
        setIcon(new ImageIcon(imagen));
        
        label.setBounds(posX-14,posY+6,80,120);
        frame.agregarLabel(label);
    }
    
    // FUNCIÓN PARA MOVER LA PARTE VISUAL DEL TANQUE EN EL FRAME, RECIBIENDO LA POSICIÓN ACTUAL
    public void mover(int xPos, int yPos, FramePrincipal frame){
        RotatedIcon img = null;
        
        int x = xPos == getX() ? 0 : xPos>getX() ? 1 : -1;
        int y = yPos == getY() ? 0 : yPos>getY() ? 1 : -1;
        
        // ROTACIÓN DEL TANQUE CUANDO LLEGA A LOS BORDES
        if(x < 0){
            img = new RotatedIcon(new ImageIcon(imagen), RotatedIcon.Rotate.DOWN); orientacion = "izquierda";}
        else if(x > 0){
            img = new RotatedIcon(new ImageIcon(imagen), RotatedIcon.Rotate.UP); orientacion = "derecha";}
        else if(y < 0){
            img = new RotatedIcon(new ImageIcon(imagen), RotatedIcon.Rotate.UPSIDE_DOWN); orientacion = "arriba";}
        else if(y > 0){
            img = new RotatedIcon(new ImageIcon(imagen), RotatedIcon.Rotate.ABOUT_CENTER); orientacion = "abajo";}
        if(img != null)
            setIcon(img);
        
        setBounds(xPos, yPos, ANCHO, ALTO);
        label.setBounds(xPos-14, yPos+6, 80, 120);
    }
    
    public void verificarTanqueXY(Tanque tank, BalaView bala, TanqueView otherTankView){
        if( getY() > otherTankView.getY()-25 && getY() < otherTankView.getY()+25 ){
            if(orientacion == "izquierda" && otherTankView.getX() < getX()){
                int potencia = distanciaX(otherTankView);
                tank.disparo.VELOCIDAD = potencia;
                tank.disparo.TIEMPO_DE_VIDA = tiempoVidaBala(potencia);
                tank.disparar = true;
            }
            else if (orientacion == "derecha" && otherTankView.getX() > getX()){
                int potencia = distanciaX(otherTankView);
                tank.disparo.VELOCIDAD = potencia;
                tank.disparo.TIEMPO_DE_VIDA = tiempoVidaBala(potencia);
                tank.disparar = true;
            }
        } // Detectando si hay otro tanque en el eje y para disparar
        else if ( getX() > otherTankView.getX()-25 && getX() < otherTankView.getX()+25 ){
            if(orientacion == "arriba" && otherTankView.getY() < getY()){
                int potencia = distanciaY(otherTankView);
                tank.disparo.VELOCIDAD = potencia;
                tank.disparo.TIEMPO_DE_VIDA = tiempoVidaBala(potencia);
                tank.disparar = true;
            }
            else if (orientacion == "abajo" && otherTankView.getY() > getY()){
                int potencia = distanciaY(otherTankView);
                tank.disparo.VELOCIDAD = potencia;
                tank.disparo.TIEMPO_DE_VIDA = tiempoVidaBala(potencia);
                tank.disparar = true;
            }
        }
    }
    
    public int distanciaX(TanqueView otherTankView){
        int diferencia = Math.abs(getX() - otherTankView.getX());
        int potencia = 0;
        if(diferencia >= 0 && diferencia <= 50){
            potencia = 10;
        } else if (diferencia > 50 && diferencia <= 200){
            potencia = 8;
        } else if(diferencia > 200 && diferencia <= 400){
            potencia = 6;
        } else if(diferencia > 400){
            potencia = 4;
        }
        return potencia;
    }
    
    public int distanciaY(TanqueView otherTankView){
        int diferencia = Math.abs(getY() - otherTankView.getY());
        int potencia = 0;
        if(diferencia >= 0 && diferencia <= 50){
            potencia = 10;
            } else if (diferencia > 50 && diferencia <= 200){
            potencia = 8;
        } else if(diferencia > 200 && diferencia <= 400){
            potencia = 6;
        } else if(diferencia > 400){
            potencia = 4;
        }
        return potencia;
    }
    
    public int tiempoVidaBala(int potencia){
        int respuesta = 0;
        switch(potencia){
            case 10:
                respuesta = 50;
            case 8:
                respuesta = 120;
            case 6:
                respuesta = 250;
            case 4:
                respuesta = 500;
        }
        return respuesta;
    }
    
    // FUNCIÓN PARA ELIMINAR TANQUE VISUAL DEL FRAME
    public void retirar(int xPos, int yPos){
        setBounds(xPos, yPos, ANCHO, ALTO);
        label.setBounds(xPos, yPos, ANCHO, ALTO);
    }
}
