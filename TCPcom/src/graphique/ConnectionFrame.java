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

public class ConnectionFrame extends JFrame{


    /**
 * 
 */
private static final long serialVersionUID = 1L;
	
private JFrame conectframe = null;
private PanelSend panelsend = null;
    /**
     * Constructeur
     */
    public ConnectionFrame () 
    {
        conectframe = new JFrame();
        panelsend = new PanelSend();
        conectframe.setTitle("Connection");
        conectframe.setLocationRelativeTo(null);
        conectframe.setSize(400, 400);
        conectframe.setContentPane(panelsend);
        conectframe.setVisible(true);
    }
    
    private class PanelSend extends JPanel implements ActionListener
   {
	   
    	JButton EnvoiBtnAccept;
	    JCheckBox Actif;
	    JLabel LabIpSrc, LabIpDest, LabPortSrc, LabPortDest, LabId;
	    BoundedTextField IpSrc, IpDest, PortSrc, PortDest, Id;

	    /**
	     * Constructeur
	     */
	    public PanelSend() 
	    {
	        setLayout(null);
	        setPreferredSize(new Dimension(300, 200));
	        setBounds(5, 5, 305, 300);
	        setBorder(new TitledBorder("Connection"));
	        
	        // Ip src
	        LabIpSrc = new JLabel("Adresse Ip source");
	        LabIpSrc.setFont(new Font("arial", 0, 12));
	        IpSrc = new BoundedTextField("", 32);
	        LabIpSrc.setBounds(10, 15, 180, 20);
	        IpSrc.setBounds(10, 35, 285, 20);
	        IpSrc.setText("");
	        add(LabIpSrc);
	        add(IpSrc);

	        // Ip dest
	        LabIpDest = new JLabel("Adresse Ip Destination");
	        LabIpDest.setFont(new Font("arial", 0, 12));
	        IpDest = new BoundedTextField("", 32);
	        LabIpDest.setBounds(10, 55, 180, 20);
	        IpDest.setBounds(10, 75, 285, 20);
	        add(LabIpDest);
	        add(IpDest);

	     // Ip src
	        LabPortSrc = new JLabel("Numero de port Source");
	        LabPortSrc.setFont(new Font("arial", 0, 12));
	        PortSrc = new BoundedTextField("", 32);
	        LabPortSrc.setBounds(10, 95, 180, 20);
	        PortSrc.setBounds(10, 115, 285, 20);
	        PortSrc.setText("");
	        add(LabPortSrc);
	        add(PortSrc);

	        // Ip dest
	        LabPortDest = new JLabel("Numero de port Destination");
	        LabPortDest.setFont(new Font("arial", 0, 12));
	        PortDest = new BoundedTextField("", 32);
	        LabPortDest.setBounds(10, 135, 180, 20);
	        PortDest.setBounds(10, 155, 285, 20);
	        add(LabPortDest);
	        add(PortDest);
	        
	        // Identifiant
	        LabId = new JLabel("Identifiant");
	        LabId.setFont(new Font("arial", 0, 12));
	        Id = new BoundedTextField("", 4);
	        LabId.setBounds(10, 180, 125, 20);
	        Id.setBounds(10, 200, 285, 20);
	        add(LabId);
	        add(Id);

	        EnvoiBtnAccept = new JButton("Valider");
	        EnvoiBtnAccept.addActionListener(this);
	        add(EnvoiBtnAccept);
	        EnvoiBtnAccept.setBounds(10, 240, 100, 20);

	    }
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == EnvoiBtnAccept){
				/*************** Fonction connection ***********************/
				conectframe.dispose();
			}
			}
		}
}
