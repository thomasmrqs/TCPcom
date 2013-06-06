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
public class TestConnexionErreurs {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("______________Test port serveur déjà pris __________________________________");
        Serveur s = GestionDesConnexions.get().lancerServeur("10", 8080);
        Serveur s2 = GestionDesConnexions.get().lancerServeur("10", 8080);
        Thread.sleep(1000);
        if (s.isAlive() && !s2.isAlive()) {
            System.out.println("ok 1 ");
        } else {
            System.out.println("nok 1 ");
        }
        Thread.sleep(100);
        s.fermer();
        s2.fermer();
        Thread.sleep(1000);
        System.out.println("______________Test port Client impossible __________________________________");
        
        Client c = GestionDesConnexions.get().lancerClient("", "127.0.0.1", 60123, 4646);
        Thread.sleep(2000);
        if (!c.isAlive()) {
            System.out.println("ok 2 ");
        } else {
            System.out.println("nok 2 ");
        }
        Thread.sleep(1000);
        System.out.println("______________Test ip remote Client impossible_____________________________");
        c = GestionDesConnexions.get().lancerClient("", "127.0.0.1000", 60123, 4646);
        Thread.sleep(3000);
        if (!c.isAlive()) {
            System.out.println("ok 2 ");
        } else {
            System.out.println("nok 2 ");
        }
    }
}
