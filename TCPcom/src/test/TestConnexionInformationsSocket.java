/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Client;
import connexion.GestionDesConnexions;
import connexion.Serveur;

/**
 *
 * @author greg
 */
public class TestConnexionInformationsSocket {
      
    public static void main(String[] args) throws InterruptedException {
        System.out.println("______________Init __________________________________");
       Serveur s = GestionDesConnexions.get().lancerServeur("10", 65000);
       Thread.sleep(1000);
       Client c = GestionDesConnexions.get().lancerClient("Client", "127.0.0.1", 65000, 64000);
       Thread.sleep(1000);
       System.out.println("______________Test des ip::port _______________________");
       System.out.println("L'ip du client est : " + c.getIpClient() + "::" + c.getPortClient());
       System.out.println("Connecté à  : " + c.getIpServeur() + "::" + c.getPortServeur());
       System.out.println("______________Fermeture          _______________________");
       //s.fermer();
       c.fermerConnexion();
       s.fermer();
      }
}
