package IHM;

import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class Test {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On cr�e une nouvelle instance de notre JWindow
				JWindow window = new JWindow();
				window.setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
				window.setVisible(true);//On la rend visible
			}
		});
		try { 
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
 
		System.exit(0);
	}
}