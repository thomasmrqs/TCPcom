package IHM;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestJDialog {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On cr�e une nouvelle instance de notre JDialog
				JDialog dialog = new JDialog();
				dialog.setSize(300, 200);//On lui donne une taille
				dialog.setTitle("Premi�re fen�tre"); //On lui donne un titre
				dialog.setVisible(true);//On la rend visible
			}
		});
	}
}
