package test;

import javax.rmi.CORBA.Util;

import connexion.Paquet;
import connexion.Utils;
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
        byte b = 1;
        System.out.println("jakez");
		System.out.println(Utils.afficherOctet(b));
        System.out.println("jakez2");
        byte[] test = new byte[2];
        test = c.PortVerBit(4097);
        System.out.println("tab0 : " + Utils.afficherOctet(test[0]) + "tab1 : " +  Utils.afficherOctet(test[1]));
        System.out.println(Utils.bitEstVrai(test[1], 7));
        System.out.println("valeur calcule : " + c.calcul(test[0], test[1]));
        //c.calculChecksum(p, "192.68.43.1", "10.176.2.34", 6);
	}
}