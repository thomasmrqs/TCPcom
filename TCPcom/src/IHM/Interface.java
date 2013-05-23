package IHM;

import javax.swing.SwingUtilities;

public class Interface {


	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				SimpleWindow fenetre = new SimpleWindow();
				fenetre.setVisible(true);//On la rend visible
			}
		});
	}
}
