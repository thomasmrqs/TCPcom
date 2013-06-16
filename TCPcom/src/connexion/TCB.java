package connexion;

public class TCB {
	
	private String 		nomLocalConnexion = null; // nom local de la connexion
	private String		nomDistantConnexion = null;
	private Connexion 	connexion = null; // contient la socket
	private int 		connexionStatus = 0; // status de la connexion
	private int 		prioriteConnexion = 0; // priorite de la connexion
	private int 		securiteConnexion = 0; // securite de la connexion
	
	private int    		temporisationTransmissionCourante = 0;
	private Boolean 	abort = false;
	
	/*
	* SEND Variables
	* */
	private int 		SND_UNA = 0;
	private int 		SND_NXT = 0;
	private int 		SND_WND = 0;
	private int 		SND_UP = 0;
	private int 		SND_WL1 = 0;
	private int 		SND_WL2 = 0;
	private int 		SND_PUSH = 0;
	private int 		ISS = 0;
	/*
	* RECEIVE Variables
	* */
	private int 		RCV_NXT = 0;
	private int 		RCV_WND = 0;
	private int 		RCV_UP = 0;
	private int 		RCV_IRS = 0;
	
	/* 
	* SEGMENT Variables
	* */
	
	private int 		SEG_SEQ = 0;  //"sequence"        - numero de sequence du segment courant
	private int 		SEG_ACK = 0;  //"acknowledge"     - numero de sequence inscrit dans l’accuse de reception
	private int 		SEG_LEN = 0;  //"length"          - longueur du segment courant
	private int 		SEG_WND = 0;  //"window"          - fenêtre inscrite dans le segment courant
	private int 		SEG_UP = 0;   //"urgent pointer"  - pointeur de donnees urgentes du segment courant
	private int 		SEG_PRC = 0;  //"precedence"      - valeur de priorite courante


	/*
	* constructeurs utilises pour creer un objet TCB a partir des informations passees a la commande OPEN
	* */

	public TCB(Connexion connexion)
	{
		setConnexion(connexion);
	}

	public TCB(Connexion connexion, int tempo, int priorite, int securite)
	{
		setConnexion(connexion);
		setTemporisationTransmissionCourante(tempo);
		setPrioriteConnexion(priorite);
		setSecuriteConnexion(securite);
	}

	public void resetTCB()
	{
		if (abort)
		{
			setNomLocalConnexion(null);
			setNomDistantConnexion(null);
			setConnexion(null);
			setConnexionStatus(0);
			setPrioriteConnexion(0);
			setSecuriteConnexion(0);
			
			setTemporisationTransmissionCourante(0);
			setAbort(false);
			
			/* SEND */
			setSND_UNA(0);
			setSND_NXT(0);
			setSND_WND(0);
			setSND_UP(0);
			setSND_WL1(0);
			setSND_WL2(0);
			setSND_PUSH(0);
			setISS(0);
			
			/* RECEIVE */
			setRCV_NXT(0);
			setRCV_WND(0);
			setRCV_UP(0);
			setRCV_IRS(0);
			
			setSEG_ACK(0);
			setSEG_LEN(0);
			setSEG_PRC(0);
			setSEG_SEQ(0);
			setSEG_UP(0);
			setSEG_WND(0);
		}
	}

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    /**
     * @return the nomLocalConnexion
     */
    public String getNomLocalConnexion() {
        return nomLocalConnexion;
    }

    /**
     * @param nomLocalConnexion the nomLocalConnexion to set
     */
    public void setNomLocalConnexion(String nomLocalConnexion) {
        this.nomLocalConnexion = nomLocalConnexion;
    }

    public int getSND_UNA() {
        return SND_UNA;
    }

    public void setSND_UNA(int sND_UNA) {
        SND_UNA = sND_UNA;
    }

    public int getSND_NXT() {
        return SND_NXT;
    }

    public void setSND_NXT(int sND_NXT) {
        SND_NXT = sND_NXT;
    }

