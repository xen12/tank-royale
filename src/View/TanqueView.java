/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Extra.RotatedIcon;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Bernardo
 */
public class TanqueView extends JLabel {
    
    private static String NOMBRE_IMAGEN; 
    private static final int ANCHO = 50;
    private static final int ALTO = 50;
    private static final int VELOCIDAD = 5;
    public String orientacion;
    
    private Image imagen;
    
    public TanqueView(int x, int y, int img){
        NOMBRE_IMAGEN = "img/tank"+ img + ".png";
        init(x, y);
    }
    
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
    }
    
    public void mover(int xPos, int yPos){
        RotatedIcon img = null;
        
        int x = xPos == getX() ? 0 : xPos>getX() ? 1 : -1;
        int y = yPos == getY() ? 0 : yPos>getY() ? 1 : -1;
        
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
    }
    
    public void retirar(int xPos, int yPos){
        setBounds(xPos, yPos, ANCHO, ALTO);
    }
}
