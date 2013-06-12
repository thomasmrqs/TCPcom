package graphique;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.Console;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.SplitPaneUI;


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
	private JPanel contain = null;
	private JTabbedPane onglets = null;
	//private Bouton bouton = null;
	private JPanel cards;
	private JSplitPane splitpane = null;
	private JPanel cards_tmp = null;
	//private JPanel test = new JPanel();
	private int count = 1;
	private int count_tmp = 1;
	private int count_serv =1;
	private int count_serv_tmp = 1;
	DegradPanel degradPanel = new DegradPanel();
	public GUI() throws IOException 
	{
		
		fenetre = new JFrame();
		mb = new  JMenuBar();
		onglets = new JTabbedPane();
		connexion = new JMenu();
		deconnexion = new JMenu();
		contain = new JPanel();
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
		cards = new JPanel (new CardLayout());
		console = new PanelConsole();
		automate = new PanelAutomate(console);
		cards.setLayout(null);
		console.setLocation(10, 5);
		cards.add(console);
		cards.add(automate);
		fenetre.setTitle("                                                                            TRANSMISSION          CONTROL           PROTOCOL    (T.C.P)      ");
		fenetre.setBackground(Color.BLACK);
			fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("IMAGES/connect.gif")));
			
			fenetre.setSize((int)getToolkit().getScreenSize().getWidth(), (int)getToolkit().getScreenSize().getHeight() - 10);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.getContentPane().add(onglets);
		fenetre.setVisible(true);
		fenetre.setJMenuBar(mb);
		cards_tmp = new JPanel();
	}
	
	
		
		

	
	
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
connexion.setIcon(new ImageIcon(getClass().getResource("./IMAGES/connect.gif")));
	deconnexion.setIcon(new ImageIcon(getClass().getResource("./IMAGES/deconnexion.jpeg")));
	manuel.setIcon(new ImageIcon(getClass().getResource("./IMAGES/manuel.png")));
	help.setIcon(new ImageIcon(getClass().getResource("./IMAGES/aide.png")));
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
	
	
	public void creer_onglet_client(){
		if (count == 1)
			
			onglets.addTab("Client " + count, new ImageIcon(getClass().getResource("./IMAGES/Client.gif")) , cards, "Client");
			
			//System.out.println("APPUYER");
		
			if (count > count_tmp)
			{
				
				//for (int i = 2; i <= count; i++)
				//{
					PanelConsole console_tmp = new PanelConsole();
					PanelAutomate automate_tmp = new PanelAutomate(console_tmp);
					console_tmp.setLocation(10, 5);
					
					
					JPanel cards_tmp = new JPanel (new CardLayout());
					cards_tmp.setLayout(null);
					
					cards_tmp.add(console_tmp);
					cards_tmp.add(automate_tmp);
					onglets.addTab("Client " + count, new ImageIcon(getClass().getResource("./IMAGES/Client.gif")) , cards_tmp, "Client");
				//}
			}
			count_tmp = count;
			count += 1;
	}
	
	public void creer_onglet_server(){
		PanelConsole console_tmp = new PanelConsole();
		PanelAutomate automate_tmp = new PanelAutomate(console_tmp);
		console_tmp.setLocation(10, 5);					
		JPanel cards_tmp = new JPanel (new CardLayout());
		cards_tmp.setLayout(null);					
		cards_tmp.add(console_tmp);
		cards_tmp.add(automate_tmp);
		if (count_serv == 1)		
			onglets.addTab("Serveur " + count_serv, new ImageIcon(getClass().getResource("./IMAGES/network.png")) , cards_tmp, "Serveur");
		if (count_serv > count_serv_tmp)
			{
					
					onglets.addTab("Serveur " + count_serv, new ImageIcon(getClass().getResource("./IMAGES/network.png")) , cards_tmp, "Serveur");
			}
		count_serv_tmp = count_serv;
		count_serv += 1;
		/*
		int cs = 1;
		int ctmp = 1;
		
		if (cs == 1)		
			onglets.addTab("Serveur " + count_serv + " - Client " + cs,
					new ImageIcon(getClass().getResource("/IMAGES/Client.gif")),
					cards_tmp,
					"Serveur");
		if (cs > ctmp)
			{
					PanelConsole console_tmp_2 = new PanelConsole();
					PanelAutomate automate_tmp_2 = new PanelAutomate(console_tmp_2);
					console_tmp_2.setLocation(10, 5);					
					JPanel cards_tmp_2 = new JPanel (new CardLayout());
					cards_tmp_2.setLayout(null);					
					cards_tmp_2.add(console_tmp_2);
					cards_tmp_2.add(automate_tmp_2);
					onglets.addTab("Serveur " + count_serv + " - Client " + cs, new ImageIcon(getClass().getResource("/IMAGES/Client.gif")) , cards_tmp_2, "Serveur");
			}
		ctmp = cs;
		cs += 1;
		*/
	}
	
	public void valider_actionPerformed(ActionEvent e)
	{
		if (clientflag == true){
			creer_onglet_client();
			clientflag = false;
		}
		if (serverflag == true) {
			creer_onglet_server();
			serverflag = false;
		}
	}
	
	public void client_actionPerformed(ActionEvent e)
	{
		if (e.getSource() == client)
		clientflag = true;
	}
	
	public void server_actionPerformed(ActionEvent e)
	{
		if (e.getSource() == server)
		serverflag = true;
	}
	
	public void ssl_actionPerformed(ActionEvent e)
	{
		if (e.getSource() == ssl)
		sslflag = true;
	}
	public void defaultOption_actionPerformed(ActionEvent e)
	{
		if (e.getSource() == defaultOption)
		defaultflag = true;
	}
	
	public void sbs_actionPerformed(ActionEvent e)
	{
		if (e.getSource() == sbs)
		sbsflag = true;
	}
	
	public static void main(String[] args) throws IOException 
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
		    
		//SplashScreenMain splash =	new SplashScreenMain();
		//splash.splash();
		GUI gui = new GUI();
		gui.CreateTCPJMenuBar();
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}*/
	
}
