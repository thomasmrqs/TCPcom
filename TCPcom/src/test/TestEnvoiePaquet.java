/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Client;
import connexion.GestionDesConnexions;
import connexion.Paquet;
import connexion.Serveur;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public class TestEnvoiePaquet {

    public static void main(String[] args) {
        System.out.println("______________Creation du paquet à envoyer_______________________");
        int port = 8000;
        Paquet p = new Paquet(port, 45000);
        p.CreerPaquetSans();
        p.AfficherPaquet();

        System.out.println("______________CONNEXION__________________________________________");
        Serveur s = GestionDesConnexions.get().lancerServeur("1", port);

        Client c = GestionDesConnexions.get().lancerClient("1", "127.0.0.1", port);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestEnvoiePaquet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("______________Envoie d'un paquet_________________________________");
        c.ecrirePaquet(p);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestEnvoiePaquet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("______________Reception d'un paquet______________________________");
        s.getListe().get(0).lireMesssages().get(0).AfficherPaquet();
        
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestEnvoiePaquet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("______________Fermeture________________________________________");
        c.fermerConnexion();
        s.fermer();


    }
}
