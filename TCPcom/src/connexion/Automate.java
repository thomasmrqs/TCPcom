package connexion;

import Ressource.Ressource;
import graphique.GUI;

import java.io.File;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automate implements Runnable {

    public boolean bypass = false; // permet de debloquer le mode pas a pas
    private int etatCourant = Ressource.ETAT_CLOSED;
    private TCB tcb = null;
    private Connexion connexion = null;
    private Utils utils = null;
    private boolean mod = false; //Si true = clien
    private int port_dist = 0;
    private int port_loc = 0;
    private String ip_dist = null;
    private boolean openOk = false;
    private Stack<Paquet> bufferPaquet = null;
    private File fichier = null;
    private boolean modePasAPas = false;
    private int currentstate = 0;

    public Automate() {
        this.modePasAPas = false;
        this.setBufferPaquet(new Stack<Paquet>());
        utils = new Utils();
    }

    /* change l'etat de l'automate, prend en compte la continuite des etats (on peut pas passer de closed a established) */
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
        String state = ((this.getMod()) ? "client" : "serveur");
        switch (this.etatCourant) {
            case Ressource.ETAT_CLOSE_WAIT:
                System.out.println("Etat " + state + " actuel : ETAT_CLOSE_WAIT");
                break;
            case Ressource.ETAT_CLOSED:
                System.out.println("Etat " + state + " actuel : ETAT_CLOSED");
                break;
            case Ressource.ETAT_CLOSING:
                System.out.println("Etat " + state + " actuel : ETAT_CLOSING");
                break;
            case Ressource.ETAT_ESTABLISHED:
                System.out.println("Etat " + state + " actuel : ETAT_ESTABLISHED");
                break;
            case Ressource.ETAT_FIN_WAIT_1:
                System.out.println("Etat " + state + " actuel : ETAT_FIN_WAIT_1");
                break;
            case Ressource.ETAT_FIN_WAIT_2:
                System.out.println("Etat " + state + " actuel : ETAT_FIN_WAIT_2");
                break;
            case Ressource.ETAT_LAST_ACK:
                System.out.println("Etat " + state + " actuel : ETAT_LAST_ACK");
                break;
            case Ressource.ETAT_LISTEN:
                System.out.println("Etat " + state + " actuel : ETAT_LISTEN");
                break;
            case Ressource.ETAT_SYN_RCVD:
                System.out.println("Etat " + state + " actuel : ETAT_SYN_RCVD");
                break;
            case Ressource.ETAT_SYN_SENT:
                System.out.println("Etat " + state + " actuel : ETAT_SYN_SENT");
                break;
            case Ressource.ETAT_TIME_WAIT:
                System.out.println("Etat " + state + " actuel : ETAT_TIME_WAIT");
                break;
            default:
                System.out.println("Etat inconnu");
                break;
        }
    }

    public String open(int port_local, String ip_distant, int port_ser, boolean mode) {
        //mode True = client
        this.setMod(mode);
        this.setIp_dist(ip_distant);
        this.setPort_local(port_local);
        this.setPort_dist(port_ser);
        if (mode == true) {//Client
            Client c = null;
            try {
                c = GestionDesConnexions.get().lancerClient(ip_distant, port_ser, port_local);
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
            this.getTcb().setNomLocalConnexion("con_" + this.getTcb().getConnexion().getIpLocale() + ":" + port_local + "_" + ip_distant + ":" + port_ser);
            this.getTcb().setNomDistantConnexion("con_" + ip_distant + ":" + port_ser + "_" + this.getTcb().getConnexion().getIpLocale() + ":" + port_local);
            this.setOpenOk(true);
            return getTcb().getNomLocalConnexion();
        }
        //Dans le cas d'un serveur
        try {
            this.setTcb(new TCB(connexion));
            this.etatCourant = Ressource.ETAT_LISTEN;
        } catch (Exception e) {
            System.out.println("Automate::Probleme serveur");
        }
        this.setOpenOk(true);
        this.getTcb().setNomLocalConnexion("loc" + this.getTcb().getConnexion().getIpLocale() + ip_distant);
        return getTcb().getNomLocalConnexion();
    }

    /* Depuis Closed */
    public void closedToSynSent() {
        if (this.getMod()) {
            this.getTcb().initISS();
            Paquet p = new Paquet(this.getPort_local(), this.getPort_dist());
            p.MettreSyn(true);
            p.MettreNbrSeq(this.getTcb().getSEG_SEQ());
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_SYN_SENT;
        }
    }

    /* Depuis Closed */
    public void closedToListen() {
        if (!this.getMod()) {
            this.getTcb().initISS();
            this.etatCourant = Ressource.ETAT_LISTEN;
        }
    }

    /* Depuis Listen */
    public void listenToSynRec() {
        if (this.getMod()) {
            return;
        }
        if (this.getIp_dist() != null && this.getPort_dist() != 0) {
            if (this.getTcb().getConnexion().getIpDistante() != this.getIp_dist()
                    || this.getTcb().getConnexion().getPortDistant() != this.getPort_dist()) {
                return;
            }
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirSyn()) {
            this.getTcb().initACK(p.ObtenirNbrSeq());
            p.MettreSyn(true);
            p.MettreAck(true);
            p.MettreNbrSeq(this.getTcb().getSEG_SEQ());
            p.MettreNbrAcc(this.getTcb().getSEG_ACK());
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_SYN_RCVD;
        }
    }

    /* Depuis Listen */
    public void listenToClosed() {
        if (!this.getTcb().getConnexion().isAlive()) {
            this.getTcb().setAbort(true);
            this.getTcb().resetTCB();
            this.etatCourant = Ressource.ETAT_CLOSED;
        }
    }

    /* Depuis Syn Sent */
    public void synSentToEstablished() {
        if (!this.getMod()) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }

        /* ajout bapt */

        /* ouverture simultannee */

        if (!p.ObtenirAck() && p.ObtenirSyn()) {
            /* CHECK DES NUMS D'ACK ET DE SEQ A FAIRE */
            p.MettreAck(true);
            p.MettreSyn(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_SYN_RCVD;
            return;
        }

        /* cas classique */

        if (p.ObtenirSyn() && p.ObtenirAck()) {
            this.getTcb().incrSEQ();
            if (this.getTcb().checkACK(p)) {
                this.getTcb().initACK(p.ObtenirNbrSeq());
                p.MettreAck(true);
                p.MettreSyn(false);
                p.MettreNbrAcc(this.getTcb().getSEG_ACK());
                p.MettreNbrSeq(this.getTcb().getSEG_SEQ());
                p.CreerPaquet();
                this.getTcb().getConnexion().ecrirePaquet(p);
                this.etatCourant = Ressource.ETAT_ESTABLISHED;
            } else {
                /* PAQUET FOIREUX : DEMANDE DE RENVOI DE PAQUET x*/
            }
        }

        /* fin ajout/modif bapt */
    }

    /* Depuis Syn Sent */
    public void synSentToClosed() {
        if (!this.getMod()) {
            return;
        }

        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }

        if (p.ObtenirRst()) {
            p.MettreFin(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_CLOSED;
        }
    }

    /* Depuis Syn Received */
    public void synRevToEstablished() {
        if (this.getMod()) {
            return;
        }
        if (this.getIp_dist() != null && this.getPort_dist() != 0) {
            if (this.getTcb().getConnexion().getIpDistante() != this.getIp_dist()
                    || this.getTcb().getConnexion().getPortDistant() != this.getPort_dist()) {
                return;
            }

        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }

        /* Ajout bapt pour deco */
        if (p.ObtenirRst()) {
            /* analyse du paquet pour savoir si c'est un abort ou un close */
            this.etatCourant = Ressource.ETAT_FIN_WAIT_1;
        } else {
            /* Fin ajout bapt */
            this.getTcb().incrSEQ();
            if (p.ObtenirAck() && this.getTcb().checkACK(p)) {
                this.etatCourant = Ressource.ETAT_ESTABLISHED;
            }
        }
    }

    /* Depuis Established */
    public void estaToCloseWait() {
        if (this.getMod()) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin()) {
            p.MettreAck(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_CLOSE_WAIT;
        }
    }

    public void estaToFinWait1() {
        if (this.getMod()) {
            Paquet p = new Paquet(this.getPort_local(), this.getPort_dist());
            p.MettreFin(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.etatCourant = Ressource.ETAT_FIN_WAIT_1;

        }
    }

    /* Depuis Fin Wait 1 */
    public void finWait1ToFinWait2() {
        if (!this.getMod()) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin()) {
            if (p.ObtenirAck()) {
                this.etatCourant = Ressource.ETAT_FIN_WAIT_1;
            }
        }
    }

    /* Depuis Close Wait */
    public void closeWaitToLastAck() {
        if (this.getMod()) {
            return;
        }
        Paquet p = new Paquet(this.getPort_local(), this.getPort_dist());
        p.MettreFin(true);
        p.CreerPaquet();
        this.getTcb().getConnexion().ecrirePaquet(p);
        this.etatCourant = Ressource.ETAT_LAST_ACK;

    }

    /* Depuis Fin Wait 2 */
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

    /* Depuis Time Wait */
    public void timeWaitToClosed() {
        if (!this.getMod()) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin()) {
            p.MettreAck(true);
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            this.getTcb().resetTCB();
            this.etatCourant = Ressource.ETAT_CLOSED;
        }
    }

    /* Depuis Last Ack */
    public void lastAckToClosed() {
        if (this.getMod()) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin() && p.ObtenirAck()) {
            this.getTcb().resetTCB();
            this.etatCourant = Ressource.ETAT_CLOSED;
        }
    }

    public void changerEtat() throws InterruptedException 
    {
        while (true) 
        {
            afficheEtatCourant();
            if (this.modePasAPas) 
            {
                Thread.sleep(200);
                GUI.get().obtainCard(this).getConsole().insertLine(" attente ... ", "Blue");
            
                while (!this.bypass && currentstate <= 10) 
                {
                    Thread.sleep(200);
                    GUI.get().obtainCard(this).getPanel_automate().update_states(utils.conversionEtat(currentstate));
                    currentstate++;
                }
                this.bypass = false;
              
                
                
                
            }
            switch (this.etatCourant) {
                case Ressource.ETAT_CLOSE_WAIT:
                    this.closeWaitToLastAck();
                    break;
                case Ressource.ETAT_CLOSED:

                    this.closedToListen();
                    this.closedToSynSent();
                    break;
                case Ressource.ETAT_CLOSING:
                    break;
                case Ressource.ETAT_ESTABLISHED:

                    /* mode serveur : verification des infos du client pour ne pas accepter n'importe qui */
                    if (!this.getMod()) {
                        if (this.getIp_dist() == this.getTcb().getConnexion().getIpDistante()
                                && this.getPort_dist() == this.getTcb().getConnexion().getPortDistant()) {
                            /*OK*/
                        } else {
                            break;
                        }
                    }
                    /*
                     Paquet p = this.getTcb().getConnexion().lireDernierMessage();
                	
                     if (this.getFile())
                     {
                     Utils.ecrireDansFichier(p, this.getFile()); 
                     }
                     else
                     {
                     this.getBufferPaquet().push(p);
                     while (!p.ObtenirFin())
                     {
                	
                     }
                     }
                     */
                    //this.estaToCloseWait();

                    // Si j'envoie une commande close 
                    // Rajouter ici la commande close !!!!!!!!
                    //this.estaToFinWait1();
                    break;
                case Ressource.ETAT_FIN_WAIT_1:
                    this.finWait1ToFinWait2();
                    break;
                case Ressource.ETAT_FIN_WAIT_2:
                    this.finWait2ToTimeWait();
                    break;
                case Ressource.ETAT_LAST_ACK:
                    this.lastAckToClosed();
                    break;
                case Ressource.ETAT_LISTEN:
                    this.listenToSynRec();
                    this.listenToClosed();
                    break;
                case Ressource.ETAT_SYN_RCVD:
                    this.synRevToEstablished();
                    break;
                case Ressource.ETAT_SYN_SENT:
                    this.synSentToEstablished();
                    break;
                case Ressource.ETAT_TIME_WAIT:
                    this.timeWaitToClosed();
                    break;
                default:
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

    @Override
    public String toString() {
        return this.getTcb().getNomLocalConnexion();
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public boolean getMod() {
        return mod;
    }

    public void setMod(boolean mode) {
        this.mod = mode;
    }

    public int getPort_dist() {
        return port_dist;
    }

    public void setPort_dist(int port_distant) {
        this.port_dist = port_distant;
    }

    public int getPort_local() {
        return port_loc;
    }

    public void setPort_local(int port_local) {
        this.port_loc = port_local;
    }

    public String getIp_dist() {
        return ip_dist;
    }

    public void setIp_dist(String ip_distant) {
        this.ip_dist = ip_distant;
    }

    public Stack<Paquet> getBufferPaquet() {
        return bufferPaquet;
    }

    public void setBufferPaquet(Stack<Paquet> bufferPaquet) {
        this.bufferPaquet = bufferPaquet;
    }

    public boolean getOpenOk() {
        return openOk;
    }

    public void setOpenOk(boolean ok) {
        this.openOk = ok;
    }

    public void setEtatCourant(int etat) {
        this.etatCourant = etat;
    }

    public int getEtatCourant() {
        return this.etatCourant;
    }

    public TCB getTcb() {
        return tcb;
    }

    public void setTcb(TCB tcb) {
        this.tcb = tcb;
    }

    public File getFichier() {
        return fichier;
    }

    public void setFichier(File fichier) {
        this.fichier = fichier;
    }
    
    public boolean isModePasAPas() {
        return modePasAPas;
    }

    public void setModePasAPas(boolean modePasAPas) {
        this.modePasAPas = modePasAPas;
    }
}
