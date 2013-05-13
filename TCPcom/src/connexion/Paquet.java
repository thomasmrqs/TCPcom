/****************************************************/
/*             Author : Jakez                       */
/****************************************************/
package connexion;

import java.util.BitSet;

public class Paquet {

	private int PortSRC = 0;
	private int PortDST = 0;
	private int seq = 0;
	private int acc = 0;
	private int dataoff = 0;
	private int reserve = 0;
	private int fenetre = 0;
	private int checksum = 0;
	private int pointeur = 0;
	private int donnee = 0;
	private int option = 0;
	private boolean urg = false;
	private boolean ack = false;
	private boolean psh = false;
	private boolean rst = false;
	private boolean syn = false;
	private boolean fin = false;
	
	private BitSet bitS = new BitSet(192);
	private int bit = 0;
	
	public Paquet ()
	{
	}
	
	public Paquet (int src, int dst)
	{
		PortSRC = src;
		PortDST = dst;
	}
	
	public Paquet(String s) {
		
		// TODO Auto-generated constructor stub
	}

	public int ObtenirPortSRC () {
		return PortSRC;
	}
	
	public void MettrePortSRC (int src) {
		PortSRC = src;
	}

	public int ObtenirPortDST () {
		return PortDST;
	}
	
	public void MettrePortDST (int dst) {
		PortDST = dst;
	}
	
	 public void MettreNbrSeq (int s)
	 {
		 seq = s;
	 }
	
	public int ObtenirNbrSeq ()
	{
		return seq;
	}
	
	public void MettreNbrAcc (int a)
	 {
		 acc = a;
	 }

	public int ObtenirNbrAcc ()
		{
			return acc;
		}
	
	public void MettreDataOff (int d)
	 {
		 dataoff = d;
	 }

	public int ObtenirReserve ()
		{
			return reserve;
		}
	
	public void MettreReserve (int r)
	 {
		 reserve = r;
	 }

	public int ObtenirDataOff ()
		{
			return dataoff;
		}
	
	public void MettreUrg (boolean u)
	 {
		 urg = u;
	 }

	public boolean ObtenirUrg ()
		{
			return urg;
		}
		
	public void MettreAck (boolean a)
	 {
		 ack = a;
	 }

	public boolean ObtenirAck ()
		{
			return ack;
		}
	
	public void MettrePsh (boolean p)
	 {
		 psh = p;
	 }

	public boolean ObtenirPsh ()
		{
			return psh;
		}

	public void MettreRst (boolean r)
	 {
		 rst = r;
	 }

	public boolean ObtenirRst ()
		{
			return rst;
		}
	
	public void MettreSyn (boolean s)
	 {
		 syn = s;
	 }

	public boolean ObtenirSyn ()
		{
			return syn;
		}

	public void MettreFin (boolean f)
	 {
		 fin = f;
	 }

	public boolean ObtenirFin ()
		{
			return fin;
		}
	
	public void MettreFenetre (int f)
	 {
		 fenetre = f;
	 }

	public int ObtenirFenetre ()
		{
			return fenetre;
		}
	
	public void MettreChecksum (int c)
	 {
		 checksum = c;
	 }

	public int ObtenirChecksum ()
		{
			return checksum;
		}

	public void MettrePointeur (int p)
	 {
		 pointeur = p;
	 }

	public int ObtenirPointeur ()
		{
			return pointeur;
		}
	
	public void MettreOption (int o)
	 {
		 option = o;
	 }

	public int ObtenirOption ()
		{
			return option;
		}
	
	public void MettreDonnee (int d)
	 {
		 donnee = d;
	 }

	public int ObtenirDonnee ()
		{
			return donnee;
		}
	
