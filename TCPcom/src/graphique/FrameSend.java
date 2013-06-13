package graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FrameSend extends JFrame 
{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
private JFrame framesend = null;
private PanelSend panelsend = null;
	    /**
	     * Constructeur
	     */
	    public FrameSend () 
	    {
	        framesend = new JFrame();
	        panelsend = new PanelSend();
	        framesend.setTitle("Segment TCP en émission");
	        framesend.setLocationRelativeTo(null);
	        framesend.setSize(400, 400);
	        framesend.setContentPane(panelsend);
	        //framesend.getContentPane().add(panelsend);
	        framesend.setVisible(true);
	    }
	    
	   private class PanelSend extends JPanel implements ActionListener
	   {
		   
		   private JButton EnvoiBtnAccept, EnvoiBtnParcourir;
		    private JCheckBox EnvoiURG, EnvoiACK, EnvoiPSH, EnvoiSYN, EnvoiRST, EnvoiFIN;
		    private JLabel EnvoiTitle, EnvoiLabNum, EnvoiLabAqu, EnvoiLabOff, EnvoiLabFen, EnvoiLabChk, EnvoiLabPtr, EnvoiLabData;
		  private  BoundedTextField EnvoiNum, EnvoiAqu, EnvoiOff, EnvoiFen, EnvoiChk, EnvoiPtr, EnvoiData;
		    private ImageIcon imageParcourir = null;
		    private File fichierselectionne = null;
		    /**
		     * Constructeur
		     */
		    public PanelSend() 
		    {
		        setLayout(null);
		        setPreferredSize(new Dimension(300, 300));
		        setBounds(5, 5, 305, 300);
		        setBorder(new TitledBorder("Segment TCP en émission"));
		        
		        imageParcourir = new ImageIcon(PanelSend.class.getResource("IMAGES/browse.gif"));
		        // Numéro de séquence
		        EnvoiLabNum = new JLabel("Numéro de séquence (32bits)");
		        EnvoiLabNum.setFont(new Font("arial", 0, 12));
		        EnvoiNum = new BoundedTextField("", 32);
		        EnvoiLabNum.setBounds(10, 15, 180, 20);
		        EnvoiNum.setBounds(10, 35, 285, 20);
		        EnvoiNum.setText("");
		        add(EnvoiLabNum);
		        add(EnvoiNum);

		        // Numéro d'acquitement
		        EnvoiLabAqu = new JLabel("Numéro d'acquittement (32bits)");
		        EnvoiLabAqu.setFont(new Font("arial", 0, 12));
		        EnvoiAqu = new BoundedTextField("", 32);
		        EnvoiLabAqu.setBounds(10, 55, 180, 20);
		        EnvoiAqu.setBounds(10, 75, 285, 20);
		        add(EnvoiLabAqu);
		        add(EnvoiAqu);

		        EnvoiLabOff = new JLabel("Offset (4bits)");
		        EnvoiLabOff.setFont(new Font("arial", 0, 12));
		        EnvoiOff = new BoundedTextField("", 4);
		        EnvoiLabOff.setBounds(10, 95, 125, 20);
		        EnvoiOff.setBounds(10, 115, 125, 20);
		        add(EnvoiLabOff);
		        add(EnvoiOff);

		        // URG
		        EnvoiURG = new JCheckBox("URG");
		        EnvoiURG.setFont(new Font("arial", 0, 12));
		        EnvoiURG.setBounds(138, 103, 60, 20);
		        add(EnvoiURG);

		        // ACK
		        EnvoiACK = new JCheckBox("ACK");
		        EnvoiACK.setFont(new Font("arial", 0, 12));
		        EnvoiACK.setBounds(193, 103, 52, 20);
		        add(EnvoiACK);

		        // PSH
		        EnvoiPSH = new JCheckBox("PSH");
		        EnvoiPSH.setFont(new Font("arial", 0, 12));
		        EnvoiPSH.setBounds(248, 103, 60, 20);
		        add(EnvoiPSH);

		        // RST
		        EnvoiRST = new JCheckBox("RST");
		        EnvoiRST.setFont(new Font("arial", 0, 12));
		        EnvoiRST.setBounds(138, 123, 60, 20);
		        add(EnvoiRST);

		        // SYN
		        EnvoiSYN = new JCheckBox("SYN");
		        EnvoiSYN.setFont(new Font("arial", 0, 12));
		        EnvoiSYN.setBounds(193, 123, 60, 20);
		        add(EnvoiSYN);

		        // FIN
		        EnvoiFIN = new JCheckBox("FIN");
		        EnvoiFIN.setFont(new Font("arial", 0, 12));
		        EnvoiFIN.setBounds(248, 123, 60, 20);
		        add(EnvoiFIN);

		        // Fenêtre
		        EnvoiLabFen = new JLabel("Fenêtre (16bits)");
		        EnvoiLabFen.setFont(new Font("arial", 0, 12));
		        EnvoiFen = new BoundedTextField("", 16);
		        EnvoiLabFen.setBounds(10, 135, 285, 20);
		        EnvoiFen.setBounds(10, 155, 285, 20);
		        add(EnvoiLabFen);
		        add(EnvoiFen);

		        // Checksum
		        EnvoiLabChk = new JLabel("Checksum (16bits)");
		        EnvoiLabChk.setFont(new Font("arial", 0, 12));
		        EnvoiChk = new BoundedTextField("", 16);
		        EnvoiLabChk.setBounds(10, 175, 140, 20);
		        EnvoiChk.setBounds(10, 195, 140, 20);
		        add(EnvoiLabChk);
		        add(EnvoiChk);

		        EnvoiLabPtr = new JLabel("Pointeur urgent (16bits)");
		        EnvoiLabPtr.setFont(new Font("arial", 0, 12));
		        EnvoiPtr = new BoundedTextField("", 16);
		        add(EnvoiLabPtr);
		        add(EnvoiPtr);
		        EnvoiLabPtr.setBounds(155, 175, 140, 20);
		        EnvoiPtr.setBounds(155, 195, 140, 20);

		        // Data
		        EnvoiLabData = new JLabel("Données");
		        EnvoiLabData.setFont(new Font("arial", 0, 12));
		        EnvoiData = new BoundedTextField("", 100);
		        EnvoiLabData.setBounds(10, 215, 285, 20);
		        EnvoiData.setBounds(10, 235, 285, 20);
		        add(EnvoiLabData);
		        add(EnvoiData);

		        EnvoiBtnAccept = new JButton("Send");
		        EnvoiBtnAccept.addActionListener(this);
		        add(EnvoiBtnAccept);
		        EnvoiBtnAccept.setBounds(10, 265, 100, 20);
		        EnvoiBtnParcourir = new JButton("Parcourir");
		        EnvoiBtnParcourir.addActionListener(this);
		        add(EnvoiBtnParcourir);
		        EnvoiBtnParcourir.setBounds(195, 265, 200, 32);
		        EnvoiBtnParcourir.setIcon(imageParcourir);
		    }
		   
		   
		    protected void paintComponent(Graphics g) 
            {
               
                BufferedImage image;
				try {
					image = ImageIO.read(PanelConsole.class.getResource("Automate/black-world.jpg"));
					 g.drawImage(image, 0, 0, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 


                super.paintComponent(g);
            }
		    
		    
		   // traitement
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if (e.getSource() == EnvoiBtnParcourir)
			{
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
			    jfc.setFileFilter(new javax.swing.filechooser.FileFilter() 
			    {
			      public boolean accept(File f) 
			      {
			    	  
			    	  String fichier = f.toString().toLowerCase();
			        if (f.isDirectory() || !fichier.isEmpty())
			        
			        	return true;
			   
			        else
			          return false;
			      }
			      public String getDescription() 
			      {
			    	  return ".jpg, .gif, .png, .ico";
			      }
			    
			    });
			    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    jfc.setMultiSelectionEnabled(false);
			    
			    int retour = jfc.showOpenDialog(this);
			   
			    if (retour == JFileChooser.APPROVE_OPTION)
			      panelsend.setFichierselectionne(jfc.getSelectedFile());
			    
			}
			//System.out.println(panelsend.getFichierselectionne().toString());
		}


		public File getFichierselectionne() 
		{
			return fichierselectionne;
		}
		

		public void setFichierselectionne(File fichierselectionne) 
		{
			this.fichierselectionne = fichierselectionne;
		}
		   
	   }
	    
	    
	
}