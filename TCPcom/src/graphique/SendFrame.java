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

public class SendFrame extends JFrame{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JFrame sendframe = null;
	private PanelSend panelsend = null;
	    /**
	     * Constructeur
	     */
	    public SendFrame () 
	    {
	        sendframe = new JFrame();
	        panelsend = new PanelSend();
	        sendframe.setTitle("Send");
	        sendframe.setLocationRelativeTo(null);
	        sendframe.setSize(400, 300);
	        sendframe.setContentPane(panelsend);
	        sendframe.setVisible(true);
	    }
	    
	    private class PanelSend extends JPanel implements ActionListener
	   {
		   
	    	private JButton EnvoiBtnAccept;
		    private JCheckBox Push, Urg;
		    private JLabel LabNomLocale, LabAddrTampon, LabTempo, LabCompteur;
		    private BoundedTextField NomLocale, AddrTampon, Tempo, Compteur;

		    /**
		     * Constructeur
		     */
		    public PanelSend() 
		    {
		        setLayout(null);
		        setPreferredSize(new Dimension(300, 200));
		        setBounds(5, 5, 305, 300);
		        setBorder(new TitledBorder("Send"));
		        
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

		        // Temporisation
		        LabTempo = new JLabel("Temporisation (min)");
		        LabTempo.setFont(new Font("arial", 0, 12));
		        Tempo = new BoundedTextField("", 4);
		        Tempo.setText("5");
		        LabTempo.setBounds(10, 95, 125, 20);
		        Tempo.setBounds(10, 115, 285, 20);
		        add(LabTempo);
		        add(Tempo);
		        
		        // Compteur
		        LabCompteur = new JLabel("Compteur");
		        LabCompteur.setFont(new Font("arial", 0, 12));
		        Compteur = new BoundedTextField("", 4);
		        LabCompteur.setBounds(10, 140, 125, 20);
		        Compteur.setBounds(10, 160, 285, 20);
		        add(LabCompteur);
		        add(Compteur);

		        // Push
		        Push = new JCheckBox("Push");
		        Push.setFont(new Font("arial", 0, 12));
		        Push.setBounds(10, 200, 60, 20);
		        add(Push);
		        
		        // Urg
		        Urg = new JCheckBox("Urg");
		        Urg.setFont(new Font("arial", 0, 12));
		        Urg.setBounds(150, 200, 60, 20);
		        add(Urg);



		        EnvoiBtnAccept = new JButton("Valider");
		        EnvoiBtnAccept.addActionListener(this);
		        add(EnvoiBtnAccept);
		        EnvoiBtnAccept.setBounds(10, 230, 100, 20);

		    }
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if (e.getSource() == EnvoiBtnAccept)
				{
					
					/*************** Fonction send
					 * param: NomLocale
					 * 		  AddrTampon
					 * 		  Tempo
					 * 		  Compteur
					 * 		  Urg
					 * 		  Push
					 *  ***********************/
					
				}
				}
			}
	}
