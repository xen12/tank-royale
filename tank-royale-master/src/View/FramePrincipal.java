/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Bernardo
 */
public class FramePrincipal extends JFrame{
    
    private PanelView panel;
    private static final int ANCHO = 1150;
    private static final int ALTO =  550;
    
    public FramePrincipal(){
        init();
    }
    
    private void init(){
        panel = new PanelView();
        panel.setLayout(null);
        panel.setBackground(new Color(50,220,170));
        panel.setBounds(0, 0, ANCHO, ALTO);
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        
        setBounds(100, 100, ANCHO, ALTO);
    }
    
    public TanqueView agregarNuevoTanque(int x, int y, int img){
        TanqueView tanque = new TanqueView(x, y, img);
        panel.add(tanque);
        return tanque;
    }
    public BalaView agregarNuevaBala(int x, int y){
        BalaView bala = new BalaView(x, y);
        panel.add(bala);
        return bala;
    }
}
