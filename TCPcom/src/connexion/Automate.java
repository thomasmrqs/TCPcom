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
    private boolean mod = false; //Si true = client
    private int port_dist = 0;
    private int port_loc = 0;
    private String ip_dist = null;
    private boolean openOk = false;
    private Stack<Paquet> bufferPaquet = null;
    private File fichier = null;
    private boolean modePasAPas = false;

    public Automate() {
        this.modePasAPas = false;
        this.setBufferPaquet(new Stack<Paquet>());
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
            //this.getTcb().setNomLocalConnexion("con_" + this.getTcb().getConnexion().getIpLocale() + ":" + port_local + "_" + ip_distant + ":" + port_ser);
            //this.getTcb().setNomDistantConnexion("con_" + ip_distant + ":" + port_ser + "_" + this.getTcb().getConnexion().getIpLocale() + ":" + port_local);
            this.getTcb().setNomLocalConnexion("TOTO");
            this.getTcb().setNomDistantConnexion("TOTO");
            this.setOpenOk(true);
            return getTcb().getNomLocalConnexion();
        }
        //Dans le cas d'un serveur
        try {
            this.setTcb(new TCB(connexion));
            this.etatCourant = Ressource.ETAT_CLOSED;
        } catch (Exception e) {
            System.out.println("Automate::Probleme serveur");
        }
        this.setOpenOk(true);
        this.getTcb().setNomDistantConnexion("con_" + this.getTcb().getConnexion().getIpLocale() + ":" + port_local + "_" + ip_distant + ":" + port_ser);
        this.getTcb().setNomLocalConnexion("con_" + ip_distant + ":" + port_ser + "_" + this.getTcb().getConnexion().getIpLocale() + ":" + port_local);
        this.getTcb().setNomLocalConnexion("TOTO");
        this.getTcb().setNomDistantConnexion("TOTO");
        return getTcb().getNomLocalConnexion();
    }

    /* Depuis Closed */
    public void closedToSynSent() {
        if (this.getMod()) {
            this.getTcb().initISS();
            Paquet p = new Paquet(this.getPort_local(), this.getPort_dist());
            p.MettreSyn(true);
            System.out.println("Numero Seq ClosedToSynSent : " + this.getTcb().getSEG_SEQ());
            p.MettreNbrSeq(this.getTcb().getSEG_SEQ());
            p.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(p);
            System.out.println("Closed To SynSent : paquet envoye :");
            p.AfficherPaquet();
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
        Paquet pr = null;
        if (p.ObtenirSyn()) {
            this.getTcb().initACK(p.ObtenirNbrSeq());
            pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
            pr.MettreSyn(true);
            pr.MettreAck(true);
            System.out.println("Numero Seq ListenToSynRec : " + this.getTcb().getSEG_SEQ());
            System.out.println("Numero Ack ListenToSynRec : " + this.getTcb().getSEG_ACK());
            pr.MettreNbrSeq(this.getTcb().getSEG_SEQ());
            pr.MettreNbrAcc(this.getTcb().getSEG_ACK());
            pr.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(pr);
            System.out.println("Listen To SynRec : paquet envoye :");
            pr.AfficherPaquet();
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
        Paquet pr = null;
        /* ajout bapt */

        /* ouverture simultannee */

        if (!p.ObtenirAck() && p.ObtenirSyn()) {
            /* CHECK DES NUMS D'ACK ET DE SEQ A FAIRE */
        	pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
            pr.MettreAck(true);
            pr.MettreSyn(true);
            pr.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(pr);
            this.etatCourant = Ressource.ETAT_SYN_RCVD;
            return;
        }

        /* cas classique */
        if (p.ObtenirSyn() && p.ObtenirAck()) {
        	/* incrementation du numero de sequence local */
            this.getTcb().incrSEQ();
            if (this.getTcb().checkACK(p)) {
            	/* incremente le numero de sequence recu et le met dans ack */
                this.getTcb().initACK(p.ObtenirNbrSeq());
                pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
                pr.MettreAck(true);
                pr.MettreNbrAcc(this.getTcb().getSEG_ACK());
                pr.MettreNbrSeq(this.getTcb().getSEG_SEQ());
                pr.CreerPaquet();
                this.getTcb().getConnexion().ecrirePaquet(pr);
                System.out.println("SynSent To Esta : paquet envoye :");
                pr.AfficherPaquet();
                this.etatCourant = Ressource.ETAT_ESTABLISHED;
            } else {
                /* PAQUET FOIREUX : DEMANDE DE RENVOI DE PAQUET */
            	System.out.println("Paquet foireux, je change pas d'etat");
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

        if (p.ObtenirFin()) {
        	Paquet pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
            pr.MettreFin(true);
            pr.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(pr);
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
        if (p.ObtenirFin()) {
            /* analyse du paquet pour savoir si c'est un abort ou un close */
            this.etatCourant = Ressource.ETAT_FIN_WAIT_1;
        } else {
            /* Fin ajout bapt */
            this.getTcb().incrSEQ();
            if (p.ObtenirAck() && this.getTcb().checkACK(p))
            {
                this.etatCourant = Ressource.ETAT_ESTABLISHED;
            }
        }
    }

    /* Depuis Fin Wait 1 */
    public void finWait1ToFinWait2() {
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirAck() && p.ObtenirNbrAcc() == this.getTcb().getSEG_SEQ() + 1) {
        	this.etatCourant = Ressource.ETAT_FIN_WAIT_2;
        }
        else
        {
        	/* ca a foire */
        }
    }

    /* Depuis Close Wait */
    public void closeWaitToLastAck() {
    	
    	Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null)
        	return;
        
        if (p.ObtenirFin() && !p.ObtenirAck())
    	{
    		Paquet pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
    		pr.MettreFin(true);
    		pr.MettreAck(true);
    		pr.MettreNbrSeq(this.getTcb().getSEG_SEQ());
    		pr.CreerPaquet();
    		this.getTcb().getConnexion().ecrirePaquet(pr);
    		this.etatCourant = Ressource.ETAT_LAST_ACK;
    		return;
    	}
    }

    /* Depuis Fin Wait 2 */
    public void finWait2ToTimeWait() {
        if (!this.getMod()) {
            return;
        }
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
        if (p == null) {
            return;
        }
        if (p.ObtenirFin() && p.ObtenirAck()) {
        	Paquet pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
            pr.MettreAck(true);
            pr.MettreNbrAcc(p.ObtenirNbrSeq() + 1);
            pr.CreerPaquet();
            this.getTcb().getConnexion().ecrirePaquet(pr);
            this.getTcb().resetTCB();
            this.etatCourant = Ressource.ETAT_TIME_WAIT;
        }
    }

    /* Depuis Time Wait */
    public void timeWaitToClosed() {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.etatCourant = Ressource.ETAT_CLOSED;
        return;
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
        if (p.ObtenirAck() && p.ObtenirNbrAcc() == this.getTcb().getSEG_SEQ() + 1) {
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
            	if (GUI.get().obtainCard(this) != null)
            	{
            		GUI.get().obtainCard(this).getPanel_automate().update_states(Utils.conversionEtat(this.etatCourant));
            	}
            	if (GUI.get().obtainCard(this) != null)
                {
            		GUI.get().obtainCard(this).getConsole().insertLine(" attente ... ", "Blue");
                }
                while (!this.bypass) 
                {
                    Thread.sleep(200);
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
                	this.established();
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

    private void established() {
    	System.out.println("debut established");
        /*
    	if (this.getIp_dist() == this.getTcb().getConnexion().getIpDistante()
                && this.getPort_dist() == this.getTcb().getConnexion().getPortDistant()) {

        } else {
        	System.out.println("fail des ports ?");
            return;
        }
        */
        Paquet p = this.getTcb().getConnexion().lireDernierMessage();
    	if (p == null)
    		return;
    	p.AfficherPaquet();
    	/* reception de la commande close */
    	System.out.println("established > test FIN et !ACK ?");
    	if (p.ObtenirFin() && !p.ObtenirAck())
    	{
    		System.out.println("established > test FIN et !ACK = OK");
    		Paquet pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
    		pr.MettreFin(true);
    		pr.MettreAck(true);
    		pr.MettreNbrSeq(this.getTcb().getSEG_SEQ());
    		pr.CreerPaquet();
    		this.getTcb().getConnexion().ecrirePaquet(pr);
    		this.etatCourant = Ressource.ETAT_FIN_WAIT_1;
    		return;
    	}
    	/* reception d'un paquet de FIN */
    	System.out.println("established > test FIN et ACK ?");
    	if (p.ObtenirFin() && p.ObtenirAck())
    	{
    		System.out.println("established > test FIN et ACK = OK");
    		Paquet pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
    		pr.MettreAck(true);
    		pr.MettreNbrAcc(this.getTcb().getSEG_SEQ() + 1);
    		pr.CreerPaquet();
    		this.getTcb().getConnexion().ecrirePaquet(pr);
    		this.etatCourant = Ressource.ETAT_CLOSE_WAIT;
    		return;
    	}
    	
    	/* Donnees sont que des strings */
		System.out.println("established > test donnee ?");
    	if (!p.ObtenirDonnee().equals(""))
    	{
    		System.out.println("established > test donnee = OK");
    		GUI.get().obtainCard(this).getConsole().insertLine(p.ObtenirDonnee(), "Green");
    		Paquet pr = new Paquet(p.ObtenirPortDST(), p.ObtenirPortSRC());
    		pr.MettreAck(true);
    		pr.MettreNbrAcc(this.getTcb().getSEG_SEQ() + 1);
    		pr.MettreNbrSeq(this.getTcb().getSEG_ACK());
    		pr.CreerPaquet();
    		this.getTcb().getConnexion().ecrirePaquet(pr);
    		return;
    	}
    	
    	/* J'ai envoye des donnees, je controle le numero d'ACK */
    	System.out.println("established > test !FIN et ACK ?");
    	if (p.ObtenirAck() && !p.ObtenirFin())
    	{
    		System.out.println("established > test !FIN et ACK = OK");
    		if (p.ObtenirNbrSeq() == this.getTcb().getSEG_ACK() && p.ObtenirNbrAcc() == (this.getTcb().getSEG_SEQ() + 1))
    		{
    			System.out.println("Acquitement des donnees OK");
    		}
    		else
    		{
    			System.out.println("Acquitement des donnees FAUX");
    		}
    		return;
    	}	
	}

	@Override
    public void run() {
       	try {
			this.changerEtat();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public String toString() {
    	return "TOTO";
        //return this.getTcb().getNomLocalConnexion();
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
