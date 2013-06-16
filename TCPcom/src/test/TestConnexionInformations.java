/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Client;
import connexion.Connexion;
import connexion.GestionDesConnexions;
import connexion.Serveur;
import java.io.IOException;

/**
 *
 * @author greg
 */
public class TestConnexionInformations {

    public static void main(String[] args) throws IOException, InterruptedException {
        Serveur s = GestionDesConnexions.get().lancerServeur(8080);
        Thread.sleep(1000);
        Connexion c = GestionDesConnexions.get().lancerClient("127.0.0.1", 8080, 12);
        Thread.sleep(1000);
        System.out.println("______________CONNEXION__________________________________________");
        System.out.println(c + " ipLocale:" + c.getIpLocale() + "::" + c.getPortLocal() + " connecte a " + c.getIpDistante() + "::" + c.getPortDistant());
        Connexion thread = s.getListe().get(0);
        System.out.println(thread + " ipLocale:" + thread.getIpLocale() + "::" + thread.getPortLocal() + " connecte a " + thread.getIpDistante() + "::" + thread.getPortDistant());
        Thread.sleep(1000);
        System.out.println("______________Fermeture__________________________________________");
        s.fermer();
        c.fermerConnexion();
    }
}
