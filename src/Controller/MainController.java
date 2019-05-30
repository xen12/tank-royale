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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Bernardo
 */
public class MainController {
    
    private FramePrincipal frame;
    private ArrayList<Tanque> tanques;
    private ArrayList<TanqueView> tanquesVisual;
    private ArrayList<JLabel> infoTanques;
    private ArrayList<BalaView> balas;
    int num = 15;
    
    public void inicializar() throws ClassNotFoundException, IOException{
        frame = new FramePrincipal();
        tanques = new ArrayList<Tanque>();
        tanquesVisual = new ArrayList<TanqueView>();
        balas = new ArrayList<BalaView>();
        inicializarTanques();
        
        frame.setVisible(true);
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                for(int i=0 ; i<tanques.size() ; i++){
                    
                    if( tanques.get(i).hp > 0 ){
                        tanquesVisual.get(i).mover(tanques.get(i).getX(), tanques.get(i).getY(), frame);
                        balas.get(i).mover(tanques.get(i).disparo.x, tanques.get(i).disparo.y);

                        //System.out.println("Salud de " + tanques.get(i).getLocalName() + ": " + tanques.get(i).hp);

                        for(int j=0 ; j<tanques.size() ; j++){
                            if(i!=j){

                                    // Sensor en eje X
                                    if( tanquesVisual.get(i).getY() > tanquesVisual.get(j).getY()-25 && tanquesVisual.get(i).getY() < tanquesVisual.get(j).getY()+25 ){
                                        if(tanquesVisual.get(i).orientacion == "izquierda" && tanquesVisual.get(j).getX() < tanquesVisual.get(i).getX()){
                                            tanques.get(i).disparar = true;
                                        }
                                        else if (tanquesVisual.get(i).orientacion == "derecha" && tanquesVisual.get(j).getX() > tanquesVisual.get(i).getX()){
                                            tanques.get(i).disparar = true;
                                        }
                                    }
                                    //Sensor en eje Y
                                    else if ( tanquesVisual.get(i).getX() > tanquesVisual.get(j).getX()-25 && tanquesVisual.get(i).getX() < tanquesVisual.get(j).getX()+25 ){
                                        if(tanquesVisual.get(i).orientacion == "arriba" && tanquesVisual.get(j).getY() < tanquesVisual.get(i).getY()){
                                            tanques.get(i).disparar = true;
                                        }
                                        else if (tanquesVisual.get(i).orientacion == "abajo" && tanquesVisual.get(j).getY() > tanquesVisual.get(i).getY()){
                                            tanques.get(i).disparar = true;
                                        }
                                    }

                                    // Detector de daño
                                    if( tanquesVisual.get(i).getX() >= balas.get(j).getX()-25 && tanquesVisual.get(i).getX() <= balas.get(j).getX()+25  && tanquesVisual.get(i).getY() >= balas.get(j).getY()-25 && tanquesVisual.get(i).getY() <= balas.get(j).getY()+25 ){
                                        tanques.get(j).disparar = false;
                                        tanques.get(i).recibirDaño();
                                        tanquesVisual.get(i).label.setText("<html>" + tanques.get(i).getLocalName() + "<br>" + tanques.get(i).hp + "/100<html>");
                                    }
                            }
                        }
                    } else {
                        tanques.remove(i);
                        
                        tanquesVisual.get(i).retirar(2000, 2000);
                        frame.eliminarTanque(tanquesVisual.get(i));
                        tanquesVisual.remove(i);
                        
                        balas.get(i).retirar(2000, 2000);
                        frame.eliminarBala(balas.get(i));
                        balas.remove(i);
                    }
                    
                }
                
            }
        }, 3, 3);
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
                ac = container.acceptNewAgent(nombres[i], tanque);
                ac.start();
                
                tanquesVisual.add(frame.agregarNuevoTanque(posX, posY, img, frame, tanque.getLocalName()));
                balas.add(frame.agregarNuevaBala(posX, posY));
                
                tanques.add(tanque);
            }
        } catch (StaleProxyException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return tanques;
    }
}
