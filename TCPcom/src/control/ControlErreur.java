package control;

import java.util.BitSet;

import connexion.Paquet;
import connexion.Utils;

public class ControlErreur {
	//	static class PseudoHeader {
	//	public int src_addr; 
	//	public int dest_addr; 
	//	public int mbz; 
	//	public int protocol; 
	//	public int len; 
	//}
	
	// Constructeur
	public ControlErreur ()
	{
	}

	public void calculChecksum(Paquet p, String ip_src, String ip_dest, int type)
	{
		String[] ipSrc = ip_src.split("\\.");
		//	int res = Integer.parseInt(ipSrc[0]);
		//	int res2 = Integer.parseInt(ipSrc[1]);
		//	int res3 = Integer.parseInt(ipSrc[2]);
		//	int res4 = Integer.parseInt(ipSrc[3]);		
		//System.out.println(res + " " + res2 + " " + res3 + " " + res4);
		// recuperation ip dest
	
		String[] ipDest = ip_dest.split("\\.");
	
		// champ MBZ: doit toujours etre a 0
		int mbz = 0;
	
		// recuperation champ protocol (6 par defaut pour TCP)
		int proto = type;
	
		// recuperation et complement à 1 des differentes infos du paquet
		// port Source, destination...
		BitSet bitset = p.ObtenirBitSet();
	
		System.out.println((bitset.get(0, 15).toByteArray())[0]);
	
		byte[] portSrc = Utils.complement(bitset.get(0, 15).toByteArray());
		System.out.println("port src = " + portSrc);
	
		byte[] portDst = Utils.complement(bitset.get(16, 31).toByteArray());
		System.out.println("port dest = " + Utils.byteArrayToIntarray(portDst));
	
		byte[] numSeq = Utils.complement(bitset.get(32, 63).toByteArray());
		System.out.println("num Seq = " + Utils.byteArrayToIntarray(numSeq));
		
		for(int k = 0; k < numSeq.length; k++)
			System.out.println("testtttt" + numSeq.length);
	
		byte[] ack = Utils.complement(bitset.get(64, 95).toByteArray());
		for(int k = 0; k < ack.length; k++)
			System.out.println(ack[k]);
	
		byte[] offset = Utils.complement(bitset.get(96, 99).toByteArray());
	
		byte[] rsrv = Utils.complement(bitset.get(100, 105).toByteArray());
	
		byte[] flags = Utils.complement(bitset.get(106, 111).toByteArray());
	
		byte[] fen = Utils.complement(bitset.get(112, 127).toByteArray());
	
		// ya le champ checksum lui-meme: mettre les 16 bits à 0
		byte[] sum;
	
		byte[] ptrUrg = Utils.complement(bitset.get(144, 159).toByteArray());
	
		//
		// complement à 1 des differentes infos de l'entete IP
		byte[] compIpSrc = Utils.complement(ipSrc.toString());
	
		byte[] compIpDest = Utils.complement(ipDest.toString());
	
		byte[] compMbz = Utils.complement(String.valueOf(mbz));
	
		byte[] compProto = Utils.complement(String.valueOf(proto));
	
		// Pour la longueur, s'il n'y a aucune donnée, len vaut 160 bits soit 20 octets
		byte[] compLen;
	
		//
		// somme des complements à 1 precedents		
		//checksum += numSeq;
	
		//return Utils.complement(checksum);
	}
}
