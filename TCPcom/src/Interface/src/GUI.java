import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class GUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	private JFrame fenetre = null;
	private JMenu connexion = null;
	private JMenu deconnexion = null;
	private JMenu mode = null;
	private JMenu manuel = null;
	private JMenu help = null;
	private JButton sbs = null;
	private JButton ssl = null;
	private JButton defaultOption = null;
	private JMenu option = null;
	private JButton client = null;
	private JButton server = null;
	private JButton valider = null;
	private JMenuBar mb = null;
	//private FrameSend send = null;
	//private ImageIcon img = null;
	private Boolean connect = false;
	private Boolean clientflag = false;
	private Boolean serverflag = false;
	private Boolean sbsflag = false;
	private Boolean defaultflag = false;
	private Boolean sslflag = false;
	private JOptionPane jop1 = null;
	private PanelConsole console = null;
	private PanelAutomate automate = null;
	private JPanel imagedefond = null;
	//private JPanel pan = null;
	DegradPanel degradPanel = new DegradPanel();
	public GUI() 
	{
		
		fenetre = new JFrame();
		mb = new  JMenuBar();
		connexion = new JMenu();
		deconnexion = new JMenu();
		option = new JMenu();
		manuel = new JMenu();
		help = new JMenu();
		mode = new JMenu();
		client = new JButton();
		server = new JButton();
		valider = new JButton();
		sbs = new JButton();
		ssl = new JButton();
		defaultOption = new JButton();
		jop1 = new JOptionPane();
		console = new PanelConsole();
		automate = new PanelAutomate();
		//imagedefond = new JPanel();
		//panelsend = new PanelSend();
		//pan = new JPanel();
		fenetre.setTitle("                                                                            TRANSMISSION          CONTROL           PROTOCOL    (T.C.P)      ");
		fenetre.setBackground(Color.BLACK);
		//img = new ImageIcon("network.png");
		//if (img.getImage() == null)
			//System.out.println("OK");
		//fenetre.setIconImage(img.getImage());
			fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("IMAGES/connect.gif")));
			
			fenetre.setSize((int)getToolkit().getScreenSize().getWidth(), (int)getToolkit().getScreenSize().getHeight());
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pan.setBackground(Color.DARK_GRAY);
		//fenetre.getContentPane().add(degradPanel);
		//fenetre.getContentPane().setBackground(degradPanel);
	//Imagedefond();
		fenetre.getContentPane().add(automate);
		//fenetre.getContentPane().setBackground(Color.DARK_GRAY);
		//fenetre.setBackground(Color.DARK_GRAY);		
		//fenetre.add(new Fond());
		fenetre.getContentPane().add(console);
		fenetre.setVisible(true);
		fenetre.setJMenuBar(mb);
	}
	
	/*public void Imagedefond() 
	{
			imagedefond = new JPanel() {
			public void paint(Graphics g) {
				try {
					BufferedImage image = ImageIO.read(GUI.class.getResource("IMAGES/wall.jpg"));
					image.
					g.drawImage(image, 0, 0, null);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		};
		imagedefond.add(console);
		imagedefond.add(automate);
		//imagedefond.setPreferredSize(new Dimension(300, 800));
		//imagedefond = fenetre.getContentPane();
		//imagedefond.setBounds(980, 0,(int)getToolkit().getScreenSize().getWidth()/2 - 980,(int)getToolkit().getScreenSize().getHeight() - 100);
		fenetre.setContentPane(imagedefond);
		//fenetre.getContentPane().add(imagedefond, BorderLayout.CENTER);
		
}*/
	
	
	public void CreateTCPJMenuBar ()
	{
		connexion.setText("connexion".toUpperCase());
		deconnexion.setText("deconnexion".toUpperCase());
		mode.setText("mode".toUpperCase());
		option.setText("option".toUpperCase());
		client.setText("client");
		server.setText("serveur");
		valider.setText("valider");
		sbs.setText("Step-by-Step");
		ssl.setText("Secure Sockets Layer");
		defaultOption.setText("Default Option");
		manuel.setText("manuel".toUpperCase());
		help.setText("AIDE & FEEDBACK");
connexion.setIcon(new ImageIcon(getClass().getResource("/IMAGES/connect.gif")));
	deconnexion.setIcon(new ImageIcon(getClass().getResource("/IMAGES/deconnexion.jpeg")));
	manuel.setIcon(new ImageIcon(getClass().getResource("/IMAGES/manuel.png")));
	help.setIcon(new ImageIcon(getClass().getResource("/IMAGES/aide.png")));
		connexion.setMnemonic(KeyEvent.VK_C);
		deconnexion.setMnemonic(KeyEvent.VK_D);
		manuel.setMnemonic(KeyEvent.VK_M);
		help.setMnemonic(KeyEvent.VK_H);
		
		mb.add(connexion);
		mb.add(deconnexion);
		mb.add(manuel);
		mb.add(help);
		connexion.add(mode);
		connexion.add(option);
		connexion.add(valider);
		mode.add(client);
		mode.add(server);
		option.add(sbs);
		option.add(ssl);
		option.add(defaultOption);
		
			valider.addActionListener(new java.awt.event.ActionListener()
			{

				@SuppressWarnings("static-access")
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if ((clientflag || serverflag) && (sbsflag || sslflag || defaultflag))
					
						valider_actionPerformed(e);
					//new PanelSend();
					//System.out.println("connect");
					else
						jop1.showMessageDialog(null, "SELECTIONNER UN MODE ET UNE OPTION", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			
		});
		
			
			client.addActionListener(new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					client_actionPerformed(e);
					//new PanelSend();
					//System.out.println("connect");
				}
				
			
		});
			
			server.addActionListener(new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					server_actionPerformed(e);
					//new PanelSend();
					//System.out.println("connect");
				}
				
			
		});
			
			sbs.addActionListener(new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					sbs_actionPerformed(e);
					//new PanelSend();
					//System.out.println("connect");
				}
				
			
		});
			
			ssl.addActionListener(new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					ssl_actionPerformed(e);
					//new PanelSend();
					//System.out.println("connect");
				}
				
			
		});
			defaultOption.addActionListener(new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					defaultOption_actionPerformed(e);
					//new PanelSend();
					//System.out.println("connect");
				}
				
			
		});
			
	}
	
	

	/*@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}*/
	
	
	public void valider_actionPerformed(ActionEvent e)
	{
		//if ((e.getSource() == client || e.getSource() == server) && (e.getSource() == sbs || e.getSource() == ssl || e.getSource() == defaultOption))
		
			
		//if (e.getSource() == valider)
			//{
				//System.out.println("Connect");
				//connect = true;
				//if (connect == true)
			//	{
			    //System.out.println("LOL");
				new FrameSend();
				//fenetre.getContentPane().add(panelsend);
	
				//}
			//}
	}
	
	public void client_actionPerformed(ActionEvent e)
	{
		clientflag = true;
	}
	
	public void server_actionPerformed(ActionEvent e)
	{
		serverflag = true;
	}
	
	public void ssl_actionPerformed(ActionEvent e)
	{
		sslflag = true;
	}
	public void defaultOption_actionPerformed(ActionEvent e)
	{
		defaultflag = true;
	}
	
	public void sbs_actionPerformed(ActionEvent e)
	{
		sbsflag = true;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		try 
		{
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		    
		SplashScreenMain splash =	new SplashScreenMain();
		splash.splash();
		GUI gui = new GUI();
		gui.CreateTCPJMenuBar();
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}*/
	
}
