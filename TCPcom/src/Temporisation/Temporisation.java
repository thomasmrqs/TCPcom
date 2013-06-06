/*http://moodle.um5s.ac.ma/moodle/mod/resource/view.php?id=272
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporisation;

import connexion.Paquet;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
/*
 * Contexte:
 *  Lorsque l'on emet un paquet, si l'on n'a pas recu son aquittement au bout du temps x il faut le ré emettre
 * Connaitre le temps x ? :
 *  A la création d'un automate/connexion, un objet T de type Temporisation est créé, puis démaré
 *  A chaque écriture de paquet on passe à cet objet T le paquet, ainsi qu'a chaque lecture
 *  Pour connaitre le temps x on interroge l'objet T
 *  Il ne faut pas oublier de terminer l'objet T avec fermer()  
 */
public class Temporisation implements Runnable {
    /*Attributs pour le fonctionnement*/
    //Permet de tuer le Thread

    private boolean alive;
    //Determine si c'est le premier calcul du RTT
    private boolean first;
    /*Attributs pour le calcul*/
    private double rto; //Retransmission Timeout
    private double rtt; // Round trip delay
    private double srtt; // temps de boucle
    private double rttVar;//Variance du rtt
    private static double ALPHA = 0.125;
    private static double BETA = 0.250;
    private Paire<Integer, Long> marqueurEmission;
    private Paire<Integer, Long> marqueurReception;

    public Temporisation(int... tempsParDefaut) {
        if (tempsParDefaut.length == 1){
            this.rtt = tempsParDefaut[0];
        }
        this.first = true;
        this.rto = 3; // initialisation à 3 secondes
        this.alive = true;
    }

    /*
     Cette methode est appelée à l'émission d'un paquet par l'automate
     * Si on n'a pas de marqueur on prend ce paquet et on l'identifie par son numéro de séquence
     */
    public void ecriturePaquet(Paquet p) {
        System.out.print(this + " interception d'un paquet à l'écriture");
        //Si il n'y a pas de marqueur de début
        if (this.marqueurEmission == null) {
            System.out.print(" : création d'un marqueur d'émission");
            this.marqueurEmission = new Paire(p.ObtenirNbrSeq(), Calendar.getInstance().getTimeInMillis());
        }else{
            System.out.print(" : a déjà un marqueur d'émission");
        }
        System.out.println("");
    }

    /*
     Cette methode est appelée à la récéption d'un paquet par l'automate
     * Si le paquet lu correspond à l'aquittement du marqeur d'émission, alors c'est notre marqeur de récéption
     */
    public void lirePaquet(Paquet p) {
        System.out.print(this + " interception d'un paquet à la lecture");
        //Si le paquet recu est un aquittement
        if (p.ObtenirAck()) {
            System.out.print(" : de type ACK");
            //Si il existe un marqeur de début
            if (this.marqueurEmission != null) {
                //Si le paquet recu est notre marqeur de fin
                if (p.ObtenirNbrAcc() == this.marqueurEmission.getKey()) {
                    System.out.print("/On a trouvé le marqueur de fin");
                    //Alors on créé notre marqueur de fin
                    this.marqueurReception = new Paire(p.ObtenirNbrSeq(), Calendar.getInstance().getTimeInMillis());
                } else {
                    System.out.print("/Pas trouvé le marqueur de fin");
                }
            }
        }
        System.out.println(".");
    }

    /*
     * Donne le temps d'attente avant une réemission d'un paquet
     */
    public synchronized int tempsAttenteAvantReemission() {
        return this.rto < 1 ? 1 : (this.rto >= 60 ? 60 : (int) this.rto);
    }

    private void calcul() {
        if (this.marqueurEmission != null && this.marqueurReception != null) {
            this.rtt = this.marqueurReception.getValue() - this.marqueurEmission.getValue();
            this.rtt /= 1000;
            this.marqueurEmission = null;
            this.marqueurReception = null;
            if (this.first) {
                this.srtt = this.rtt;
                this.rttVar = this.rtt / 2;
                this.rto = this.srtt + 4 * this.rttVar;
                return;
            }
            this.rttVar = (1 - Temporisation.BETA) * this.rttVar + Temporisation.BETA * Math.abs(this.srtt - this.rtt);
            this.srtt = (1 - Temporisation.ALPHA) * this.srtt + Temporisation.ALPHA * this.rtt;
            this.rto = this.srtt + 4 * this.rttVar;
        }
    }

    @Override
    public void run() {
        while (this.alive) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Temporisation.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.calcul();
        }
    }

    @Override
    public String toString() {
        return "Objet de temporisation (" + this.tempsAttenteAvantReemission() + "s)";
    }

    public synchronized void fermer() {
        System.out.println(this + " fermeture ....");
        this.alive = false;
    }

    private class Paire<K, V> implements Map.Entry<K, V> {

        private final K key;
        private V value;

        public Paire(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException("Not supported yet : Pair<K,V>");
        }

        @Override
        public String toString() {
            return "Paire{" + "key=" + key + ", value=" + value + '}';
        }
    }
}
