/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Temporisation.Temporisation;
import connexion.Paquet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greg
 */
public class TestTemporisation {

    public static ArrayList<Paquet> creerListeEnvoie() {
        ArrayList<Paquet> l = new ArrayList<>();
        Paquet p;
        for (int i = 0; i <= 2; i++) {
            p = new Paquet();
            p.CreerPaquet();
            p.MettreNbrSeq(i);
            l.add(p);
        }
        return l;
    }

    public static ArrayList<Paquet> creerListeReception() {
        ArrayList<Paquet> l = new ArrayList<>();
        Paquet p;
        for (int i = 0; i <= 2; i++) {
            p = new Paquet();
            p.CreerPaquet();
            p.MettreAck(true);
            p.MettreNbrAcc(i);
            l.add(p);
        }
        return l;
    }

    public static void attendre() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestTemporisation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        /**
         * ************************************************************************************
         */
        System.out.println("______________Création d'un objet de Temporisation__________");
        Temporisation tempo = new Temporisation();
        Thread t = new Thread(tempo);
        t.start();
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Demande du temps de retransmition______________");
        System.out.println("temps : " + tempo.tempsAttenteAvantReemission() + " seconde(s)");
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Envoie d'un paquet_____________________________");
        tempo.ecriturePaquet(TestTemporisation.creerListeEnvoie().get(0));
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Demande du temps de retransmition______________");
        System.out.println("temps : " + tempo.tempsAttenteAvantReemission() + " seconde(s)");
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Lecture d'un paquet_____________________________");
        tempo.lirePaquet(TestTemporisation.creerListeReception().get(0));
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Demande du temps de retransmition______________");
        System.out.println("temps : " + tempo.tempsAttenteAvantReemission() + " seconde(s)");
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Envoie de 100 paquets__________________________");
        for (Paquet p : TestTemporisation.creerListeEnvoie()) {
            tempo.ecriturePaquet(p);
        }
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Demande du temps de retransmition______________");
        System.out.println("temps : " + tempo.tempsAttenteAvantReemission() + " seconde(s)");
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Lecture de 100 paquets__________________________");
        for (Paquet p : TestTemporisation.creerListeReception()) {
            tempo.lirePaquet(p);
        }
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Demande du temps de retransmition______________");
        System.out.println("temps : " + tempo.tempsAttenteAvantReemission() + " seconde(s)");
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Lecture+Ecriture de 100 paquets________________");
        for (Paquet p : TestTemporisation.creerListeEnvoie()) {
            tempo.ecriturePaquet(p);
        }
        for (Paquet p : TestTemporisation.creerListeReception()) {
            tempo.lirePaquet(p);
        }
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Demande du temps de retransmition______________");
        System.out.println("temps : " + tempo.tempsAttenteAvantReemission() + " seconde(s)");
        TestTemporisation.attendre();
        /**
         * ************************************************************************************
         */
        System.out.println("______________Fermeture______________________________________");
        tempo.fermer();
    }
}
