
import connexion.Client;
import connexion.Serveur;
import connexion.Connexion;
import java.net.Inet4Address;
import java.net.UnknownHostException;
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
public class Main {

    public static void main(String[] args) {
        int port = 60000;
        Inet4Address ip;
        try {
            ip = (Inet4Address) Inet4Address.getLocalHost();
            
            
            Serveur s1 = Connexion.creerServeur(port);
            Serveur s2 = Connexion.creerServeur(port + 1);
            Thread.sleep(100);
            Client c1 = Connexion.creerClient(ip, port);
            Client c2 = Connexion.creerClient(ip, port);
            Client c3 = Connexion.creerClient(ip, port + 1);


        } catch (UnknownHostException ex) {
          //  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }




    }
}
