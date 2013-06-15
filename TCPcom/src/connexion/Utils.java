package connexion;
import Ressource.Ressource;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author greg
 */
public class Utils {

    private static int ID_SERVEUR = 0;

    public static int creerIdentifiantServeur() {
        return ++Utils.ID_SERVEUR;
    }
    private static int ID_CLIENT = 0;

    public static int creerIdentifiantClient() {
        return ++Utils.ID_CLIENT;
    }

    public static byte changerValeurBit(int position, byte octet, boolean valeur) {
        if (valeur) {
            octet |= (01 << position);
        } else {
            octet &= ~(01 << position);
        }
        return octet;
    }

    public static boolean bitEstVrai(byte octet, int position) {
        return ((octet >> position) & 01) == 01 ? true : false;
    }

    public static String afficherOctet(byte b) {
        //System.out.println("test greg");
        String res = "octet : [";
        for (int i = 0; i < 8; i++) {
            //System.out.println("rest = " + res);
            res += Utils.bitEstVrai(b, i) + "";
            if (i < 7) {
                res += ";";
            }
        }
        return res + "]";
    }
    
    public static byte[] complement(String str){
        byte[] tab = new byte[str.getBytes().length];
        int i = 0;
        for (byte d : str.getBytes()){
            d ^= 0XFF;
            tab[i++] = d;
        }
        return tab;
    } 
    
    public static byte[] complement(byte[] str){
        byte[] tab = new byte[str.length];
        int i = 0;
        for (byte d : str){
            d ^= 0XFF;
            tab[i++] = d;
        }
        return tab;
    } 
    
    /*
     
      if (this.getTcb().getConnexion() instanceof Client) {
                GUI.get().obtainCard(this).getPanel_automate().update_states(Utils.conversionEtat(this.etatCourant));
            }
            
            Thread.sleep(200);
     */
    public static String conversionEtat(int i){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(i){
            case Ressource.ETAT_CLOSED :
                return "CLOSED_INIT";
            case Ressource.ETAT_LISTEN:
                return "LISTEN";
            case Ressource.ETAT_SYN_RCVD:
                return "SYN_RCVD";
            case Ressource.ETAT_SYN_SENT:
                return "SYN_SENT";
            case Ressource.ETAT_ESTABLISHED:
                return "ESTAB";
            case Ressource.ETAT_FIN_WAIT_1:
                return "FIN_WAIT_1";
            case Ressource.ETAT_CLOSE_WAIT:
                return "CLOSE_WAIT";
            case Ressource.ETAT_FIN_WAIT_2:
                return "FIN_WAIT_2";
            case Ressource.ETAT_CLOSING:
                return "CLOSING";
            case Ressource.ETAT_LAST_ACK:
                return "LAST_ACK";
            case Ressource.ETAT_TIME_WAIT:
                return "TIME_WAIT";
        }
        return "";
        
    }
}
