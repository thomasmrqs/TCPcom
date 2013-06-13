package graphique;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class OpenFrame extends JFrame  {

    /**
 * 
 */
private static final long serialVersionUID = 1L;
	
private JFrame openframe = null;
private PanelSend panelsend = null;
    /**
     * Constructeur
     */
    public OpenFrame () 
    {
        openframe = new JFrame();
        panelsend = new PanelSend();
        openframe.setTitle("Open");
        openframe.setLocationRelativeTo(null);
        openframe.setSize(400, 250);
        openframe.setContentPane(panelsend);
        openframe.setVisible(true);
    }
    
    private class PanelSend extends JPanel implements ActionListener
   {
	   
    	private JButton EnvoiBtnAccept;
	    private JCheckBox Actif;
	    private JLabel LabPortLocale, LabSocket, LabTempo;
	    private BoundedTextField PortLocale, Socket, Tempo;

	    /**
	     * Constructeur
	     */
	    public PanelSend() 
	    {
	        setLayout(null);
	        setPreferredSize(new Dimension(300, 200));
	        setBounds(5, 5, 305, 300);
	        setBorder(new TitledBorder("Open"));
	        
	        // Port Locale
	        LabPortLocale = new JLabel("Numero de port locale");
	        LabPortLocale.setFont(new Font("arial", 0, 12));
	        PortLocale = new BoundedTextField("", 32);
	        LabPortLocale.setBounds(10, 15, 180, 20);
	        PortLocale.setBounds(10, 35, 285, 20);
	        PortLocale.setText("");
	        add(LabPortLocale);
	        add(PortLocale);

	        // Numero Socket
	        LabSocket = new JLabel("Socket");
	        LabSocket.setFont(new Font("arial", 0, 12));
	        Socket = new BoundedTextField("", 32);
	        LabSocket.setBounds(10, 55, 180, 20);
	        Socket.setBounds(10, 75, 285, 20);
	        add(LabSocket);
	        add(Socket);

	        // Temporisation
	        LabTempo = new JLabel("Temporisation (min)");
	        LabTempo.setFont(new Font("arial", 0, 12));
	        Tempo = new BoundedTextField("", 4);
	        Tempo.setText("5");
	        LabTempo.setBounds(10, 95, 125, 20);
	        Tempo.setBounds(10, 115, 285, 20);
	        add(LabTempo);
	        add(Tempo);

	        // Actif Passif
	        Actif = new JCheckBox("Actif");
	        Actif.setFont(new Font("arial", 0, 12));
	        Actif.setBounds(10, 140, 60, 20);
	        add(Actif);



	        EnvoiBtnAccept = new JButton("Valider");
	        EnvoiBtnAccept.addActionListener(this);
	        add(EnvoiBtnAccept);
	        EnvoiBtnAccept.setBounds(10, 180, 100, 20);

	    }
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == EnvoiBtnAccept){
				/*************** Fonction open ***********************/
			}
			}
		}
}
