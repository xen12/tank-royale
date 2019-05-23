/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Valores;
import Model.Tanque;
import View.BalaView;
import View.FramePrincipal;
import View.TanqueView;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bernardo
 */
public class MainController {
    
    private FramePrincipal frame;
    private Tanque[] tanques;
    private TanqueView[] tanquesVisual;
    //private BalaView[] balas;
    int num = 50;
    
    public void inicializar() throws ClassNotFoundException, IOException{
        frame = new FramePrincipal();
        tanques = new Tanque[num];
        tanquesVisual = new TanqueView[num];
        inicializarTanques();
        
        frame.setVisible(true);
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                for(int i=0 ; i<num ; i++){
                    tanquesVisual[i].mover(tanques[i].getX(), tanques[i].getY());
                }
                
            }
        }, 5, 5);
    }
    
    public void inicializarTanques() {
        String[] nombres = new String[num];
        for(int i=0 ; i<num ; i++){
            nombres[i] = "Tanque " + (i+1);
        }
        
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        AgentContainer container = runtime.createMainContainer( profile );
        AgentController ac = null;
        
        try {
            for(int i=0;i<num;i++){
                int altura = (int)Math.floor(Math.random() * (480 - 1 +1) + 0);
                int ancho = (int)Math.floor(Math.random() * (1080 - 1 +1) + 0);
                int img = ((int)Math.floor(Math.random() * (4 - 1 +1) + 1));
                //balas[i] = frame.agregarNuevaBala(i * 200, i * 200);
                Tanque tanque = new Tanque(ancho, altura);
                tanquesVisual[i] = frame.agregarNuevoTanque(ancho, altura, img);
                
                ac = container.acceptNewAgent(nombres[i], tanque);
                ac.start();
                tanques[i] = tanque;
            }
        } catch (StaleProxyException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return tanques;
    }
}
