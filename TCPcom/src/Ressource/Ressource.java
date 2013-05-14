/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressource;

/**
 *
 * @author greg
 */
public interface Ressource {
    /*Gestion du mode PAS à PAS*/
    public static boolean MODE_NORMAL = false;
    public static boolean MODE_STEP_BY_STEP = true;
    /*Gestion des états d'un automate*/
    public static int ETAT_CLOSED = 0;
    public static int ETAT_LISTEN = 1;
    public static int ETAT_SYN_RCVD = 2;
    public static int ETAT_SYN_SENT = 3;
    public static int ETAT_ESTABLISHED = 4;
    public static int ETAT_FIN_WAIT_1 = 5;
    public static int ETAT_FIN_WAIT_2 = 6;
    public static int ETAT_CLOSE_WAIT = 7;
    public static int ETAT_LAST_ACK = 8;
    public static int ETAT_CLOSING = 9;
    public static int ETAT_TIME_WAIT = 10;
    /*Pour la temporisation*/
    public static int TEMPORISATION_TEMPS_DEFAUT = 300; // Correspond à 300 secondes
}
