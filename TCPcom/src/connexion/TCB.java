package connexion;

public class TCB
{
    
    
        private Connexion connexion;
	/*
	 * SEND Variables
	 * */
	private int SND_UNA = 0;
	private int SND_NXT = 0;
	private int SND_WND = 0;
	private int SND_UP = 0;
	private int SND_WL1 = 0;
	private int SND_WL2 = 0;
	private int SND_PUSH = 0;
	private int ISS = 0;
	
	/*
	 * RECEIVE Variables
	 * */
	private int RCV_NXT = 0;
	private int RCV_WND = 0;
	private int RCV_UP = 0;
	private int RCV_IRS = 0;
	
	
	public TCB (Connexion connexion)
	{
            this.connexion = connexion;
	}

	public void resetTCB ()
	{
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
}
