/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Connexion;
import connexion.GestionDesConnexions;
import connexion.Paquet;
import java.io.IOException;

/**
 *
 * @author greg
 */
public class TestConnexionErreur {

    public static void main(String[] args) throws IOException, InterruptedException {
        Paquet p = new Paquet("data");
        System.out.println("______________ERREUR 1__________________________________________");
        Connexion c = GestionDesConnexions.get().lancerClient(null, "127.0.0.1", 4500, 0);
        System.out.println(c.isAlive() == false);
        System.out.println("______________ERREUR 2__________________________________________");
        GestionDesConnexions.get().lancerServeur(null, 4500);
        c = GestionDesConnexions.get().lancerClient(null, "127.0.0.1", 4500, -50);
        System.out.println(c.isAlive() == false);
        System.out.println("______________ERREUR 3__________________________________________");
        c = GestionDesConnexions.get().lancerClient(null, "127.0.0.7000", 4500, 1);
        System.out.println(c.isAlive() == false);
        System.out.println("______________ERREUR 4__________________________________________");
        GestionDesConnexions.get().lancerServeur(null, 4500);
    }
}
