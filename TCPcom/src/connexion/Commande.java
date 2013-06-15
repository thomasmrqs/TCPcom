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
		ArrayList<Automate> LA = new ArrayList<Automate>(); 
		for (int i = 0; i < GUI.get().getOnglets().getTabCount();i++){
			ItemCard c = (ItemCard) GUI.get().getOnglets().getComponent(i);
			if (c.isClient()){
				LA.add(c.getAutomate());
			}else{
				for (Object o : c.getMap_console().values()){
					LA.add((Automate) o);
				}
			}
		}
		
		
		
		return statu;
	}

	public Automate trouverNomLocal ()
	{
		ArrayList<Automate> LA = new ArrayList<Automate>();
		Automate auto = new Automate();
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
		return auto;
	}
	
	
}
