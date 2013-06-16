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
	int bit = 0;
	private BitSet bitS = new BitSet();
	
	
	// Constructeur
	public ControlErreur ()
	{
	}

	
	public Byte[] PortVerBit (int port) {
		//System.out.println(bit);
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		Byte[] b = new Byte[2];
		boolean bi = false;
		for (i = 15, j = 16; j > 0; i--, j--) {
			if (Math.pow(2,i) <= port)
			{
				c[i] = '1';
				port = (int) (port - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i=0, j = 0; j >= 0; i++, j++)
		{
			if (c[i] == '1')
				bi = true;
			if (j == 8)
				j = 0;
			if (i < 8)
				b[0] = Utils.changerValeurBit(j, b[0], bi);
			else
				b[1] = Utils.changerValeurBit(j, b[1], bi);
		}
		//System.out.println(bitS.size());
		return b;
	}
	
	public Byte[] TrenteDeuxVerBit (int trenteDeux) {
		//System.out.println(bit);
		char c[] = new char [32];
		int i = 0;
		int j = 0;
		Byte[] b = new Byte[4];
		boolean bi = false;
		for (i = 31, j = 32; j > 0; i--, j--) {
			if (Math.pow(2,i) <= trenteDeux)
			{
				c[i] = '1';
				trenteDeux = (int) (trenteDeux - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i=0, j = 0; j >= 0; i++, j++)
		{
			if (c[i] == '1')
				bi = true;
			if (j == 8)
				j = 0;
			if (i < 8)
				b[0] = Utils.changerValeurBit(j, b[0], bi);
			else if (i < 16)
				b[1] = Utils.changerValeurBit(j, b[1], bi);
			else if (i < 24)
				b[2] = Utils.changerValeurBit(j, b[2], bi);
			else
				b[3] = Utils.changerValeurBit(j, b[3], bi);
		}
		//System.out.println(bitS.size());
		return b;
	}
	
	public void calculChecksum(Paquet p, String ip_src, String ip_dest, int type)
	{
		if (p == null)
			return;
		Byte[] p_s = new Byte[2];
		Byte[] p_d = new Byte[2];
		int port_src = p.ObtenirPortSRC();
		int port_dst = p.ObtenirPortDST();
		int nbr_seq = p.ObtenirNbrSeq();
		int ack_nbr = p.ObtenirNbrAcc();
		int data_off = p.ObtenirDataOff();
		int reserve = p.ObtenirReserve();
		int fenetre = p.ObtenirFenetre();
		p_s = this.PortVerBit(port_src);
		p_d = this.PortVerBit(port_dst);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String[] ipSrc = ip_src.split("\\.");
			int res = Integer.parseInt(ipSrc[0]);
			int res2 = Integer.parseInt(ipSrc[1]);
			int res3 = Integer.parseInt(ipSrc[2]);
			int res4 = Integer.parseInt(ipSrc[3]);		
		System.out.println(res + " " + res2 + " " + res3 + " " + res4);
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

	public BitSet getBitS() {
		return bitS;
	}

	public void setBitS(BitSet bitS) {
		this.bitS = bitS;
	}
}
