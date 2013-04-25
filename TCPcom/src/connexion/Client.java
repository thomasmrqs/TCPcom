package connexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public class Client implements Runnable {

    private Inet4Address ipServeur;
    private int portServeur;
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;

    public Client(Inet4Address ipServeur, int portServeur) {
        this.ipServeur = ipServeur;
        this.portServeur = portServeur;
    }

    public void ecriture(String str) {
        try {
            this.out.writeBytes(str + '\n');
            this.out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Client (connecté à : " + this.ipServeur + ", sur le port : " + this.portServeur + ", socket : " + this.socket + ") : ";
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket(this.ipServeur, this.portServeur);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new DataOutputStream(this.socket.getOutputStream());
            System.out.println(this + " connecté");
            String tmp;
            while (true) {
                this.out.writeBytes("Message du Client" + '\n');
                tmp = this.in.readLine();
                System.out.println(this + "recu : " + tmp);
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
    }
}
