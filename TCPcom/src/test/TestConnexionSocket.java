/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Client;
import connexion.GestionDesConnexions;
import connexion.Paquet;
import connexion.Serveur;
import java.io.IOException;

/**
 *
 * @author greg
 */
public class TestConnexionSocket {

    public static void main(String[] args) throws IOException, InterruptedException {
        Paquet p = new Paquet("data");
        System.out.println("______________CONNEXION__________________________________________");
        Serveur s = GestionDesConnexions.get().lancerServeur(8000);
        Thread.sleep(10);
        Client c = GestionDesConnexions.get().lancerClient("127.0.0.1", 8000);
        Thread.sleep(1000);
        System.out.println("______________CONTROLE CONNEXION_________________________________");
        System.out.println(GestionDesConnexions.get());
        Thread.sleep(1000);
        System.out.println("______________ECRITURE D'UN PAQUET (client1=>serveur)____________");
        c.ecrirePaquet(p);
        Thread.sleep(1000);
        System.out.println("______________ECRITURE D'UN PAQUET (serveur=>client1)____________");
        GestionDesConnexions.get().obtenirConnexion(1).ecrirePaquet(p);
        Thread.sleep(1000);
        System.out.println("______________CONTROLE CONNEXION_________________________________");
        System.out.println(GestionDesConnexions.get());
        Thread.sleep(1000);

        System.out.println("______________CONNEXION NOUVEAU CLIENT___________________________");
        GestionDesConnexions.get().lancerClient("127.0.0.1", 8000);
        Thread.sleep(1000);
        System.out.println("______________CONTROLE CONNEXION_________________________________");
        System.out.println(GestionDesConnexions.get());
        Thread.sleep(1000);
        System.out.println("______________ECRITURE D'UN PAQUET (client2=>serveur)____________");
        GestionDesConnexions.get().obtenirConnexion(2).ecrirePaquet(p);
        Thread.sleep(1000);
        System.out.println("______________ECRITURE D'UN PAQUET (client2=>serveur)____________");
        GestionDesConnexions.get().obtenirConnexion(2).ecrirePaquet(p);
        Thread.sleep(1000);
        System.out.println("______________ECRITURE D'UN PAQUET (client2=>serveur)____________");
        GestionDesConnexions.get().obtenirConnexion(2).ecrirePaquet(p);
        Thread.sleep(1000);
        System.out.println("______________CONTROLE CONNEXION_________________________________");
        System.out.println(GestionDesConnexions.get());
        Thread.sleep(1000);
        System.out.println("______________FERMETURE___________________________________________");
        c.fermerConnexion();
        GestionDesConnexions.get().obtenirConnexion(2).fermerConnexion();
        s.fermer();
        Thread.sleep(1000);
    }
}
