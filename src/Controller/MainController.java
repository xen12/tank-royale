package Controller;
// IMPORTACIÓN DE CLASES DE LOS OTROS PAQUETES
import Model.Tanque;
import View.BalaView;
import View.FramePrincipal;
import View.TanqueView;
// IMPORTACIÓN DE PAQUETES DE JADE
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
// IMPORTACIÓN DE FUNCIONALIDADES DE JAVA
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
// LABEL DE JAVASWING
import javax.swing.JLabel;

/**
 * @author Gustavo
 */

// CONTROLADOR PRINCIPAL DEL PROYECTO (Clase que inicializa valores y la puesta en marcha de las funciones de los objetos)
public class MainController {    
    private FramePrincipal frame;
    private ArrayList<Tanque> tanques;
    private ArrayList<TanqueView> tanquesVisual;
    private ArrayList<JLabel> infoTanques;
    private ArrayList<BalaView> balas;
    int num = 5;
    
    //Función para inicializar los valores de los atributos que estan arriba
    public void inicializar() throws ClassNotFoundException, IOException{
        frame = new FramePrincipal();
        tanques = new ArrayList<Tanque>();
        tanquesVisual = new ArrayList<TanqueView>();
        balas = new ArrayList<BalaView>();
        inicializarTanques();
        
        frame.setVisible(true);
        Timer timer = new Timer();
        
        // Función de java para ejecutar una función repetidamente como for, pero con un intervalo de tiempo de inactividad entre cada iteración
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                // Recorre todos los tanques de la lista de tanques
                for(int i=0 ; i<tanques.size() ; i++){
                    
                    // Verifica la salud del tanque en posición i, y ejecuta el if si es mayor a 0
                    if( tanques.get(i).hp > 0 ){
                        tanquesVisual.get(i).mover(tanques.get(i).getX(), tanques.get(i).getY(), frame);
                        balas.get(i).mover(tanques.get(i).disparo.x, tanques.get(i).disparo.y);

                        // Recorre otra vez todos los tanques de la lista para comparar la posición del tanque j respecto de i
                        for(int j=0 ; j<tanques.size() ; j++){
                            if(i!=j){
                                    /*
                                    // Detectando si hay otro tanque en el eje x para disparar
                                    if( tanquesVisual.get(i).getY() > tanquesVisual.get(j).getY()-25 && tanquesVisual.get(i).getY() < tanquesVisual.get(j).getY()+25 ){
                                        if(tanquesVisual.get(i).orientacion == "izquierda" && tanquesVisual.get(j).getX() < tanquesVisual.get(i).getX()){
                                            tanques.get(i).disparar = true;
                                        }
                                        else if (tanquesVisual.get(i).orientacion == "derecha" && tanquesVisual.get(j).getX() > tanquesVisual.get(i).getX()){
                                            tanques.get(i).disparar = true;
                                        }
                                    } // Detectando si hay otro tanque en el eje y para disparar
                                    else if ( tanquesVisual.get(i).getX() > tanquesVisual.get(j).getX()-25 && tanquesVisual.get(i).getX() < tanquesVisual.get(j).getX()+25 ){
                                        if(tanquesVisual.get(i).orientacion == "arriba" && tanquesVisual.get(j).getY() < tanquesVisual.get(i).getY()){
                                            tanques.get(i).disparar = true;
                                        }
                                        else if (tanquesVisual.get(i).orientacion == "abajo" && tanquesVisual.get(j).getY() > tanquesVisual.get(i).getY()){
                                            tanques.get(i).disparar = true;
                                        }
                                    }
                                   */
                                    
                                    // FUNCIÓN DE ARRIBA REFACTORIZADA EN LA CLASE Tanque
                                    tanquesVisual.get(i).verificarTanqueXY(tanques.get(i), balas.get(i), tanquesVisual.get(j));

                                    // Función que indica en interfaz nombre del tanque y su salud
                                    if( tanquesVisual.get(i).getX() >= balas.get(j).getX()-25 && tanquesVisual.get(i).getX() <= balas.get(j).getX()+25  && tanquesVisual.get(i).getY() >= balas.get(j).getY()-25 && tanquesVisual.get(i).getY() <= balas.get(j).getY()+25 ){
                                        tanques.get(j).disparar = false;
                                        tanques.get(i).recibirDaño();
                                        tanquesVisual.get(i).label.setText("<html>" + tanques.get(i).getLocalName() + "<br>" + tanques.get(i).hp + "/100<html>");
                                    }
                            }
                        }
                    } else { // Se ejecuta else si la salud del tanque es menor igual a 0 para removerlo de la lista e interfaz
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
    
    // FUNCIÓN LLAMADA DESDE ARRIBA PARA INICIALIZAR LOS ARREGLOS
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
            // FOR PARA LLENAR LOS ARREGLOS CON AGENTES TANQUES, TANQUES VISUALES Y BALAS VISUALES
            for(int i=0;i<num;i++){
                // SE INICIALIZA LA POSICIÓN DEL TANQUE EN EL CAMPO ALEATORIAMENTE EN X, Y
                int posY = (int)Math.floor(Math.random() * (480 - 1 +1) + 0);
                int posX = (int)Math.floor(Math.random() * (1080 - 1 +1) + 0);
                int img = ((int)Math.floor(Math.random() * (4 - 1 +1) + 1));
                //balas[i] = frame.agregarNuevaBala(i * 200, i * 200);
                Tanque tanque = new Tanque(posX, posY);
                // SE AGRAEGA EL AGENTE TANQUE RECIEN CREADO AL CONTENEDOR ac
                ac = container.acceptNewAgent(nombres[i], tanque);
                ac.start();
                
                // AGREGANDO EL TANQUE VISUAL AL FRAME CREADO ARRIBA
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
