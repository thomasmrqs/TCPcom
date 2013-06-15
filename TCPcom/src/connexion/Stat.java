/**
 * 
 */
package connexion;

/**
 * @author jakez
 *
 */
public class Stat {

	private int port_loc = 0;
	private String ip_loc = null;
	private int port_dist = 0;
	private String ip_dist = null;
	private String nom_loc = null;
	private int fenetre_recep = 0;
	private int fenetre_emis = 0;
	private int etat = 0;
	private int nbr_tam_att_ack = 0;
	private int nbr_tam_att_recep = 0;
	private boolean urg = false;
	private boolean priorite = false;
	private boolean securite = false;
	private boolean tempo = false;
	
	public int getPort_loc() {
		return port_loc;
	}
	public void setPort_loc(int port_loc) {
		this.port_loc = port_loc;
	}
	public String getIp_loc() {
		return ip_loc;
	}
	public void setIp_loc(String ip_loc) {
		this.ip_loc = ip_loc;
	}
	public int getPort_dist() {
		return port_dist;
	}
	public void setPort_dist(int port_dist) {
		this.port_dist = port_dist;
	}
	public String getIp_dist() {
		return ip_dist;
	}
	public void setIp_dist(String ip_dist) {
		this.ip_dist = ip_dist;
	}
	public String getNom_loc() {
		return nom_loc;
	}
	public void setNom_loc(String nom_loc) {
		this.nom_loc = nom_loc;
	}
	public int getFenetre_recep() {
		return fenetre_recep;
	}
	public void setFenetre_recep(int fenetre_recep) {
		this.fenetre_recep = fenetre_recep;
	}
	public int getFenetre_emis() {
		return fenetre_emis;
	}
	public void setFenetre_emis(int fenetre_emis) {
		this.fenetre_emis = fenetre_emis;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public int getNbr_tam_att_ack() {
		return nbr_tam_att_ack;
	}
	public void setNbr_tam_att_ack(int nbr_tam_att_ack) {
		this.nbr_tam_att_ack = nbr_tam_att_ack;
	}
	public int getNbr_tam_att_recep() {
		return nbr_tam_att_recep;
	}
	public void setNbr_tam_att_recep(int nbr_tam_att_recep) {
		this.nbr_tam_att_recep = nbr_tam_att_recep;
	}
	public boolean isUrg() {
		return urg;
	}
	public void setUrg(boolean urg) {
		this.urg = urg;
	}
	public boolean isPriorite() {
		return priorite;
	}
	public void setPriorite(boolean priorite) {
		this.priorite = priorite;
	}
	public boolean isSecurite() {
		return securite;
	}
	public void setSecurite(boolean securite) {
		this.securite = securite;
	}
	public boolean isTempo() {
		return tempo;
	}
	public void setTempo(boolean tempo) {
		this.tempo = tempo;
	}
	
	public Stat ()
	{
		
	}
	
}
