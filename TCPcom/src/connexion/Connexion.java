package connexion;

import java.net.Inet4Address;

/**
 *
 * @author greg
 */
public class Connexion {

    public static Serveur creerServeur(int port) {
        Serveur s = new Serveur(port);
        (new Thread(s)).start();
        return s;
    }

    public static Client creerClient(Inet4Address ipserveur, int portServeur) {
        Client c = new Client(ipserveur, portServeur);
        (new Thread(c)).start();
        return c;
    }
}
