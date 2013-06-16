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
        p.MettreSyn(true);
        p.AfficherPaquet();
        byte[] test = new byte[2];
        test = c.PortVerBit(1645);
        System.out.println("port : " + c.calcul(test[0], test[1]));
        System.out.println("chcecksum : " + c.calculChecksum(p, "192.68.43.1", "10.176.2.34", 6));
	}
}