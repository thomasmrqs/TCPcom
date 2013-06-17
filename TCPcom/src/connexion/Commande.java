package connexion;

import graphique.*;
import Ressource.Ressource;
import graphique.GUI;
import graphique.ItemCard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Commande {

	
	
	
	public Commande ()
	{
	}

	public int Send (String nom_local, String donnee, int compteur, boolean push, boolean urg, int tempo)
	{
		int addr = 0;
		Automate auto = null;
		String don = null;
		if ((auto = this.trouverNomLocal(nom_local)) == null)
			return addr;
		Paquet p = new Paquet (auto.getPort_local(), auto.getPort_dist());
		p.MettreDataOff(6);
		if (push)
			p.MettrePsh(push);
		if (urg)
			p.MettreUrg(urg);
		if (compteur != donnee.length())
		{
			don.concat(donnee.substring(0, compteur - 1));
		}
		else
			don.concat(donnee);
		p.MettreDonnee(don);
		p.MettrePortSRC(auto.getPort_local());
		p.MettrePortDST(auto.getPort_dist());
		p.MettreNbrAcc(auto.getTcb().getSEG_ACK());
		p.MettreNbrSeq(auto.getTcb().getSEG_SEQ());
		p.CreerPaquet();
        auto.getTcb().getConnexion().ecrirePaquet(p);
        addr = compteur;
		return addr;
	}
	
	public int reveive (String nom_local, File fil, int compteur)
	{
		int addr = 0;
		Automate auto = null;
		String don = null;
		if ((auto = this.trouverNomLocal(nom_local)) == null)
			return addr;
		auto.setFichier(fil);
		
		return 1;
	}
	
	
	public int Close (String nom_local)
	{
		int addr = 0;
		Automate auto = null;
		System.out.println("test du nom local de l'automate");
		if ((auto = this.trouverNomLocal(nom_local)) == null)
			return addr;
		Paquet p = new Paquet (auto.getPort_local(), auto.getPort_dist());
		p.MettreDataOff(6);
		p.MettrePsh(true);
		p.MettreFin(true);
		p.MettreDonnee("close");
		p.CreerPaquet();
		p.AfficherPaquet();
        auto.getTcb().getConnexion().ecrirePaquet(p);
		addr = 1;
		return addr;
	}
	
	public int Abort (String nom_local)
	{
		int addr = 0;
		Automate auto = null;
		if ((auto = this.trouverNomLocal(nom_local)) == null)
			return addr;
		Paquet p = new Paquet (auto.getPort_local(), auto.getPort_dist());
		p.MettreDataOff(6);
		p.MettrePsh(true);
		p.MettreFin(true);
		p.MettreUrg(true);
		p.MettreDonnee("abort");
		p.CreerPaquet();
        auto.getTcb().getConnexion().ecrirePaquet(p);
		return 1;
	}
	
	public Stat status (String nom_local) 
	{
		Stat statu = new Stat ();
		Automate auto = null;
		if ((auto = this.trouverNomLocal(nom_local)) == null)
			return null;
		statu.setNom_loc(nom_local);
		statu.setEtat(auto.getEtatCourant());
		statu.setIp_dist(auto.getTcb().getConnexion().getIpDistante());
		statu.setIp_loc(auto.getTcb().getConnexion().getIpLocale());
		statu.setPort_dist(auto.getTcb().getConnexion().getPortDistant());
		statu.setPort_loc(auto.getTcb().getConnexion().getPortLocal());
		if (auto.getTcb().getPrioriteConnexion() != 0)
			statu.setPriorite(true);
		if (auto.getTcb().getSecuriteConnexion() != 0)
			statu.setSecurite(true);
		if (auto.getTcb().getSecuriteConnexion() != 0)
			statu.setUrg(true);
		/**********************/
		/* Fix me             */
		/**********************/
		
		return statu;
	}

	public Automate trouverNomLocal (String nom_loc)
	{
		ArrayList<Automate> LA = new ArrayList<Automate>();
		for (int i = 0; i < GUI.get().getOnglets().getTabCount();i++)
		{
			ItemCard c = (ItemCard) GUI.get().getOnglets().getComponent(i);
			if (c.isClient())
			{
				LA.add(c.getAutomate());
			}
			else
			{
				for (Automate o : c.getMap_console().keySet())
				{
					LA.add(o);
				}
			}
		}
		for (Automate automate : LA) 
		{
			String nom = automate.getTcb().getNomLocalConnexion();
			if (nom == nom_loc)
				return automate;
			else 
				return null;
			
		}
		return null;
	}
	
	
}
