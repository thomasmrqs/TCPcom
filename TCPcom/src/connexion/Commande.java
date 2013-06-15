package connexion;

import graphique.*;
import Ressource.Ressource;
import graphique.GUI;
import graphique.ItemCard;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Commande {

	
	
	
	public Commande ()
	{
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
		//statu.isPriorite(auto.getTcb().)
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
				for (Object o : c.getMap_console().values())
				{
					LA.add((Automate) o);
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
