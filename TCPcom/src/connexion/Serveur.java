package connexion;

import graphique.GUI;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author greg
 */
public class Serveur implements Runnable {

    ArrayList<ServeurThread> liste;
    private int port;
    private int id;
    private boolean alive;
    private ServerSocket socket;
    private int port_saisie;
    private String ip_saisie;

    public int getPort_saisie() {
        return port_saisie;
    }

    public void setPort_saisie(int port_saisie) {
        this.port_saisie = port_saisie;
    }

    public String getIp_saisie() {
        return ip_saisie;
    }

    public void setIp_saisie(String ip_saisie) {
        this.ip_saisie = ip_saisie;
    }

    @Deprecated
    public Serveur(int port) {
        //Retirer le nom du serveur => identifiant géré automatiquement
        this.alive = false;
        this.liste = new ArrayList<ServeurThread>();
        this.port = port;
        this.id = Utils.creerIdentifiantServeur();
    }

    public ArrayList<ServeurThread> getListe() {
        return this.liste;
    }

    @Override
    public String toString() {
        return "Serveur " + this.id + " : ";
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void run() {
        ServeurThread st;
        int idServeurThread = 0;
        try {
            this.socket = new ServerSocket(this.port);
            System.out.println(this + " en écoute");
            this.alive = true;
            while (this.alive) {
                st = new ServeurThread(this.id, ++idServeurThread, this.socket.accept());
                this.liste.add(st);
                System.out.println("Je vais ajouter un client à mon serveur");
                Automate a = new Automate();
                a.setConnexion(st);
                a.open(st.portLocal, this.ip_saisie, this.port_saisie, false);
                (new Thread(a)).start();
                GUI.get().obtainCard(this).addClientToServeur(a);//Ajout du serveurThread                
                GestionDesConnexions.get().ajouterConnexion(st);
                (new Thread(st)).start();
            }
        } catch (IOException ex) {
            System.out.println("Erreure de " + this + "IOException " + ex.getMessage());
            this.alive = false;
        }
    }

    public void fermer() {
        try {
            this.alive = false;
            for (Connexion c : this.liste) {
                c.fermerConnexion();
            }
            this.socket.close();
        } catch (Exception ex) {
            System.out.println("Erreur de " + this + " lors de la fermeture");
        }
    }
}
