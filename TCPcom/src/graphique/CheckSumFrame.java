package graphique;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CheckSumFrame extends JFrame {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JFrame chksumframe = null;
	private PanelSend panelsend = null;
	    /**
	     * Constructeur
	     */
	    public CheckSumFrame() 
	    {
	        chksumframe = new JFrame();
	        panelsend = new PanelSend();
	        chksumframe.setTitle("Abort");
	        chksumframe.setLocationRelativeTo(null);
	        chksumframe.setSize(400, 150);
	        chksumframe.setContentPane(panelsend);
	        chksumframe.setVisible(true);
	    }
	    
	    private class PanelSend extends JPanel implements ActionListener
	   {
		   
	    	JButton EnvoiBtnAccept;
		    JLabel Labchksum;
		    BoundedTextField chksum;

		    /**
		     * Constructeur
		     */
		    public PanelSend() 
		    {
		        setLayout(null);
		        setPreferredSize(new Dimension(300, 200));
		        setBounds(5, 5, 305, 300);
		        setBorder(new TitledBorder("Modifier Checksum"));
		        
		        // Nom Locale
		        Labchksum = new JLabel("Checksum");
		        Labchksum.setFont(new Font("arial", 0, 12));
		        chksum = new BoundedTextField("", 32);
		        Labchksum.setBounds(10, 15, 180, 20);
		        chksum.setBounds(10, 35, 285, 20);
		        chksum.setText("");
		        add(Labchksum);
		        add(chksum);

		        EnvoiBtnAccept = new JButton("Valider");
		        EnvoiBtnAccept.addActionListener(this);
		        add(EnvoiBtnAccept);
		        EnvoiBtnAccept.setBounds(10, 75, 100, 20);

		    }
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if (e.getSource() == EnvoiBtnAccept)
				{
					
					/*************** Fonction chg chksum ***********************/
					
					
				}
				}
			}
	
}