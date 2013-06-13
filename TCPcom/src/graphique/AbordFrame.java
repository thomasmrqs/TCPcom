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

public class AbordFrame extends JFrame{

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JFrame abordframe = null;
	private PanelSend panelsend = null;
	    /**
	     * Constructeur
	     */
	    public AbordFrame() 
	    {
	        abordframe = new JFrame();
	        panelsend = new PanelSend();
	        abordframe.setTitle("Abort");
	        abordframe.setLocationRelativeTo(null);
	        abordframe.setSize(400, 150);
	        abordframe.setContentPane(panelsend);
	        abordframe.setVisible(true);
	    }
	    
	    private class PanelSend extends JPanel implements ActionListener
	   {
		   
	    	JButton EnvoiBtnAccept;
		    JLabel LabNomLocale;
		    BoundedTextField NomLocale;

		    /**
		     * Constructeur
		     */
		    public PanelSend() 
		    {
		        setLayout(null);
		        setPreferredSize(new Dimension(300, 200));
		        setBounds(5, 5, 305, 300);
		        setBorder(new TitledBorder("Abord"));
		        
		        // Nom Locale
		        LabNomLocale = new JLabel("Noms locale");
		        LabNomLocale.setFont(new Font("arial", 0, 12));
		        NomLocale = new BoundedTextField("", 32);
		        LabNomLocale.setBounds(10, 15, 180, 20);
		        NomLocale.setBounds(10, 35, 285, 20);
		        NomLocale.setText("");
		        add(LabNomLocale);
		        add(NomLocale);

		        EnvoiBtnAccept = new JButton("Valider");
		        EnvoiBtnAccept.addActionListener(this);
		        add(EnvoiBtnAccept);
		        EnvoiBtnAccept.setBounds(10, 75, 100, 20);

		    }
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == EnvoiBtnAccept){
					
					/*************** Fonction abord
					 * param: NomLocale
					 *  ***********************/
					
					abordframe.dispose();
				}
				}
			}
	
	
}
