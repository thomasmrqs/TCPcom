package connexion;

import Ressource.Ressource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static byte[] complement(String str) {
        byte[] tab = new byte[str.getBytes().length];
        int i = 0;
        for (byte d : str.getBytes()) {
            d ^= 0XFF;
            tab[i++] = d;
        }
        return tab;
    }

    public static byte[] complement(byte[] str) {
        byte[] tab = new byte[str.length];
        int i = 0;
        for (byte d : str) {
            d ^= 0XFF;
            tab[i++] = d;
        }
        return tab;
    }

    public static int[] byteArrayToIntarray(byte[] barray) {
        int[] iarray = new int[barray.length];
        int i = 0;
        for (byte b : barray) {
            iarray[i++] = b & 0xff;
        }
        // "and" with 0xff since bytes are signed in java
        return iarray;
    }

    public static void ecrireDansFichier(Paquet p, File f) {
        FileWriter fileWritter = null;
        BufferedWriter bufferWritter = null;
        try {
            fileWritter = new FileWriter(f.getName(), true);
            bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(p.ObtenirDonnee());
            bufferWritter.close();
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (bufferWritter != null) {
                    bufferWritter.close();
                }
                if (fileWritter != null) {
                    fileWritter.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    public static  String conversionEtat(int i) {
        switch (i) {
            case Ressource.ETAT_CLOSED:
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
    // supporte les adresses IPV4 & IPV6
    public Boolean CheckAdresseIp (String Ip)
    {
    	if (Ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))|((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((b((25[0-5])|(1d{2})|(2[0-4]d)|(d{1,2}))b).){3}(b((25[0-5])|(1d{2})|(2[0-4]d)|(d{1,2}))b))|(([0-9A-Fa-f]{1,4}:){0,5}:((b((25[0-5])|(1d{2})|(2[0-4]d)|(d{1,2}))b).){3}(b((25[0-5])|(1d{2})|(2[0-4]d)|(d{1,2}))b))|(::([0-9A-Fa-f]{1,4}:){0,5}((b((25[0-5])|(1d{2})|(2[0-4]d)|(d{1,2}))b).){3}(b((25[0-5])|(1d{2})|(2[0-4]d)|(d{1,2}))b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$"))
    	
    		return true;
    	else
    		return false;
    }
    
}