	public BitSet ObtenirBitSet ()
	{
		return bitS;
	}
	
	
	public void PortVerBit (int port) {
		System.out.println(bit);
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		for (i = 15, j = 16; j > 0; i--, j--) {
			if (Math.pow(2,i) <= port)
			{
				c[i] = '1';
				port = (int) (port - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i=0, j = 15; j >= 0; i++, j--)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
		System.out.println(bitS.size());
	}
	
	public void SeqAccVerBit (boolean se)
	{
		int s = 0;
		if (se)
			s = seq;
		else 
			s = acc;
		char c[] = new char [32];
		int i = 0;
		int j = 0;
		for (i = 31, j = 32; j > 0; j--, --i)
		{
			if (Math.pow(2,i) <= s)
			{
				c[i] = '1';
				s = (int) (s - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i = 0, j = 32; j > 0; j--, ++i)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
	}
	
	public void DataOffVerBit ()
	{
		int s = dataoff;
		char c[] = new char [4];
		int i = 0;
		int j = 0;
		for (i = 3, j = 4; j > 0; --i, --j)
		{
			if (Math.pow(2,i) <= s)
			{
				c[i] = '1';
				s = (int) (s - Math.pow(2,i));
			}
		}
		for (i = 0, j = 4; j > 0; ++i, --j)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
	}
	
	
	public void RserveVerBit ()
	{
		int s = reserve;
		char c[] = new char [6];
		int i = 0;
		int j = 0;
		for (i = 5, j = 6; j > 0; --i, --j)
		{
			if (Math.pow(2,i) <= s)
			{
				c[i] = '1';
				s = (int) (s - Math.pow(2,i));
			}
		}
		for (i = 0, j = 6; j > 0; ++i, --j)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
	}
	
	public void ControleVerBit ()
	{
		if (fin)
			bitS.set(bit);
		else
			bitS.set(bit, false);
		++bit;
		if (syn)
			bitS.set(bit);
		else
			bitS.set(bit, false);
		++bit;
		if (rst)
			bitS.set(bit);
		else
			bitS.set(bit, false);
		++bit;
		if (psh)
			bitS.set(bit);
		else
			bitS.set(bit, false);
		++bit;
		if (ack)
			bitS.set(bit);
		else
			bitS.set(bit, false);
		++bit;
		if (urg)
			bitS.set(bit);
		else
			bitS.set(bit, false);
		++bit;
	}
	
	public void FentreVerBit (int fen) {
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		for (i = 15, j = 16; j > 0; i--, j--) {
			if (Math.pow(2,i) <= fen)
			{
				c[i] = '1';
				fen = (int) (fen - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i=0, j = 15; j >= 0; i++, j--)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
		
	}
	
	public void ChecksumVerBit (int check) {
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		for (i = 15, j = 16; j > 0; i--, j--) {
			if (Math.pow(2,i) <= check)
			{
				c[i] = '1';
				check = (int) (check - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i=0, j = 15; j >= 0; i++, j--)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
		
	}
	
	
	public void PointeurVerBit (int point) {
		char c[] = new char [16];
		int i = 0;
		int j = 0;
		for (i = 15, j = 16; j > 0; i--, j--) {
			if (Math.pow(2,i) <= point)
			{
				c[i] = '1';
				point = (int) (point - Math.pow(2,i));
			}
			else
				c[i] = '0';
		}
		for (i=0, j = 15; j >= 0; i++, j--)
		{
			if (c[i] == '1')
				bitS.set(bit);
			else
				bitS.set(bit, false);
			++bit;
		}
		
	}
	
	public void OptionVerBit ()
	{
		for (int j = 31; j >= 0; j--)
		{
			bitS.set(bit, false);
			++bit;
		}
	}
	
	public void AfficherPaquet ()
	{
		System.out.println ("Port source = " + PortSRC);
		System.out.println ("Port destinataire = " + PortDST);
		System.out.println ("Numéro de séquence = " + seq);
		System.out.println ("Accusé de reception = " + acc);
		System.out.println ("Data offset = " + dataoff);
		System.out.println ("Reservé = " + reserve);
		System.out.println ("URG ? " + urg);
		System.out.println ("ACK ? " + ack);
		System.out.println ("PSH ? " + psh);
		System.out.println ("RST ? " + rst);
		System.out.println ("SYN ? " + syn);
		System.out.println ("FIN ? " + fin);
		System.out.println ("Fenetre = " + fenetre);
		System.out.println ("Checksum = " + checksum);
		System.out.println ("Pointeur de donnée urgente = " + pointeur);
		System.out.println ("Reservé = " + reserve);
		System.out.println ("Données = " + donnee);
		/*System.out.println ("taille bit " + bitS.size());
		System.out.println ("tab : " + Utils.afficherOctet(this.ObtenirBitSet().toByteArray()[0]));
		System.out.println ("longuer: " + this.ObtenirBitSet().toByteArray().length);
		System.out.println ("tab 2: " + Utils.afficherOctet(this.ObtenirBitSet().toByteArray()[1]));*/
	}
	
	public BitSet CreerPaquetSansDonnee ()
	{
		PortVerBit(PortSRC);
		PortVerBit(PortDST);
		SeqAccVerBit(true);
		SeqAccVerBit(false);
		DataOffVerBit();
		RserveVerBit();
		ControleVerBit();
		FentreVerBit(fenetre);
		ChecksumVerBit(checksum);
		PointeurVerBit(pointeur);
		OptionVerBit();
		return bitS;
		
	}
	
	public byte[] convertirBitSetSansDonne ()
	{
		byte[] tab = new byte[24];
		for (int i = 15; i >= 0; --i)
		{
			if (i > 7 )
			{
				tab[1] = Utils.changerValeurBit(i - 8, tab[1], bitS.get(i));
			}
			else
				tab[0] = Utils.changerValeurBit(i, tab[0], bitS.get(i));
		}
		for (int i = 31, j = 15; i >= 16; --i, --j)
		{
			if (j > 7 )
			{
				tab[3] = Utils.changerValeurBit(j - 8, tab[3], bitS.get(i));
			}
			else
				tab[2] = Utils.changerValeurBit(j, tab[2], bitS.get(i));
 
		}
		for (int i = 63, j = 31; i >= 32; --i, --j)
		{
			if (j > 23)
				tab[7] = Utils.changerValeurBit(j - 24, tab[7], bitS.get(i));
			else if (j > 15)
				tab[6] = Utils.changerValeurBit(j - 16, tab[6], bitS.get(i));
			else if (j > 7)
				tab[5] = Utils.changerValeurBit(j - 8, tab[5], bitS.get(i));
			else
				tab[4] = Utils.changerValeurBit(j, tab[4], bitS.get(i));
		}
		for (int i = 95, j = 31; i >= 64; --i, --j)
		{
			if (j > 23)
				tab[11] = Utils.changerValeurBit(j - 24, tab[11], bitS.get(i));
			else if (j > 15)
				tab[10] = Utils.changerValeurBit(j - 16, tab[10], bitS.get(i));
			else if (j > 7)
				tab[9] = Utils.changerValeurBit(j - 8, tab[9], bitS.get(i));
			else
				tab[8] = Utils.changerValeurBit(j, tab[8], bitS.get(i));
		}
		
		//**********************Data offset*/////////////////////////
		for (int i = 127, j = 31; i >= 96; --i, --j)
		{
			if (j > 23)
				tab[15] = Utils.changerValeurBit(j - 24, tab[15], bitS.get(i));
			else if (j > 15)
				tab[14] = Utils.changerValeurBit(j - 16, tab[14], bitS.get(i));
			else if (j > 7)
				tab[13] = Utils.changerValeurBit(j - 8, tab[13], bitS.get(i));
			else
				tab[12] = Utils.changerValeurBit(j, tab[12], bitS.get(i));
		}
		for (int i = 159, j = 31; i >= 128; --i, --j)
		{
			if (j > 23)
				tab[19] = Utils.changerValeurBit(j - 24, tab[19], bitS.get(i));
			else if (j > 15)
				tab[18] = Utils.changerValeurBit(j - 16, tab[18], bitS.get(i));
			else if (j > 7)
				tab[17] = Utils.changerValeurBit(j - 8, tab[17], bitS.get(i));
			else
				tab[16] = Utils.changerValeurBit(j, tab[16], bitS.get(i));
		}
		for (int i = 191, j = 31; i >= 160; --i, --j)
		{
			if (j > 23)
				tab[23] = Utils.changerValeurBit(j - 24, tab[23], false);
			else if (j > 15)
				tab[22] = Utils.changerValeurBit(j - 16, tab[22], false);
			else if (j > 7)
				tab[21] = Utils.changerValeurBit(j - 8, tab[21], false);
			else
				tab[20] = Utils.changerValeurBit(j, tab[20], false);
		}
		//**********************************************////////////////////////////////
		
		
		//System.out.println(Utils.afficherOctet(tab[7]));
		//System.out.println(Utils.afficherOctet(tab[6]));
		//System.out.println(Utils.afficherOctet(tab[0]));
		//System.out.println(Utils.afficherOctet(tab[1]));
		
		
		return tab;
	}
}
