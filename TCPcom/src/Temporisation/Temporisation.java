/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporisation;

import Ressource.Ressource;
import connexion.Paquet;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author greg
 */
public class Temporisation implements Runnable {
    //Permet de tuer le thread
    private boolean alive;
    //Temporisation de retransmission
    private long rto;
    private long roundTripDelay;
    //Borne supérieure
    private double uBound;
    //Borne inférieure
    private double lBound;
    //Temps de boucle : srtt
    private double srtt;
    private double alpha = 0.8;
    private double beta = 1.5;
    

    public synchronized void fermer() {
        this.alive = false;
    }
    //Attributs utiles
    private int delai;
    private Paire<Paquet, Long> marqueur;//Est le point de repère 
    private Paire<Paquet, Long> marqueur2;//Doit recouvrir le point de repère

    /*Constructeurs*/
    public Temporisation(int tempsParDefaut) {
        if (tempsParDefaut <= 0){
            tempsParDefaut = Ressource.TEMPORISATION_TEMPS_DEFAUT;
        }
        this.roundTripDelay = 1;
        this.delai = tempsParDefaut;
        this.srtt = 1;
        this.uBound = 60; //60 secondes
        this.lBound = 1; // 1 seconde
    }


    /*Interaction avec l'exterieur*/
    public void ecriturePaquet(Paquet p) {
        System.out.println(this + " interception de l'ecriture d'un paquet");
        if (this.marqueur == null) {
            System.out.println(this + " J'ai un nouveau marqueur : start");
            this.initialiserMarqueur(p);
        }
    }

    public void lirePaquet(Paquet p) {
        System.out.println(this + " interception de la lecture d'un paquet");
        if (this.marqueur2 != null){
            System.out.println(this + " Je n'ai pas de marqueur de fin");
            this.determinerInterval(p);
        }
    }
    
    public long tempsAllerRetour(){
        return this.roundTripDelay;
    }
    
    public long tempsAvantRetransmission(){
        return this.rto;
    }
    
    /*Fonctions de calcul */
    private void initialiserMarqueur(Paquet p) {
        this.marqueur = new Paire(p, Calendar.getInstance().getTimeInMillis());
    }

    private void determinerInterval(Paquet p) {
        System.out.println(this + " Il faut determiner si le marqueur de fin est dans l'interval");
        this.marqueur2 = new Paire<>(p, Calendar.getInstance().getTimeInMillis());
    }

    private long calculRoundTripDelay(Paire<Paquet,Long> start, Paire<Paquet,Long> end){
        return start.getValue() - end.getValue();
    }
    
    private void calculTempsDeBoucle(long roundTripDelay){
        this.srtt = (this.alpha * this.srtt) + ((1 - this.alpha) * roundTripDelay);
    }
    
    // Pour executer cette fonction, le srtt doit etre à jour
    private void calculTemporisationRetransmission(){
        this.rto = (long) Math.min(this.uBound, Math.max(this.lBound, this.beta * this.srtt));
    }
    
    /*Boucle infinie*/
    @Override
    public void run() {
        while (this.alive) {
            if (this.marqueur != null && this.marqueur2 != null) {
                this.roundTripDelay = this.calculRoundTripDelay(this.marqueur, this.marqueur2);
                this.calculTempsDeBoucle(this.roundTripDelay);
                this.calculTemporisationRetransmission();
            }
        }
    }
    
    
    

    @Override
    public String toString() {
        return "Objet de temporisation";
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
    }
}
