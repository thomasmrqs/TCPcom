package connexion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author greg
 */
public class Utils {
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
        String res = "octet : [";
        for (int i = 0; i < 8; i++) {
            res += Utils.bitEstVrai(b, i) + "";
            if (i < 7) {
                res += ";";
            }
        }
        return res + "]";
    }
}
