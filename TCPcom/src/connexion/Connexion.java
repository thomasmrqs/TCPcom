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
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public abstract class Connexion implements Runnable {

    protected boolean alive;
    protected int portDistant;
    protected String ipLocale;
    protected String ipDistante;
    protected int portLocal;
    protected DataOutputStream out;
    protected BufferedReader in;
    protected Socket socket;
    protected final Stack<Paquet> ecriture;
    protected final Stack<Paquet> lecture;

    public int getPortDistant() {
        return portDistant;
    }

    public String getIpLocale() {
        return ipLocale;
    }

    public String getIpDistante() {
        return ipDistante;
    }

    public int getPortLocal() {
        return portLocal;
    }

    protected abstract void initialisation() throws IOException;

    public Connexion() {
        this.alive = false;
        this.lecture = new Stack();
        this.ecriture = new Stack();
    }

    @Override
    public void run() {
        System.out.println(this + " lance ");
        try {
            this.initialisation();
            this.alive = true;
            while (this.alive) {
                Thread.sleep(10);
                this.lectureEcriture();
            }
        } catch (IOException ex) {
            this.alive = false;
            System.out.println("Connexion::" + this + " Erreure run()");
        } catch (Exception e) {
            this.alive = false;
            System.out.println("Connexion::" + this + " Erreure run()_Etape 2");
        }
    }

    
    public Paquet lireDernierMessage() {
        synchronized (this.lecture) {
            if (this.lecture.isEmpty()) {
                return null;
            }
            return this.lecture.pop();
        }
    }

    public void ecrirePaquet(Paquet p) {
        synchronized (this.ecriture) {
            this.ecriture.push(p);
        }
    }

    @Deprecated
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
                Decompression d = new Decompression(tmp.getBytes());
                this.lecture.add(d.DecompSegment());
            }
        }
    }

    @Deprecated
    public synchronized String etatLecture() {
        String res = this.lecture.size() + " messages lus :";
        for (Paquet p : this.lecture) {
            res += "{" + p + "}";
        }
        return res;
    }

    public void fermerConnexion() {
        try {
            this.alive = false;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.in != null) {
                this.in.close();
            }
            if (this.out != null) {
                this.out.close();
            }
            if (this.socket != null) {
                this.socket.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this + " fermeture ....");
    }

    public boolean isAlive() {
        return alive;
    }
}
