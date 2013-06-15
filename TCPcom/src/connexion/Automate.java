package connexion;

import Ressource.Ressource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automate implements Runnable {

    /* attribut de l'etat courant */
    private int etatCourant = Ressource.ETAT_CLOSED;
    private TCB tcb = null;
    private Connexion connexion;
    private boolean mod = false; //Si true = client
    private int port_dist = 0;
    private String ip_dist = null;
    private boolean openOk = false;

    public boolean getMod() {
        return mod;
    }

    public void setMod(boolean mod) {
        this.mod = mod;
    }

    public int getPort_dist() {
        return port_dist;
    }

    public void setPort_loc(int port_distant) {
        this.port_dist = port_distant;
    }

    public String getIp_dist() {
        return ip_dist;
    }

    public void setIp_dist(String ip_distant) {
        this.ip_dist = ip_distant;
    }

    public Automate() {
    }

    public void setEtatCourant(int etat) {
        this.etatCourant = etat;
    }

    public int getEtatCourant() {
        return this.etatCourant;
    }

    /*change l'etat de l'automate, prend en compte la continuit� des etats (on peut pas passer de closed a established)*/
    public void validerChangementEtat(int etat) {

        if (this.etatCourant == Ressource.ETAT_CLOSED) {
            if (etat != Ressource.ETAT_LISTEN || etat != Ressource.ETAT_SYN_SENT) {
                System.out.println("Etat impossible a atteindre depuis CLOSED");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_LISTEN) {
            if (etat != Ressource.ETAT_CLOSED || etat != Ressource.ETAT_SYN_SENT || etat != Ressource.ETAT_SYN_RCVD) {
                System.out.println("Etat impossible a atteindre depuis LISTEN");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_SYN_SENT) {
            if (etat != Ressource.ETAT_CLOSED || etat != Ressource.ETAT_SYN_RCVD || etat != Ressource.ETAT_ESTABLISHED) {
                System.out.println("Etat impossible a atteindre depuis SYN_SENT");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_SYN_RCVD) {
            if (etat != Ressource.ETAT_ESTABLISHED || etat != Ressource.ETAT_FIN_WAIT_1) {
                System.out.println("Etat impossible a atteindre depuis SYN_RCVD");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_ESTABLISHED) {
            if (etat != Ressource.ETAT_FIN_WAIT_1 || etat != Ressource.ETAT_FIN_WAIT_2) {
                System.out.println("Etat impossible a atteindre depuis ESTABLISHED");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_FIN_WAIT_1) {
            if (etat != Ressource.ETAT_FIN_WAIT_2 || etat != Ressource.ETAT_CLOSING) {
                System.out.println("Etat impossible a atteindre depuis FIN WAIT 1");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_FIN_WAIT_2) {
            if (etat != Ressource.ETAT_TIME_WAIT) {
                System.out.println("Etat impossible a atteindre depuis FIN WAIT 2");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_CLOSING) {
            if (etat != Ressource.ETAT_TIME_WAIT) {
                System.out.println("Etat impossible a atteindre depuis CLOSING");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_TIME_WAIT) {
            if (etat != Ressource.ETAT_CLOSED) {
                System.out.println("Etat impossible a atteindre depuis TIME WAIT");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_CLOSE_WAIT) {
            if (etat != Ressource.ETAT_LAST_ACK) {
                System.out.println("Etat impossible a atteindre depuis CLOSE WAIT");
            } else {
                setEtatCourant(etat);
            }
        } else if (this.etatCourant == Ressource.ETAT_LAST_ACK) {
            if (etat != Ressource.ETAT_CLOSED) {
                System.out.println("Etat impossible a atteindre depuis LAST ACK");
            } else {
                setEtatCourant(etat);
            }
        } else {
            System.out.println("Etat inconnu");
        }
    }

    public void afficheEtatCourant() {
        switch (this.etatCourant) {
            case Ressource.ETAT_CLOSE_WAIT:
                System.out.println("Etat actuel : ETAT_CLOSE_WAIT");
                break;
            case Ressource.ETAT_CLOSED:
                System.out.println("Etat actuel : ETAT_CLOSED");
                break;
            case Ressource.ETAT_CLOSING:
                System.out.println("Etat actuel : ETAT_CLOSING");
                break;
            case Ressource.ETAT_ESTABLISHED:
                System.out.println("Etat actuel : ETAT_ESTABLISHED");
                break;
            case Ressource.ETAT_FIN_WAIT_1:
                System.out.println("Etat actuel : ETAT_FIN_WAIT_1");
                break;
            case Ressource.ETAT_FIN_WAIT_2:
                System.out.println("Etat actuel : ETAT_FIN_WAIT_2");
                break;
            case Ressource.ETAT_LAST_ACK:
                System.out.println("Etat actuel : ETAT_LAST_ACK");
                break;
            case Ressource.ETAT_LISTEN:
                System.out.println("Etat actuel : ETAT_LISTEN");
                break;
            case Ressource.ETAT_SYN_RCVD:
                System.out.println("Etat actuel : ETAT_SYN_RCVD");
                break;
            case Ressource.ETAT_SYN_SENT:
                System.out.println("Etat actuel : ETAT_SYN_SENT");
                break;
            case Ressource.ETAT_TIME_WAIT:
                System.out.println("Etat actuel : ETAT_TIME_WAIT");
                break;
            default:
                System.out.println("Etat inconnu");
                break;
        }
    }

    public TCB getTcb() {
        return tcb;
    }

    public void setTcb(TCB tcb) {
        this.tcb = tcb;
    }

   public String open(int port_local, String ip_distant, int port_ser, boolean mode) {
        //mode True = client
        this.setMod(mode);
        this.setIp_dist(ip_distant);
        this.setPort_loc(port_local);
        if (mode == true) {//Client
            Client c = null;
            try {
                c = GestionDesConnexions.get().lancerClient("toto", ip_distant, port_ser);
                this.setTcb(new TCB(c));
                this.connexion = c;
                this.etatCourant = Ressource.ETAT_CLOSED;
            } catch (Exception e) {
                System.out.println("Automate::J'ai tout casse");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Automate.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.getTcb().setNomLocalConnexion("loc" + this.getTcb().getConnexion().getIpLocale()+ ip_distant );
            this.setOpenOk(true);
            return getTcb().getNomLocalConnexion();
        }
        //Dans le cas d'un serveur
        try 
        {
        this.setTcb(new TCB (connexion));
        this.etatCourant = Ressource.ETAT_LISTEN;
        }
        catch (Exception e) {
            System.out.println("Automate::Probleme serveur");
        }
        this.setOpenOk(true);
        this.getTcb().setNomLocalConnexion("loc" + ip_distant + this.getTcb().getConnexion().getIpLocale());
        return getTcb().getNomLocalConnexion();
    }

    /* Changer l'�tat de CLOSED � SYN_SENT */
    public void closedToSynSent() {
        if (this.getMod() == true) {
            int port_ser = this.getTcb().getConnexion().portDistant;
            /* CA FAIT TOUT CASSEEEEEE */
            //int port_client = this.getTcb().getConnexion().socket.getLocalPort();
            Paquet p = new Paquet(100005, port_ser);
            p.MettreSyn(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_SYN_SENT;

        }
    }

    public void synSentToEstablished() {
        if (this.getMod() == false) {
            return;
        }
        System.out.println("debut");
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            System.out.println("null");
            return;
        }
        if (p.ObtenirSyn() == true) {
            if (p.ObtenirAck() == true) {
                this.etatCourant = Ressource.ETAT_ESTABLISHED;
                p.MettreSyn(false);
                p.CreerPaquet();
                this.getTcb().getConnexion().ecrirePaquet(p);
            }
        }
    }

    public boolean getOpenOk() {
        return openOk;
    }

    public void setOpenOk(boolean ok) {
        this.openOk = ok;
    }

    public void closedToListen() {
        if (this.getMod() == false) {
            this.etatCourant = Ressource.ETAT_LISTEN;
        }
    }

    public void listenToSynRec() {
        if (this.getMod() == true) {
            return;
        }
        if (this.getIp_dist() != null && this.getPort_dist() != 0) {
            if (this.getTcb().getConnexion().getIpDistante() != this.getIp_dist()) {
                return;
            }
            if (this.getTcb().getConnexion().getPortDistant() != this.getPort_dist()) {
                return;
            }
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirSyn() == true) {
            this.etatCourant = Ressource.ETAT_SYN_RCVD;
            p.MettreSyn(true);
            p.MettreAck(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
        }
    }

    public void synRevToEstablished() {
        if (this.getMod() == true) {
            return;
        }
        if (this.getIp_dist() != null && this.getPort_dist() != 0) {
            if (this.getTcb().getConnexion().getIpDistante() != this.getIp_dist()) {
                return;
            }
            if (this.getTcb().getConnexion().getPortDistant() != this.getPort_dist()) {
                return;
            }
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirAck() == true) {
            this.etatCourant = Ressource.ETAT_ESTABLISHED;
        }
    }

    public void listenToClosed() {
        if (this.getTcb().getConnexion().isAlive() == false) {
            this.etatCourant = Ressource.ETAT_CLOSED;
        }

    }

    public void estaToCloseWait() {
        if (this.getMod() == true) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin() == true) {
            p.MettreAck(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_CLOSE_WAIT;
        }
    }

    public void estaToFinWait1() {
        if (this.getMod() == true) {
            int port_ser = this.getTcb().getConnexion().portDistant;
            Paquet p = new Paquet(100005, port_ser);
            p.MettreFin(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_FIN_WAIT_1;

        }
    }

    public void finWait1ToFinWait2() {
        if (this.getMod() != true) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin() == true) {
            if (p.ObtenirAck() == true) {
                this.etatCourant = Ressource.ETAT_FIN_WAIT_1;
            }
        }
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    /**
     * *************************
     */
    /* GREGGGGGGGGGGGGGGGGGGGGGG*/
    /**
     * **************************
     */
    public void closeWaitToLastAck() {
        if (this.getMod() == true) {
            return;
        }
        int port_cli = this.getTcb().getConnexion().portDistant;
        Paquet p = new Paquet(100005, port_cli);
        p.MettreFin(true);
        p.CreerPaquet();
        this.getTcb().getConnexion().ecrirePaquet(p);
        this.etatCourant = Ressource.ETAT_LAST_ACK;

    }

    public void finWait2ToTimeWait() {
        /**
         * ****************************
         */
        /*          TIMER              */
        /**
         * ****************************
         */
        return;
    }

    public void timeWaitToClosed() {
        if (this.getMod() != true) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin() == true) {
            p.MettreAck(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_TIME_WAIT;
        }
    }

    public void lastAckToClosed() {
        if (this.getMod() == true) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin() == true) {
            if (p.ObtenirAck() == true) {
                this.etatCourant = Ressource.ETAT_CLOSED;
            }
        }
    }

    public void changerEtat() throws InterruptedException {
        /* CHANGER LE TRUE */
        while (true) {
            afficheEtatCourant();
            System.out.println("before switch");
            switch (this.etatCourant) {
                case Ressource.ETAT_CLOSE_WAIT:
                    this.closeWaitToLastAck();
                    // System.out.println("Etat actuel : ETAT_CLOSE_WAIT");
                    break;
                case Ressource.ETAT_CLOSED:

                    this.closedToListen();
                    this.closedToSynSent();
                    break;
                case Ressource.ETAT_CLOSING:
                    //System.out.println("Etat actuel : ETAT_CLOSING");
                    break;
                case Ressource.ETAT_ESTABLISHED:
                    this.estaToCloseWait();


                    // Si j'envoie une commande close 
                    // Rajouter ici la commande close !!!!!!!!
                    this.estaToFinWait1();

                    //System.out.println("Etat actuel : ETAT_ESTABLISHED");
                    break;
                case Ressource.ETAT_FIN_WAIT_1:
                    this.finWait1ToFinWait2();
                    //System.out.println("Etat actuel : ETAT_FIN_WAIT_1");
                    break;
                case Ressource.ETAT_FIN_WAIT_2:
                    this.finWait2ToTimeWait();
                    //System.out.println("Etat actuel : ETAT_FIN_WAIT_2");
                    break;
                case Ressource.ETAT_LAST_ACK:
                    this.lastAckToClosed();
                    //System.out.println("Etat actuel : ETAT_LAST_ACK");
                    break;
                case Ressource.ETAT_LISTEN:
                    this.listenToSynRec();
                    this.listenToClosed();
                    //if (this.etatCourant == Ressource.ETAT_LISTEN) {
                    //    this.setTcb(new TCB(getCli()));
                    // }
                    System.out.println("Etat actuel : ETAT_LISTEN");
                    break;
                case Ressource.ETAT_SYN_RCVD:
                    this.synRevToEstablished();
                    System.out.println("Etat actuel : ETAT_SYN_RCVD");
                    break;
                case Ressource.ETAT_SYN_SENT:
                    this.synSentToEstablished();
                    //System.out.println("Etat actuel : ETAT_SYN_SENT");
                    System.out.println("youpi ?");
                    break;
                case Ressource.ETAT_TIME_WAIT:
                    this.timeWaitToClosed();
                    //System.out.println("Etat actuel : ETAT_TIME_WAIT");
                    break;
                default:
                    //System.out.println("Etat inconnu");
                    break;
            }
            Thread.sleep(100);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.changerEtat();
            } catch (InterruptedException ex) {
                Logger.getLogger(Automate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
