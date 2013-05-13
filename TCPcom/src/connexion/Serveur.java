package connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public class Serveur implements Runnable {

    ArrayList<ServeurThread> liste;
    private int port;
    private String nom;
    private boolean alive;
    private ServerSocket socket;

    public Serveur(String nom, int port) {
        this.alive = true;
        this.liste = new ArrayList();
        this.port = port;
        this.nom = nom;
    }

    public ArrayList<ServeurThread> getListe() {
        return this.liste;
    }

    @Override
    public String toString() {
        return "Serveur " + this.nom + " : ";
    }

    @Override
    public void run() {
        ServeurThread st;
        try {
            this.socket = new ServerSocket(this.port);
            System.out.println(this + " en écoute");
            while (this.alive) {
                st = new ServeurThread(this.nom, this.socket.accept());
                this.liste.add(st);
                GestionDesConnexions.get().ajouterConnexion(st);
                (new Thread(st)).start();
            }
        } catch (IOException ex) {
            System.out.println(this  + " fermeture ....");
        }
    }

    public void fermer() {
        try {
            for (Connexion c : this.liste) {
                c.fermerConnexion();
            }
            this.socket.close();
            this.alive = false;
        } catch (Exception ex) {
           System.out.println(this  + " Erreureeee");
        }
    }
}
