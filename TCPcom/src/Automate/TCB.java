package Automate;

import java.net.Socket;

/**
 * Cette classe represente un objet TCB (Transmission Control Block)
 * Lorsqu'une connexion est creee par la commande OPEN, le protocol TCP/IP cree un objet TCB
 * pour sauvegarder les paramètres de cette connexion.
 * 
 * @author sylla salimata
 * 
 *
 */

public class TCB {
	
	private String nomLocalConnexion; // nom local de la connexion
	private Socket socketLocal;   // socket locale 
	private Socket socketDistant; // socket distante 
	private int    temporisationTransmissionCourante;
	private Boolean abort = false;
	
	/** variables de sequence de emission */
	
	private int send_Una;            //"unacknowledge"         - envoi sans accuse de reception
	private int send_Next;           //"next"                  - envoi du suivant
	private int send_Wnd;            //"window"                - envoi de la fenêtre
	private int send_Up;             //"urgent pointer"        - pointeur de donnees urgentes
	private int send_windowLast1;    //"window last 1"         - dernier numero de sequence de segment envoye
	private int send_windowLast2;    //"window last 2"         - dernier accuse de reception
	private int initialSendSequence; //"initial send sequence" - premier numero de sequence du message

	/** variables de sequence de reception */
	
	private int receive_next;           //"next"                     - reception du suivant
	private int receive_window;         //"window"                   - reception de fenetre
	private int receive_urgentPointer;  //"urgent pointer"           - pointeur de donnees urgentes
	private int initialReceiveSequence; //"initial receive sequence" - premier numero de sequence en reception
	
	
	/** variables du segment courant */
	
	private int segment_seq;  //"sequence"        - numero de sequence du segment courant
	private int segment_ack;  //"acknowledge"     - numero de sequence inscrit dans l’accuse de reception
	private int segment_len;  //"length"          - longueur du segment courant
	private int segment_wnd;  //"window"          - fenêtre inscrite dans le segment courant
	private int segment_up;   //"urgent pointer"  - pointeur de donnees urgentes du segment courant
	private int segment_prc;  //"precedence"      - valeur de priorite courante
	
	
	/** status de la connexion */
	private int connexionStatus;
	
	
	
	/** priorite */
	private int prioriteConnexion;
	
	/** securite */
	private int securiteConnexion;
	
	/**
	 * constructeur utilisee pour creer un objet TCB à partir des informations passées à la commande OPEN
	 * Les autres informations de l'objet TCB seront renseignees par les getters/setters de la classe
	 */
	public TCB(Socket socketDistant,int tempo,int priorite,int securite){
		this.socketDistant = socketDistant;
		this.prioriteConnexion = priorite;
		this.securiteConnexion = securite;
		this.temporisationTransmissionCourante = tempo;
	}
	
	/**
	 * reinitialise les donnees de la connexion courante
	 */
	public void reset()
	{
		if (abort == true)
		{
			nomLocalConnexion = null;
			socketLocal = null; 
			socketDistant = null ; 
			temporisationTransmissionCourante = 0;
		
			send_Una = 0;    
			send_Next = 0;           
			send_Wnd = 0;           
			send_Up = 0;             
			send_windowLast1 = 0;    
			send_windowLast2 = 0; 
			initialSendSequence = 0;
			receive_next = 0 ;           
			receive_window = 0;         
			receive_urgentPointer = 0;  
			initialReceiveSequence = 0; 
		
			segment_seq = 0;  
			segment_ack = 0;  
			segment_len = 0;  
			segment_wnd = 0;  
			segment_up = 0;   
			segment_prc = 0;  
			connexionStatus = 0;
			prioriteConnexion = 0;
			securiteConnexion = 0 ;
		}
	}
	
	/********************* getters et setters de la classe ******************************/
	
	
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

	/**
	 * @return the socketLocal
	 */
	public Socket getSocketLocal() {
		return socketLocal;
	}

	/**
	 * @param socketLocal the socketLocal to set
	 */
	public void setSocketLocal(Socket socketLocal) {
		this.socketLocal = socketLocal;
	}

	/**
	 * @return the socketDistant
	 */
	public Socket getSocketDistant() {
		return socketDistant;
	}

	/**
	 * @param socketDistant the socketDistant to set
	 */
	public void setSocketDistant(Socket socketDistant) {
		this.socketDistant = socketDistant;
	}

	/**
	 * @return the temporisationTransmissionCourante
	 */
	public int getTemporisationTransmissionCourante() {
		return temporisationTransmissionCourante;
	}

	/**
	 * @param temporisationTransmissionCourante the temporisationTransmissionCourante to set
	 */
	public void setTemporisationTransmissionCourante(
			int temporisationTransmissionCourante) {
		this.temporisationTransmissionCourante = temporisationTransmissionCourante;
	}

	/**
	 * @return the connexionStatus
	 */
	public int getConnexionStatus() {
		return connexionStatus;
	}

	/**
	 * @param connexionStatus the connexionStatus to set
	 */
	public void setConnexionStatus(int connexionStatus) {
		this.connexionStatus = connexionStatus;
	}

	/**
	 * @return the securiteConnexion
	 */
	public int getSecuriteConnexion() {
		return securiteConnexion;
	}

	/**
	 * @param securiteConnexion the securiteConnexion to set
	 */
	public void setSecuriteConnexion(int securiteConnexion) {
		this.securiteConnexion = securiteConnexion;
	}


