/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Bernardo
 */
public class PanelView extends JPanel implements KeyListener{
    
    //TanqueView tanque;
    
    public PanelView(){
        inicializar();
    }
    
    private void inicializar(){
        //tanque = new TanqueView(50, 50);
        //add(tanque);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                tanque.mover(1, 0);
                break;
            case KeyEvent.VK_LEFT:
                tanque.mover(-1, 0);
                break;
            case KeyEvent.VK_UP:
                tanque.mover(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                tanque.mover(0, 1);
                break;
            default:
                break;
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
