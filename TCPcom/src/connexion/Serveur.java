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
    private ArrayList<GestionClient> clients;

    public Serveur(int port) {
        this.port = port;
        this.clients = new ArrayList();
    }

    @Override
    public void run() {
        GestionClient gestionnaire;
        ServerSocket s;
        try {
            s = new ServerSocket(this.port);
            System.out.println(this + " en écoute");
            while (true) {
                gestionnaire = new GestionClient(s.accept());
                System.out.println(this + " client accepté");
                (new Thread(gestionnaire)).start();
                this.clients.add(gestionnaire);
            }
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class GestionClient implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private DataOutputStream out;

        public GestionClient(Socket socketClient) {
            this.socket = socketClient;
        }

        @Override
        public void run() {
            try {
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.out = new DataOutputStream(this.socket.getOutputStream());
                String tmp;
                while (true) {
                    tmp = this.in.readLine();
                    System.out.println(this + "recu : " + tmp);
                    this.out.writeBytes("Message du Serveur" + '\n');
                }

            } catch (IOException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        public String toString() {
            return "Gestionnnaire de client : ";
        }
    }

    @Override
    public String toString() {
        return "Serveur local (écoute sur le port : " + this.port + ") : ";
    }
}
