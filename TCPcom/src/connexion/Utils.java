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
}
