/****************************************************/
/*             Author : Jakez                       */
/****************************************************/


package connexion;

import java.util.BitSet;

public class Decompression {
	
	private Paquet p = new Paquet ();
	private byte[] segment;
	
	public Decompression(byte[] b) {
		// TODO Auto-generated constructor stub
		segment = b;		
	}
	
	public void DecompSRC ()
	{
		char[] c = new char[16];
		int i = 0;
		int j = 0;
		int src = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[0],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[1],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 16; ++i)
		{
			if (c[i] == '1')
				src = (int) (src + Math.pow(2,i));
		}
		p.MettrePortSRC(src);
	}
	
	public void DecompDST ()
	{
		char[] c = new char[16];
		int i = 0;
		int j = 0;
		int dst = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[2],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[3],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 16; ++i)
		{
			if (c[i] == '1')
				dst = (int) (dst + Math.pow(2,i));
		}
		p.MettrePortDST(dst);
	}
	
	public void DecompSeq ()
	{
		char[] c = new char[32];
		int i = 0;
		int j = 0;
		int seq = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[4],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[5],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0, j = 16; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[6],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0, j = 24; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[7],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 32; ++i)
		{
			if (c[i] == '1')
				seq = (int) (seq + Math.pow(2,i));
		}
		p.MettreNbrSeq(seq);
		System.out.println("seq" + seq);
	}
	
	public void DecompAcc ()
	{
		char[] c = new char[32];
		int i = 0;
		int j = 0;
		int acc = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[8],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[9],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0, j = 16; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[10],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0, j = 24; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[11],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 32; ++i)
		{
			if (c[i] == '1')
				acc = (int) (acc + Math.pow(2,i));
		}
		p.MettreNbrAcc(acc);
		System.out.println("acc = " + acc);
	}
	

	public void DecompDatOff ()
	{
		char[] c = new char[4];
		int i = 0;
		int j = 0;
		int data = 0;
		for (i=0; i < 4; ++i)
		{
			if (Utils.bitEstVrai(segment[12],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		for (i = 0; i < 4; ++i)
		{
			if (c[i] == '1')
				data = (int) (data + Math.pow(2,i));
		}
		p.MettreDataOff(data);
		System.out.println("data = " + data);
	}
	
	public void DecompReserve ()
	{
		char[] c = new char[6];
		int i = 0;
		int j = 0;
		int res = 0;
		for (i=4, j = 0; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[12],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0, j = 4; i < 2; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[13],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 6; ++i)
		{
			if (c[i] == '1')
				res = (int) (res + Math.pow(2,i));
		}
		p.MettreReserve(res);
		System.out.println("res = " + res);
	}
	
	public void DecompFlag ()
	{
		char[] c = new char[6];
		int i = 0;
		int j = 0;
		int res = 0;
		for (i=2, j = 0; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[13],i))
				c[j] = '1';
			else
				c[j] = '0';
		}

		for (i = 0; i < 6; ++i)
		{
			if (c[i] == '1')
				switch (i)
				{
				case 0 : 
					p.MettreUrg(true);
					break;
				case 1 : 
					p.MettreAck(true);
					break;
				case 2 :
					p.MettrePsh(true);
					break;
				case 3 :
					p.MettreRst(true);
					break;
				case 4 :
					p.MettreSyn(true);
					break;
				case 5 : 
					p.MettreFin(true);
					break;
				}
		}

	}
	public void DecompFen ()
	{
		char[] c = new char[16];
		int i = 0;
		int j = 0;
		int fen = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[14],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[15],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 16; ++i)
		{
			if (c[i] == '1')
				fen = (int) (fen + Math.pow(2,i));
		}
		p.MettreFenetre(fen);
		System.out.println("fenetre = " + fen);
	}
	
	public void DecompChecksum ()
	{
		char[] c = new char[16];
		int i = 0;
		int j = 0;
		int check = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[16],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[17],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 16; ++i)
		{
			if (c[i] == '1')
				check = (int) (check + Math.pow(2,i));
		}
		p.MettreChecksum(check);
		System.out.println("check = " + check);
	}

	public void DecompPonteur ()
	{
		char[] c = new char[16];
		int i = 0;
		int j = 0;
		int point = 0;
		for (i=0; i < 8; ++i)
		{
				
			if (Utils.bitEstVrai(segment[18],i))
				c[i] = '1';
			else
				c[i] = '0';
		}
		
		for (i = 0, j = 8; i < 8; ++i, ++j)
		{
			if (Utils.bitEstVrai(segment[19],i))
				c[j] = '1';
			else
				c[j] = '0';
		}
		for (i = 0; i < 16; ++i)
		{
			if (c[i] == '1')
				point = (int) (point + Math.pow(2,i));
		}
		p.MettrePointeur(point);
		System.out.println("point = " + point);
	}

	public void DecompOption ()
	{
		p.MettreOption(0);
	}

	public Paquet DecompSegment ()
	{
		this.DecompSRC();
		this.DecompDST();
		this.DecompSeq();
		this.DecompAcc();
		this.DecompDatOff();
		this.DecompReserve();
		this.DecompFlag();
		this.DecompFen();
		this.DecompChecksum();
		this.DecompPonteur();
		this.DecompOption();
		return p;
	}
	
	
}
