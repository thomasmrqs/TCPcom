package IHM;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleWindow extends JFrame implements ActionListener{

	private JButton bouton;
	private JButton bouton2;
	
	public SimpleWindow(){
		super();
		build();//On initialise notre fenêtre
	}
 
	private void build(){
		setTitle("TCPCOM - Jackqueze est homosexuel"); //On donne un titre à l'application
		setSize(1000,600); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}

	private Container buildContentPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
	 
		JLabel label = new JLabel("Bienvenue dans ma modeste application");
	 
		panel.add(label);
		
		bouton = new JButton("Bouton 1");
		bouton.addActionListener(this);
		panel.add(bouton);
 
		bouton2 = new JButton("Bouton 2");
		bouton2.addActionListener(this);
		panel.add(bouton2);
	 
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
 
		if(source == bouton){
			System.out.println("Action bouton 1.");
		} else if(source == bouton2){
			System.out.println("Action bouton 2.");	
		}
	}
}
