/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Connexion;
import connexion.GestionDesConnexions;
import connexion.Paquet;
import connexion.Serveur;
import java.io.IOException;

/**
 *
 * @author greg
 */
public class TestConnexionErreur {

    public static void main(String[] args) throws IOException, InterruptedException {
        Paquet p = new Paquet("data");
        System.out.println("______________ERREUR 1__________________________________________");
        Connexion c = GestionDesConnexions.get().lancerClient("127.0.0.1", 4500, 0);
        System.out.println(c.isAlive() == false);
        System.out.println("______________ERREUR 2__________________________________________");
        GestionDesConnexions.get().lancerServeur(4500);
        c = GestionDesConnexions.get().lancerClient("127.0.0.1", 4500, -50);
        System.out.println(c.isAlive() == false);
        System.out.println("______________ERREUR 3__________________________________________");
        c = GestionDesConnexions.get().lancerClient("127.0.0.7000", 4500, 1);
        System.out.println(c.isAlive() == false);
        System.out.println("______________ERREUR 4__________________________________________");
        Serveur s = GestionDesConnexions.get().lancerServeur(4500);
        Thread.sleep(1000);
        System.out.println(c.isAlive() == false);
        System.exit(0);
    }
}
