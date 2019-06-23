/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * @author Gustavo
 */

// CLASE DEL PANEL PRINCIPAL DE LA APLICACIÓN (Ventana)
public class FramePrincipal extends JFrame{
    
    private PanelView panel;
    private static final int ANCHO = 1150;
    private static final int ALTO =  550;
    //private URL url = getClass().getResource("/img/bg.jpg");
    //Image image = new ImageIcon(url).getImage();
    
    public FramePrincipal(){
        init();
    }
    
    // INICIALIZACIÓN DE VALORES
    private void init(){
        
        panel = new PanelView();
        panel.setLayout(null);
        //panel.setBackground(new Color(50,220,170));
        panel.setBounds(0, 0, ANCHO, ALTO);
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        
        setBounds(100, 100, ANCHO, ALTO);
    }
    
    // AÑADIR TanqueView AL PANEL
    public TanqueView agregarNuevoTanque(int x, int y, int img, FramePrincipal frame, String name){
        TanqueView tanque = new TanqueView(x, y, img, frame, name);
        panel.add(tanque);
        return tanque;
    }
    
    // ELIMINAR TanqueView DEL PANEL
    public void eliminarTanque(TanqueView tanque){
        panel.remove(tanque);
    }
    
    // AÑADIR BalaView AL PANEL
    public BalaView agregarNuevaBala(int x, int y){
        BalaView bala = new BalaView(x, y);
        panel.add(bala);
        return bala;
    }
    
    // ELIMINAR BalaView DEL PANEL
    public void eliminarBala(BalaView bala){
        panel.remove(bala);
    }
    
    public void agregarLabel(JLabel label){
        panel.add(label);
    }
}
