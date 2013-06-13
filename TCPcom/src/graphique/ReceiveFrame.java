package graphique;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ReceiveFrame extends JFrame{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JFrame receiveframe = null;
	private PanelSend panelsend = null;
	    /**
	     * Constructeur
	     */
	    public ReceiveFrame() 
	    {
	        receiveframe = new JFrame();
	        panelsend = new PanelSend();
	        receiveframe.setTitle("Receive");
	        receiveframe.setLocationRelativeTo(null);
	        receiveframe.setSize(400, 250);
	        receiveframe.setContentPane(panelsend);
	        receiveframe.setVisible(true);
	    }
	    
	    private class PanelSend extends JPanel implements ActionListener
	   {
		   
	    	JButton EnvoiBtnAccept;
		    JLabel LabNomLocale, LabAddrTampon, LabCompteur;
		    BoundedTextField NomLocale, AddrTampon, Compteur;

		    /**
		     * Constructeur
		     */
		    public PanelSend() 
		    {
		        setLayout(null);
		        setPreferredSize(new Dimension(300, 200));
		        setBounds(5, 5, 305, 300);
		        setBorder(new TitledBorder("Receive"));
		        
		        // Nom Locale
		        LabNomLocale = new JLabel("Noms locale");
		        LabNomLocale.setFont(new Font("arial", 0, 12));
		        NomLocale = new BoundedTextField("", 32);
		        LabNomLocale.setBounds(10, 15, 180, 20);
		        NomLocale.setBounds(10, 35, 285, 20);
		        NomLocale.setText("");
		        add(LabNomLocale);
		        add(NomLocale);

		        // Adresse Tampon
		        LabAddrTampon = new JLabel("Adresse Tampon");
		        LabAddrTampon.setFont(new Font("arial", 0, 12));
		        AddrTampon = new BoundedTextField("", 32);
		        LabAddrTampon.setBounds(10, 55, 180, 20);
		        AddrTampon.setBounds(10, 75, 285, 20);
		        add(LabAddrTampon);
		        add(AddrTampon);
		        
		        // Compteur
		        LabCompteur = new JLabel("Compteur");
		        LabCompteur.setFont(new Font("arial", 0, 12));
		        Compteur = new BoundedTextField("", 4);
		        LabCompteur.setBounds(10, 95, 125, 20);
		        Compteur.setBounds(10, 115, 285, 20);
		        add(LabCompteur);
		        add(Compteur);


		        EnvoiBtnAccept = new JButton("Valider");
		        EnvoiBtnAccept.addActionListener(this);
		        add(EnvoiBtnAccept);
		        EnvoiBtnAccept.setBounds(10, 155, 100, 20);

		    }
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == EnvoiBtnAccept){
					
					/*************** Fonction receive
					 * param: NomLocale
					 * 		  AddrTampon
					 * 		  Compteur
					 *   ***********************/
					receiveframe.dispose();
				}
				}
			}

}
