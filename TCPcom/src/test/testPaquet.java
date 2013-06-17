package test;

import connexion.Client;
import connexion.Decompression;
import connexion.GestionDesConnexions;
import connexion.Paquet;
import connexion.Serveur;
import connexion.ServeurThread;

public class testPaquet {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Serveur s = GestionDesConnexions.get().lancerServeur(1234);
		Thread.sleep(1000);
		Client c = GestionDesConnexions.get().lancerClient("127.0.0.1", 1234, 4321);
		Paquet p = new Paquet (4000, 5000);
		p.MettreSyn(true);
		p.MettreChecksum(0);
		p.MettreFenetre(0);
		p.MettreDonnee("coucou greg");
		p.MettreNbrAcc(0);
		p.MettreNbrSeq(59);
		p.MettreDataOff(0);
		p.CreerPaquet();	
		c.ecrirePaquet(p);	
		ServeurThread st = s.getListe().get(0);
		Thread.sleep(1000);
		System.out.println(st);
	}

}
