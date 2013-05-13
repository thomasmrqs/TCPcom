/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public abstract class Connexion implements Runnable {

    private boolean alive;
    
    protected int port;
    protected DataOutputStream out;
    protected BufferedReader in;
    protected Socket socket;
    protected final Stack<Paquet> ecriture;
    protected final Stack<Paquet> lecture;

    protected abstract void initialisation() throws IOException;

    public Connexion() {
        this.alive = true;
        this.lecture = new Stack();
        this.ecriture = new Stack();
    }

    @Override
    public void run() {
        System.out.println(this + " a été lancé ");
        try {
            this.initialisation();
        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (this.alive) {
                Thread.sleep(10);
                this.lectureEcriture();
            }
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Paquet lireDernierMessage() {
        synchronized (this.lecture) {
            return this.lecture.pop();
        }
    }

    public void ecrirePaquet(Paquet p) {
        synchronized (this.ecriture) {
            this.ecriture.push(p);
        }
    }

    public ArrayList<Paquet> lireMesssages() {
        synchronized (this.lecture) {
            ArrayList<Paquet> liste = new ArrayList();
            while (!this.lecture.isEmpty()) {
                liste.add(this.lecture.pop());
            }
            return liste;
        }
    }

    private void lectureEcriture() throws IOException {
        String tmp;
        synchronized (this.ecriture) {
            if (this.ecriture.size() > 0) {
                System.out.println(this + " écrit vraiment un message ");
                this.out.write(this.ecriture.pop().convertirBitSet());
                this.out.write('\n');
            } else {
                this.out.write('\n');
            }
        }
        synchronized (this.lecture) {
            tmp = this.in.readLine();
            if (!tmp.isEmpty()) {
                System.out.println(this + " lit vraiment un message ");
                this.lecture.add(new Paquet(tmp));
            }
        }
    }

    public synchronized String etatLecture() {
        String res = this.lecture.size() + " messages lus :";
        for (Paquet p : this.lecture) {
            res += "{" + p + "}";
        }
        return res;
    }

    public void fermerConnexion() {
        try {
            if (this.in == null) {
                this.in.close();
            }
            if (this.out == null) {
                this.out.close();
            }
            if (this.socket == null) {
                this.socket.close();
            }
            this.alive = false;
        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this  + " fermeture ....");
    }
}
