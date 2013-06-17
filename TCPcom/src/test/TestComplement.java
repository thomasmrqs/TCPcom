package test;

import connexion.Utils;

public class TestComplement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] tab = new byte[1];
		String s = "00001011";
		tab = s.getBytes();
		System.out.println("_"  + Utils.afficherOctet(tab[tab.length - 1]) + "_"  );
		System.out.println("test");
		
		System.out.println("test");

		System.out.println("_"  + Utils.afficherOctet(Utils.complement(tab)[tab.length - 1]) + "_"  );
		
	}

}