    public int getSND_WND() {
        return SND_WND;
    }

    public void setSND_WND(int sND_WND) {
        SND_WND = sND_WND;
    }

    public int getSND_UP() {
        return SND_UP;
    }

    public void setSND_UP(int sND_UP) {
        SND_UP = sND_UP;
    }

    public int getSND_WL1() {
        return SND_WL1;
    }

    public void setSND_WL1(int sND_WL1) {
        SND_WL1 = sND_WL1;
    }

    public int getSND_WL2() {
        return SND_WL2;
    }

    public void setSND_WL2(int sND_WL2) {
        SND_WL2 = sND_WL2;
    }

    public int getSND_PUSH() {
        return SND_PUSH;
    }

    public void setSND_PUSH(int sND_PUSH) {
        SND_PUSH = sND_PUSH;
    }

    public int getISS() {
        return ISS;
    }

    public void setISS(int iSS) {
        ISS = iSS;
    }

    public int getRCV_NXT() {
        return RCV_NXT;
    }

    public void setRCV_NXT(int rCV_NXT) {
        RCV_NXT = rCV_NXT;
    }

    public int getRCV_WND() {
        return RCV_WND;
    }

    public void setRCV_WND(int rCV_WND) {
        RCV_WND = rCV_WND;
    }

    public int getRCV_UP() {
        return RCV_UP;
    }

    public void setRCV_UP(int rCV_UP) {
        RCV_UP = rCV_UP;
    }

    public int getRCV_IRS() {
        return RCV_IRS;
    }

    public void setRCV_IRS(int rCV_IRS) {
        RCV_IRS = rCV_IRS;
    }

	public int getSEG_SEQ() {
		return SEG_SEQ;
	}

	public void setSEG_SEQ(int sEG_SEQ) {
		SEG_SEQ = sEG_SEQ;
	}

	public int getSEG_ACK() {
		return SEG_ACK;
	}

	public void setSEG_ACK(int sEG_ACK) {
		SEG_ACK = sEG_ACK;
	}

	public int getSEG_LEN() {
		return SEG_LEN;
	}

	public void setSEG_LEN(int sEG_LEN) {
		SEG_LEN = sEG_LEN;
	}

	public int getSEG_WND() {
		return SEG_WND;
	}

	public void setSEG_WND(int sEG_WND) {
		SEG_WND = sEG_WND;
	}

	public int getSEG_UP() {
		return SEG_UP;
	}

	public void setSEG_UP(int sEG_UP) {
		SEG_UP = sEG_UP;
	}

	public int getSEG_PRC() {
		return SEG_PRC;
	}

	public void setSEG_PRC(int sEG_PRC) {
		SEG_PRC = sEG_PRC;
	}

	public int getConnexionStatus() {
		return connexionStatus;
	}

	public void setConnexionStatus(int connexionStatus) {
		this.connexionStatus = connexionStatus;
	}

	public int getPrioriteConnexion() {
		return prioriteConnexion;
	}

	public void setPrioriteConnexion(int prioriteConnexion) {
		this.prioriteConnexion = prioriteConnexion;
	}

	public int getSecuriteConnexion() {
		return securiteConnexion;
	}

	public void setSecuriteConnexion(int securiteConnexion) {
		this.securiteConnexion = securiteConnexion;
	}

	public int getTemporisationTransmissionCourante() {
		return temporisationTransmissionCourante;
	}

	public void setTemporisationTransmissionCourante(
			int temporisationTransmissionCourante) {
		this.temporisationTransmissionCourante = temporisationTransmissionCourante;
	}
	
	public Boolean getAbort ()
	{
		return this.abort;
	}
	
	public void setAbort(Boolean abort)
	{
		this.abort = abort;
	}

	public String getNomDistantConnexion() {
		return nomDistantConnexion;
	}

	public void setNomDistantConnexion(String nomDistantConnexion) {
		this.nomDistantConnexion = nomDistantConnexion;
	}
}
