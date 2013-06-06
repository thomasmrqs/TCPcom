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
public class ServeurThread extends Connexion {

    private int id;
    private int idServeur;

    public ServeurThread(int idServeur, int id, Socket s) {
        super();
        this.id = id;
        this.idServeur = idServeur;
        this.socket = s;
    }

    @Override
    protected void initialisation() throws IOException {
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public String toString() {
        return "Serveur : " + this.idServeur + " / Connexion n°" + this.id + " : ";
    }
}
