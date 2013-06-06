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

    private String ipServeur;
    private String nom;

    public String getIpServeur() {
        return ipServeur;
    }

    public void setIpServeur(String ipServeur) {
        this.ipServeur = ipServeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Client(String nom, String ipServeur, int portServeur) {
        super();
        this.nom = nom;
        this.ipServeur = ipServeur;
        this.port = portServeur;
    }

    @Override
    protected void initialisation() throws IOException {
        this.socket = new Socket(InetAddress.getByName(this.ipServeur), this.port);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public String toString() {
        return "Client " + this.nom + " : ";
    }
}
