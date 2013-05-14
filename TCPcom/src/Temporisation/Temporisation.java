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

    public synchronized void fermer() {
        this.alive = false;
    }
    //Attributs utiles
    private int delai;
    private Paire<Paquet, Long> marqueur;//Est le point de repère 
    private Paire<Paquet, Long> marqueur2;//Doit recouvrir le point de repère

    /*Constructeurs*/
    public Temporisation(int tempsParDefaut) {
        this.delai = tempsParDefaut;

    }

    public Temporisation() {
        this.delai = Ressource.TEMPORISATION_TEMPS_DEFAUT;
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
    
    /*Boucle infinie*/
    @Override
    public void run() {
        long roundTripDelay = 0;
        while (this.alive) {
            if (this.marqueur != null && this.marqueur2 != null) {
                roundTripDelay = this.calculRoundTripDelay(this.marqueur, this.marqueur2);
                
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