	/**
	 * @return the send_Una
	 */
	public int getSend_Una() {
		return send_Una;
	}


	/**
	 * @param send_Una the send_Una to set
	 */
	public void setSend_Una(int send_Una) {
		this.send_Una = send_Una;
	}


	/**
	 * @return the send_Next
	 */
	public int getSend_Next() {
		return send_Next;
	}


	/**
	 * @param send_Next the send_Next to set
	 */
	public void setSend_Next(int send_Next) {
		this.send_Next = send_Next;
	}


	/**
	 * @return the send_Wnd
	 */
	public int getSend_Wnd() {
		return send_Wnd;
	}


	/**
	 * @param send_Wnd the send_Wnd to set
	 */
	public void setSend_Wnd(int send_Wnd) {
		this.send_Wnd = send_Wnd;
	}


	/**
	 * @return the send_Up
	 */
	public int getSend_Up() {
		return send_Up;
	}


	/**
	 * @param send_Up the send_Up to set
	 */
	public void setSend_Up(int send_Up) {
		this.send_Up = send_Up;
	}


	/**
	 * @return the send_windowLast1
	 */
	public int getSend_windowLast1() {
		return send_windowLast1;
	}


	/**
	 * @param send_windowLast1 the send_windowLast1 to set
	 */
	public void setSend_windowLast1(int send_windowLast1) {
		this.send_windowLast1 = send_windowLast1;
	}


	/**
	 * @return the send_windowLast2
	 */
	public int getSend_windowLast2() {
		return send_windowLast2;
	}


	/**
	 * @param send_windowLast2 the send_windowLast2 to set
	 */
	public void setSend_windowLast2(int send_windowLast2) {
		this.send_windowLast2 = send_windowLast2;
	}


	/**
	 * @return the initialSendSequence
	 */
	public int getInitialSendSequence() {
		return initialSendSequence;
	}


	/**
	 * @param initialSendSequence the initialSendSequence to set
	 */
	public void setInitialSendSequence(int initialSendSequence) {
		this.initialSendSequence = initialSendSequence;
	}


	/**
	 * @return the receive_next
	 */
	public int getReceive_next() {
		return receive_next;
	}


	/**
	 * @param receive_next the receive_next to set
	 */
	public void setReceive_next(int receive_next) {
		this.receive_next = receive_next;
	}


	/**
	 * @return the receive_window
	 */
	public int getReceive_window() {
		return receive_window;
	}


	/**
	 * @param receive_window the receive_window to set
	 */
	public void setReceive_window(int receive_window) {
		this.receive_window = receive_window;
	}


	/**
	 * @return the receive_urgentPointer
	 */
	public int getReceive_urgentPointer() {
		return receive_urgentPointer;
	}


	/**
	 * @param receive_urgentPointer the receive_urgentPointer to set
	 */
	public void setReceive_urgentPointer(int receive_urgentPointer) {
		this.receive_urgentPointer = receive_urgentPointer;
	}


	/**
	 * @return the initialReceiveSequence
	 */
	public int getInitialReceiveSequence() {
		return initialReceiveSequence;
	}


	/**
	 * @param initialReceiveSequence the initialReceiveSequence to set
	 */
	public void setInitialReceiveSequence(int initialReceiveSequence) {
		this.initialReceiveSequence = initialReceiveSequence;
	}


	/**
	 * @return the segment_seq
	 */
	public int getSegment_seq() {
		return segment_seq;
	}


	/**
	 * @param segment_seq the segment_seq to set
	 */
	public void setSegment_seq(int segment_seq) {
		this.segment_seq = segment_seq;
	}


	/**
	 * @return the segment_ack
	 */
	public int getSegment_ack() {
		return segment_ack;
	}


	/**
	 * @param segment_ack the segment_ack to set
	 */
	public void setSegment_ack(int segment_ack) {
		this.segment_ack = segment_ack;
	}


	/**
	 * @return the segment_len
	 */
	public int getSegment_len() {
		return segment_len;
	}


	/**
	 * @param segment_len the segment_len to set
	 */
	public void setSegment_len(int segment_len) {
		this.segment_len = segment_len;
	}


	/**
	 * @return the segment_wnd
	 */
	public int getSegment_wnd() {
		return segment_wnd;
	}


	/**
	 * @param segment_wnd the segment_wnd to set
	 */
	public void setSegment_wnd(int segment_wnd) {
		this.segment_wnd = segment_wnd;
	}


	/**
	 * @return the segment_up
	 */
	public int getSegment_up() {
		return segment_up;
	}


	/**
	 * @param segment_up the segment_up to set
	 */
	public void setSegment_up(int segment_up) {
		this.segment_up = segment_up;
	}


	/**
	 * @return the segment_prc
	 */
	public int getSegment_prc() {
		return segment_prc;
	}


	/**
	 * @param segment_prc the segment_prc to set
	 */
	public void setSegment_prc(int segment_prc) {
		this.segment_prc = segment_prc;
	}


	/**
	 * @return the prioriteConnexion
	 */
	public int getPrioriteConnexion() {
		return prioriteConnexion;
	}


	/**
	 * @param prioriteConnexion the prioriteConnexion to set
	 */
	public void setPrioriteConnexion(int prioriteConnexion) {
		this.prioriteConnexion = prioriteConnexion;
	}

	public Boolean getAbort() {
		return abort;
	}

	public void setAbort(Boolean abort) {
		this.abort = abort;
	}
}