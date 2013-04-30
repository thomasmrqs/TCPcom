/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author greg
 */
public class ServeurThread implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private DataOutputStream out;

        public ServeurThread(Socket socketClient) {
            this.socket = socketClient;
        }

        @Override
        public void run() {
            try {
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.out = new DataOutputStream(this.socket.getOutputStream());
                String tmp;
                // PREVOIR MODE PAS A PAS
                // Automate a = new Automate();
                while (true) {
                    // while (true && a.estConnecté()) {
                    tmp = this.in.readLine();
                    // tmp est un trame/packet
                    
                    // a.analyserPacket(tmp);
                    
                    // if (a.estConnecté)
                    // {
                    // afficher fin de connection
                    // }
                    
                    System.out.println(this + "recu : " + tmp);
                    this.out.writeBytes("Message du Serveur" + System.getProperty("line.separator"));
                }

            } catch (IOException ex) {
                //Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }

        }

        @Override
        public String toString() {
            return "Gestionnnaire de client : ";
        }
    }
