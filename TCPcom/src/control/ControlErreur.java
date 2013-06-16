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
	private int bit = 0;
	private BitSet bitS = new BitSet();
	private int check = 0; 
	
	
	// Constructeur
	public ControlErreur ()
	{
	}

	
	public byte[] PortVerBit (int port) {
		//System.out.println(bit);
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		int pos = 0;
		byte[] b = new byte[2];
		boolean bi = false;
		for (i = 15, j = 16, pos = 0; j > 0; i--, j--, ++pos) {
			if (Math.pow(2,i) <= port)
			{
				//System.out.println("puissance : " + i + " pos : " + pos);
				c[pos] = '1';
				port = (int) (port - Math.pow(2,i));
			}
			else
				c[pos] = '0';
		}
		for (i=0, j = 0; i <= 15; i++, j++, bi = false)
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
	
	
	
	public byte[] TrenteDeuxVerBit (int trenteDeux) {
		//System.out.println(bit);
		char c[] = new char [32];
		int i = 0;
		int j = 0;
		int pos = 0;
		byte[] b = new byte[4];
		boolean bi = false;
		for (i = 31, j = 32, pos = 0; j > 0; i--, j--, ++pos) {
			if (Math.pow(2,i) <= trenteDeux)
			{
				c[pos] = '1';
				trenteDeux = (int) (trenteDeux - Math.pow(2,i));
			}
			else
				c[pos] = '0';
		}
		for (i=0, j = 0; i <= 31; i++, j++, bi = false)
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
	
	public byte[] seizeVerBit (Paquet p, int reserve, int data_off)
	{
		byte[] b = new byte[2];
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		int pos = 0;
		boolean bi = false;
		for (i = 3, j = 4, pos = 0; j > 0; i--, j--, ++pos) {
			if (Math.pow(2,i) <= data_off)
			{
				c[pos] = '1';
				data_off = (int) (data_off - Math.pow(2,i));
			}
			else
				c[pos] = '0';
		}
		for (i = 5, j = 6, pos = 4; j > 0; i--, j--, ++pos) {
			if (Math.pow(2,i) <= reserve)
			{
				c[pos] = '1';
				reserve = (int) (reserve - Math.pow(2,i));
			}
			else
				c[pos] = '0';
		}
		
		if (p.ObtenirUrg() == true)
			c[10] = '1';
		else 
			c[10] = '0';
		if (p.ObtenirAck() == true)
			c[11] = '1';
		else 
			c[11] = '0';
		if (p.ObtenirPsh() == true)
			c[12] = '1';
		else 
			c[12] = '0';
		if (p.ObtenirRst() == true)
			c[13] = '1';
		else 
			c[13] = '0';
		if (p.ObtenirSyn() == true)
			c[14] = '1';
		else 
			c[14] = '0';
		if (p.ObtenirFin() == true)
			c[15] = '1';
		else 
			c[15] = '0';
				
		for (i=0, j = 0; i <= 15; i++, j++, bi = false)
		{
			if (c[i] == '1')
				bi = true;
			if (j == 8)
				j = 0;
			if (i < 8)
				b[0] = Utils.changerValeurBit(j, b[0], bi);
			else if (i < 16)
				b[1] = Utils.changerValeurBit(j, b[1], bi);
		}
		//System.out.println(bitS.size());
		return b;
	}
	
	public byte convertHuit (int val)
	{
		byte b = 0;
		int j = 0;
		int i = 0;
		char c[] = new char [8];
		int pos = 0;
		boolean bi = false;
		for (i = 7, j = 8, pos = 0; j > 0; i--, j--, ++pos) 
		{
			if (Math.pow(2,i) <= val)
			{
				c[pos] = '1';
				val = (int) (val - Math.pow(2,i));
			}
			else
				c[pos] = '0';
		}
		for (i=0, j = 0; i <= 7; i++, j++, bi = false)
		{
			if (c[i] == '1')
				bi = true;
			if (i < 8)
				b = Utils.changerValeurBit(j, b, bi);

		}
		return b;		
	}
	
	
	public byte[] converIP (String ip)
	{
		byte[] b = new byte[4];
		String[] ipSrc = ip.split("\\.");
		int res = Integer.parseInt(ipSrc[0]);
		int res2 = Integer.parseInt(ipSrc[1]);
		int res3 = Integer.parseInt(ipSrc[2]);
		int res4 = Integer.parseInt(ipSrc[3]);
		b[0] = this.convertHuit(res);
		b[1] = this.convertHuit(res2);
		b[2] = this.convertHuit(res3);
		b[3] = this.convertHuit(res4);
		return b;
	}
	
	public int calcul (byte b, byte b2)
	{
		int i = 0;
		int j = 0;
		int pui = 0;
		int val = 0;
		for (j = 0, pui = 15, i = 0; j < 16; ++j, --pui, ++i)
		{
			if (i == 8)
				i = 0;
			if (j < 8)
			{
				if (Utils.bitEstVrai(b, i))
					val += Math.pow(2,pui);
			}
			else
			{
				if (Utils.bitEstVrai(b2, i))
					val += Math.pow(2,pui);
			}
		}
		return val;
	}
	
	
	
	public int calculChecksum(Paquet p, String ip_src, String ip_dest, int type)
	{
		
		int pos = 0;
		
		if (p == null)
			return -1;
		byte[] p_s = new byte[2];
		byte[] p_d = new byte[2];
		byte[] nb_seq = new byte[4];
		byte[] nb_acc = new byte[4];
		byte[] urg_point = new byte[4];
		byte[] opt = new byte[4];
		byte[] fen = new byte[2];
		byte[] headercheck = new byte[2];
		byte[] data = new byte[p.ObtenirDonnee().getBytes().length];
		byte[] data_reser_flag = new byte[2];
		byte[] ip_sr = new byte[4];
		byte[] ip_ds = new byte[4];
		byte[] rese_proto = new byte [2];
		byte[] tcpHead = new byte[2];
		byte[] seizeFinal = new byte[2];

		
		int port_src = p.ObtenirPortSRC();
		int port_dst = p.ObtenirPortDST();
		int nbr_seq = p.ObtenirNbrSeq();
		int ack_nbr = p.ObtenirNbrAcc();
		int data_off = p.ObtenirDataOff();
		int reserve = p.ObtenirReserve();
		int fenetre = p.ObtenirFenetre();
		int checksum = p.ObtenirChecksum();
		int ptr_urg = p.ObtenirPointeur();
		int option = p.ObtenirOption();
		// padding
		// data
		
		/* Mise des donnee en byte[] */
		
		
		p_s = this.PortVerBit(port_src);
		p_d = this.PortVerBit(port_dst);
		nb_seq = this.TrenteDeuxVerBit(nbr_seq);
		nb_acc = this.TrenteDeuxVerBit(ack_nbr);
		data_reser_flag = this.seizeVerBit(p, reserve, data_off);
		fen = this.PortVerBit(fenetre);
		urg_point = this.PortVerBit(ptr_urg);
		headercheck = this.PortVerBit(0);
		opt = this.TrenteDeuxVerBit(0);
		data = p.ObtenirDonnee().getBytes();
		ip_sr = this.converIP(ip_src);
		ip_ds = this.converIP(ip_dest);
		rese_proto = this.PortVerBit(6);
		tcpHead = this.PortVerBit(24);
		
		/* Complement a 1 des byte[] */
		
		p_s = Utils.complement(p_s);
		p_d = Utils.complement(p_d);
		nb_seq = Utils.complement(nb_seq);
		nb_acc = Utils.complement(nb_acc);
		data_reser_flag = Utils.complement(data_reser_flag);
		fen = Utils.complement(fen);
		urg_point = Utils.complement(urg_point);
		headercheck = Utils.complement(headercheck);
		opt = Utils.complement(opt);
		data = Utils.complement(data);
		ip_sr = Utils.complement(ip_sr);
		ip_ds = Utils.complement(ip_ds);
		rese_proto = Utils.complement(rese_proto);
		tcpHead = Utils.complement(tcpHead);
		
		System.out.println("getcheck1 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(p_s[0], p_s[1]));
		System.out.println("getcheck2 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(p_d[0], p_d[1]));
		System.out.println("getcheck3 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(nb_seq[0], nb_seq[1]));
		System.out.println("getcheck4 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(nb_seq[2], nb_seq[3]));
		System.out.println("getcheck5 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(nb_acc[0], nb_acc[1]));
		System.out.println("getcheck6 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(nb_acc[2], nb_acc[3]));
		System.out.println("getcheck7 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(data_reser_flag[0], data_reser_flag[1]));
		System.out.println("getcheck8 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(fen[0], fen[1]));
		System.out.println("getcheck9 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(urg_point[0], urg_point[1]));
		System.out.println("getcheck10 : " + this.getCheck());
		//this.setCheck(this.getCheck() + this.calcul(headercheck[0], headercheck[1]));
		System.out.println("getcheck11 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(opt[0], opt[1]));
		System.out.println("getcheck12 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(opt[2], opt[3]));
		System.out.println("getcheck13 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(ip_sr[0], ip_sr[1]));
		System.out.println("getcheck14 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(ip_sr[2], ip_sr[3]));
		System.out.println("getcheck15 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(ip_ds[0], ip_ds[1]));
		System.out.println("getcheck16 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(ip_ds[2], ip_ds[3]));
		System.out.println("getcheck17 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(rese_proto[0], rese_proto[1]));
		System.out.println("getcheck18 : " + this.getCheck());
		this.setCheck(this.getCheck() + this.calcul(tcpHead[0], tcpHead[1]));
		System.out.println("getcheck19 : " + this.getCheck());
		if (p.ObtenirDonnee() != null)
		{
			System.out.println("jakez donne");
			data = p.ObtenirDonnee().getBytes();
			data = Utils.complement(data);
			int mod = 0;
			byte m = 0;
			mod = p.ObtenirDonnee().getBytes().length % 2;
			for (pos = 0; pos < p.ObtenirDonnee().getBytes().length; ++pos)
			{
				System.out.println("jakez donne2");
				if ((pos + 1) < (p.ObtenirDonnee().getBytes().length - 1))
					this.setCheck(this.getCheck() + this.calcul(data[pos], data[pos + 1]));
				if (mod == 1)
					this.setCheck(this.getCheck() + this.calcul(data[pos], m));
				++pos;
			}
				
		}
		
		seizeFinal = this.PortVerBit(this.getCheck());
		seizeFinal = Utils.complement(seizeFinal);
		this.setCheck(this.calcul(seizeFinal[0], seizeFinal[1]));
		return this.getCheck();
		
		
	}

	public BitSet getBitS() {
		return bitS;
	}

	public void setBitS(BitSet bitS) {
		this.bitS = bitS;
	}


	public int getCheck() {
		return check;
	}


	public void setCheck(int check) {
		this.check = check;
	}
}
