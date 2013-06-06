package test;

import connexion.Automate;
import connexion.GestionDesConnexions;

public class TestConnexionAutomate {

	public TestConnexionAutomate() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		GestionDesConnexions.get().lancerServeur("Test TOTO", 10001);
		Thread.sleep(500);
		Automate test = Automate.open(10000, "192.168.1.127", 10001, true);
		test.changerEtat();
	}

}
