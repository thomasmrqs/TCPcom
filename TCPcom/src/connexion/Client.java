/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author greg
 */
public class Client extends Connexion {

    private String nom;
    private int id;
    private boolean alive;

    public String getIpClient() {
        if (this.socket != null && !this.socket.isClosed()) {
            return this.socket.getLocalAddress().getHostAddress();
        }
        return "ClientNoAdr";
    }

    

    public String getNom() {
        return nom;
    }

    

    public Client(String nom, String ipServeur, int portServeur) {
        super();
        this.alive = true;
        this.id = Utils.creerIdentifiantClient();
        this.portLocal = 0;
        this.nom = nom;
        this.ipDistante = ipServeur;
        this.portDistant = portServeur;
    }

    public Client(String nom, String ipServeur, int portServeur, int portClient) {
        super();
        this.alive = true;
        this.id = Utils.creerIdentifiantClient();
        this.portLocal = portClient;
        this.nom = nom;
        this.ipDistante = ipServeur;
        this.portDistant = portServeur;
    }

    @Override
    protected void initialisation() throws IOException {
        try {
            this.socket = new Socket(InetAddress.getByName(this.ipDistante), this.portDistant, null, this.portLocal);
            this.portLocal = this.socket.getLocalPort();
            this.ipLocale = this.socket.getLocalAddress().getHostName();
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            this.alive = false;
            System.out.println("Erreur du client " + this.id + " " + e.getMessage());
            
        }
    }

    public int getId() {
        return id;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public String toString() {
        return "Client " + this.id + " : ";
    }
}
