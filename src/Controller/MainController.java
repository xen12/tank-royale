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
    private BalaView[] balas;
    int num = 10;
    
    public void inicializar() throws ClassNotFoundException, IOException{
        frame = new FramePrincipal();
        tanques = new Tanque[num];
        tanquesVisual = new TanqueView[num];
        balas = new BalaView[num];
        inicializarTanques();
        
        frame.setVisible(true);
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                for(int i=0 ; i<num ; i++){
                    tanquesVisual[i].mover(tanques[i].getX(), tanques[i].getY());
                    balas[i].mover(tanques[i].disparo.x, tanques[i].disparo.y);
                    
                    for(int j=0 ; j<num ; j++){
                        if(i!=j){
                            double dif = Math.min(Math.abs(tanques[i].getX() - tanques[j].getX()) 
                                    , Math.abs(tanques[i].getY() - tanques[j].getY()));
                            double val = Math.random();
                            if(tanques[i].getX() ==  tanques[j].getX() || tanques[i].getY() ==  tanques[j].getY()){
                                tanques[i].disparar = true;
                                //tanques[i].detener();
                                //tanques[j].detener();
                                //System.out.println("Disparo de " + tanques[i].getLocalName());
                            }
                        }
                    }
                    
                    
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
                int posY = (int)Math.floor(Math.random() * (480 - 1 +1) + 0);
                int posX = (int)Math.floor(Math.random() * (1080 - 1 +1) + 0);
                int img = ((int)Math.floor(Math.random() * (4 - 1 +1) + 1));
                //balas[i] = frame.agregarNuevaBala(i * 200, i * 200);
                Tanque tanque = new Tanque(posX, posY);
                tanquesVisual[i] = frame.agregarNuevoTanque(posX, posY, img);
                balas[i] = frame.agregarNuevaBala(posX, posY);
                
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
