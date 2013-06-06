/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public class GestionDesConnexions {

    private static GestionDesConnexions instance = null;
    /*contient une liste de connexion */
    ArrayList<Connexion> listeConnexion;

    private GestionDesConnexions() {
        this.listeConnexion = new ArrayList();
    }

    public static GestionDesConnexions get() {
        if (GestionDesConnexions.instance == null) {
            GestionDesConnexions.instance = new GestionDesConnexions();
        }
        return GestionDesConnexions.instance;
    }

    public synchronized void ajouterConnexion(Connexion c) {
        this.listeConnexion.add(c);
    }

    public synchronized Connexion obtenirConnexion(int i) {
        if ((i >= 0) && (i < this.listeConnexion.size())) {
            return this.listeConnexion.get(i);
        }
        return null;
    }

    public Serveur lancerServeur(String nom, int port) {
        Serveur serveur = new Serveur(nom, port);
        (new Thread(serveur)).start();
        return serveur;
    }

    @Deprecated
    public Client lancerClient(String nom, String adresseDest, int portServeur) {
        Client t = new Client(nom, adresseDest, portServeur);
        (new Thread(t)).start();
        this.ajouterConnexion(t);
        return t;
    }

    public Client lancerClient(String nom, String adresseDest, int portServeur, int portClient) {
        Client t = new Client(nom, adresseDest, portServeur, portClient);
        (new Thread(t)).start();
        this.ajouterConnexion(t);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestionDesConnexions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public String toString() {
        String res = "";
        for (Connexion c : this.listeConnexion) {
            res += "\n\t-" + c + c.etatLecture();
        }
        return "Gestion des connexions : \n\t" + this.listeConnexion.size() + " connexion : " + res;
    }
}
