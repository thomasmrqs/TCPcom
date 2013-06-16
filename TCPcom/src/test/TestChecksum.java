package test;

import connexion.Paquet;
import control.ControlErreur;

public class TestChecksum {
	public static void main(String[] args) {
        Paquet p = new Paquet();
        ControlErreur c = new ControlErreur ();
        System.out.println("_________CHECKSUM____________");
        p.MettrePortSRC(1645);
        p.MettrePortDST(80);
        p.MettreNbrSeq(1);
        p.MettreAck(true);
        p.MettreDataOff(5);
        p.MettreReserve(0);
        p.MettreFenetre(128);
        p.MettreChecksum(0);
        p.AfficherPaquet();
        
        c.calculChecksum(p, "192.68.43.1", "10.176.2.34", 6);
	}
}