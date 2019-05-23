
import Controller.MainController;
import View.FramePrincipal;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bernardo
 */
public final class Principal {
    public static void main(String[] args) throws StaleProxyException, IOException, ClassNotFoundException{
        //new FramePrincipal().setVisible(true);xx
        //new MainController();
        MainController mainController = new MainController();
        mainController.inicializar();
    }
}
