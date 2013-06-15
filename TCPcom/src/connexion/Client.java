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
        this.id = Utils.creerIdentifiantClient();
        this.portLocal = 0;
        this.nom = nom;
        this.ipDistante = ipServeur;
        this.portDistant = portServeur;
    }

    public Client(String nom, String ipServeur, int portServeur, int portClient) {
        super();
        this.id = Utils.creerIdentifiantClient();
        this.portLocal = portClient;
        this.nom = nom;
        this.ipDistante = ipServeur;
        this.portDistant = portServeur;
    }

    @Override
    protected void initialisation() throws IOException {
            this.socket = new Socket(InetAddress.getByName(this.ipDistante), this.portDistant, null, this.portLocal);
            this.portLocal = this.socket.getLocalPort();
            this.ipLocale = this.socket.getLocalAddress().getHostName();
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new DataOutputStream(this.socket.getOutputStream());
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Client ";
    }
}
