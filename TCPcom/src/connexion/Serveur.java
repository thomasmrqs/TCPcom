package connexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public class Serveur implements Runnable {

    private int port;
    private ArrayList<ServeurThread> clients;

    public Serveur(int port) {
        this.port = port;
        this.clients = new ArrayList();
    }

    @Override
    public void run() {
        ServeurThread gestionnaire;
        ServerSocket s;
        try {
            s = new ServerSocket(this.port);
            System.out.println(this + " en écoute");
            while (true) {
                gestionnaire = new ServeurThread(s.accept());
                System.out.println(this + " client accepté");
                (new Thread(gestionnaire)).start();
                this.clients.add(gestionnaire);
            }
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Serveur local (écoute sur le port : " + this.port + ") : ";
    }
}
