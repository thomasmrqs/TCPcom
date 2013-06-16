/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.util.ArrayList;

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

    public Serveur lancerServeur(int port) {
        Serveur serveur = new Serveur(port);
        (new Thread(serveur)).start();
        return serveur;
    }

    @Deprecated
    public Client lancerClient(String adresseDest, int portServeur) {
        Client t = new Client(adresseDest, portServeur);
        (new Thread(t)).start();
        this.ajouterConnexion(t);
        return t;
    }

    public Client lancerClient(String adresseDest, int portServeur, int portClient) {
        Client t = new Client(adresseDest, portServeur, portClient);
        (new Thread(t)).start();
        this.ajouterConnexion(t);
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
