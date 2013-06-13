package graphique;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class PanelAutomate extends JPanel implements ActionListener, ItemListener {

    ImageIcon panel_background = new ImageIcon(getClass().getResource("Automate/WALL.jpg"));
    Map<String, Map<String, ImageIcon>> map_textures = new HashMap<String, Map<String, ImageIcon>>();
    Map<String, JLabel> map_jlabels = new HashMap<String, JLabel>();
    JLabel labclosed1, lablisten, labsynrcvd, labsynsent;
    JComboBox listStates = null;
    JButton Bnextstep;
    JCheckBox Boxstepbystep, Boxautoack;
  private Automaton automaton =  new Automaton();    // Automaton.getInstance();
    String[] automate_states = new String[]{"", "CLOSED_INIT", "LISTEN", "SYN_RCVD", "SYN_SENT", "ESTAB",
        "FIN_WAIT_1", "CLOSE_WAIT", "FIN_WAIT_2", "CLOSING",
        "LAST_ACK", "TIME_WAIT", "CLOSED"};

    
    private String state = null;
    private Boolean error = false;
    private PanelConsole console = null;
    //private AutomateConditions conditions = null;
    private Boolean stepon = true;
    
    /**
     * Constructeur du panneau automate
     */
    
    
    public PanelAutomate(PanelConsole console) 
    {
        this.console = console;
    	setLayout(null);
        state = new String();
      //  conditions = new AutomateConditions();
        setBounds(620, 20, 2000,800//(int)getToolkit().getScreenSize().getWidth() + 200,  (int)getToolkit().getScreenSize().getHeight()-150);
);
        // chargement de l'automate
        loadMapTextures();
        loadMapJLabels();
        draw_automate();

        // check box ack-auto
       /* Boxautoack = new Checkbox("ACK Automatique", true);
        Boxautoack.setBounds(400, 620, 140, 30);
        Boxautoack.setBackground(new Color(59, 59, 59));
        Boxautoack.setForeground(Color.white);
        Boxautoack.addItemListener(this);
       add(Boxautoack); */
        Boxautoack = new JCheckBox("ACK Automatique");
		Boxautoack.setMnemonic(KeyEvent.VK_A);
		Boxautoack.setSelected(false);
        Boxautoack.setBounds(50, 520, 160, 30);
        Boxautoack.setBackground(new Color(59, 59, 59));
        Boxautoack.setForeground(Color.white);
        Boxautoack.addItemListener(this);
        add(Boxautoack);
       /*class Bouton extends JButton  implements ActionListener, ChangeListener {
    	      public Bouton() 
    	      {
    	         super("<html><i>ACK<br>Automatique</i></html>", new ImageIcon("ACK.png"));
    	         setBounds(480, 620, 90, 20);
    	         addActionListener(this);
    	         addChangeListener((ChangeListener) this);
    	         setEnabled(false);
    	         setFocusPainted(false);
    	         setRolloverIcon(new ImageIcon("ACK2.png"));
    	      }    
    	      
    	      
    	      public void stateChanged(ChangeEvent e) {
    	         setForeground(getModel().isRollover() ? Color.RED : Color.BLACK);
    	      }


			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
    	   }
       add(new Bouton()); */
        // check box step-by-step
        Boxstepbystep = new JCheckBox("Mode \"Step-by-Step\"");
        Boxstepbystep.setMnemonic(KeyEvent.VK_S);
		Boxstepbystep.setSelected(false);
        Boxstepbystep.setBounds(50, 550, 180, 30);
        Boxstepbystep.setBackground(new Color(59, 59, 59));
        Boxstepbystep.setForeground(Color.white);
        Boxstepbystep.addItemListener(this);
        add(Boxstepbystep);

        // combo box des états
		/*listStates = new JComboBox(automate_states);
        listStates.setBounds(8, 530, 100, 20);
        listStates.setFont(new Font("Arial", 0, 11));
        add(listStates);*/

        // bouton de switching
        /*Bgotostate = new JButton("Switch");
        Bgotostate.addActionListener(this);
        add(Bgotostate);
        Bgotostate.setBounds(110, 530, 80, 20);*/

        // bouton de next step
        Bnextstep = new JButton("Suivant");
        Bnextstep.addActionListener(this);
        Bnextstep.setEnabled(false);
        add(Bnextstep);
        Bnextstep.setBounds(480, 570, 90, 20); 
    }

    
    public Map<String, ImageIcon> loadTexture(String text1, String text2) {
        Map<String, ImageIcon> map = new HashMap<String, ImageIcon>();
        ImageIcon text_off = new ImageIcon(getClass().getResource(text1));
        ImageIcon text_on = new ImageIcon(getClass().getResource(text2));

        map.put("OFF", text_off);
        map.put("ON", text_on);
        return map;
    }

   
    public void loadMapTextures() 
    {
        map_textures.put("CLOSED_INIT", loadTexture("Automate/closed.png", "Automate/closed-on.png"));
        map_textures.put("LISTEN", loadTexture("Automate/listen.png", "Automate/listen-on.png"));
        map_textures.put("SYN_RCVD", loadTexture("Automate/synrcvd.png", "Automate/synrcvd-on.png"));
        map_textures.put("SYN_SENT", loadTexture("Automate/synsent.png", "Automate/synsent-on.png"));
        map_textures.put("ESTAB", loadTexture("Automate/estab.png", "Automate/estab-on.png"));
        map_textures.put("FIN_WAIT_1", loadTexture("Automate/finwait1.png", "Automate/finwait-1-on.png"));
        map_textures.put("CLOSE_WAIT", loadTexture("Automate/closewait.png", "Automate/closewait-on.png"));
        map_textures.put("FIN_WAIT_2", loadTexture("Automate/finwait2.png", "Automate/finwait-2-on.png"));
        map_textures.put("CLOSING", loadTexture("Automate/closing.png", "Automate/closing-on.png"));
        map_textures.put("LAST_ACK", loadTexture("Automate/lastack.png", "Automate/lastack-on.png"));
        map_textures.put("TIME_WAIT", loadTexture("Automate/timewait.png", "Automate/timewait-on.png"));
        map_textures.put("CLOSED", loadTexture("Automate/closed.png", "Automate/closed-on.png"));
    }

    /**
     * Charge la map (table de hash) des JLabels
     * Structure : "STATE_NAME" => JLabel
     */
    public void loadMapJLabels() {
        JLabel closed_initLabel = new JLabel((map_textures.get("CLOSED_INIT")).get("OFF"));
        JLabel listenLabel = new JLabel((map_textures.get("LISTEN")).get("OFF"));
        JLabel syn_rcvdLabel = new JLabel((map_textures.get("SYN_RCVD")).get("OFF"));
        JLabel syn_sentLabel = new JLabel((map_textures.get("SYN_SENT")).get("OFF"));
        JLabel estabLabel = new JLabel((map_textures.get("ESTAB")).get("OFF"));
        JLabel fin_wait_1Label = new JLabel((map_textures.get("FIN_WAIT_1")).get("OFF"));
        JLabel close_waitLabel = new JLabel((map_textures.get("CLOSE_WAIT")).get("OFF"));
        JLabel fin_wait_2Label = new JLabel((map_textures.get("FIN_WAIT_2")).get("OFF"));
        JLabel closingLabel = new JLabel((map_textures.get("CLOSING")).get("OFF"));
        JLabel last_ackLabel = new JLabel((map_textures.get("LAST_ACK")).get("OFF"));
        JLabel time_waitLabel = new JLabel((map_textures.get("TIME_WAIT")).get("OFF"));
        JLabel closedLabel = new JLabel((map_textures.get("CLOSED")).get("OFF"));

        map_jlabels.put("CLOSED_INIT", closed_initLabel);
        map_jlabels.put("LISTEN", listenLabel);
        map_jlabels.put("SYN_RCVD", syn_rcvdLabel);
        map_jlabels.put("SYN_SENT", syn_sentLabel);
        map_jlabels.put("ESTAB", estabLabel);
        map_jlabels.put("FIN_WAIT_1", fin_wait_1Label);
        map_jlabels.put("CLOSE_WAIT", close_waitLabel);
        map_jlabels.put("FIN_WAIT_2", fin_wait_2Label);
        map_jlabels.put("CLOSING", closingLabel);
        map_jlabels.put("LAST_ACK", last_ackLabel);
        map_jlabels.put("TIME_WAIT", time_waitLabel);
        map_jlabels.put("CLOSED", closedLabel);
    }

    /**
     * Charge les JLabels (images) et les positiones dans le JPanel
     */
    public void draw_Line(Graphics g, int n1x, int n2x, int n1y, int n2y, int image1W, int image1H, int image2W, int image2H, String extremite, String position)
    {
    	int X1;
		int Y1;
		int X2;
		int Y2;
    	
    	if (position == "UP" && extremite == "closed-listen")
    	{    	
    		
    		X1 =  n1x + image1W/2 + 20 ;
    		Y1 = n1y + image1H;
    		X2 = X1;
    		Y2 = Y1 + 10;
    	
    		g.setColor(Color.RED);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.GREEN);
    		g.drawLine(X2, Y2, X2, n2y);
    		X1 = Y1 = X2 = Y2 = 0; 
    	//paintComponent(g);
    	}
    	
    	if (position == "DOWN" && extremite == "closed-listen")
    	{
    		X1 =  n1x + image1W/2 - 20 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "horizontale" && extremite == "closed-synsent")
    	{
    		X1 = n1x + image1W/2;
    		Y1 = n1y + image1H/2 - 10;
    		X2 = n2x + image2W/2;
    		Y2 = n1y + image1H/2 - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "verticale" && extremite == "closed-synsent")
    	{
    		X1 = n2x + image2W/2;
    		Y1 = n1y + image1H/2;
    		X2 = X1;
    		Y2 = n2y -10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1 -10, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X1, Y2, X2, n2y);
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "DOWN" && extremite == "lastack-closed")
    	{
    		X1 =  n1x + image1W/2 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "horizontale" && extremite == "closed-synsent2")
    	{
    		X1 = n1x + image1W - 56;
    		Y1 = n1y + image1H/2 + 10;
    		X2 = X1 + 10;
    		Y2 = Y1;
    		g.setColor(Color.RED);
    		g.drawLine(X1, Y1, X2, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "verticale" && extremite == "closed-synsent2")
    	{
    		X1 = n1x + image1W + 10 ;
    		Y1 = n1y + image1H/2 + 10;
    		X2 = n2x + image2W/2 - 20;
    		Y2 = Y1;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1 - 55, Y1, X2, Y2);
    		g.drawLine(X2, Y2, X2, n2y);
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	
    	
    	if (position == "horizontale" && extremite == "synsent-synrcvd")
    	{
    		X1 = n1x;
    		Y1 = n1y + image1H/2;
    		X2 = n2x + image2W;
    		Y2 = n2y + image2H/2;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2 - 10, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2 - 10, Y2, n2x + 30, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "verticale" && extremite == "listen-synrcvd")
    	{
    		X1 = n1x + image1W/2 - 20 ;
    		Y1 = n1y + image1H;
    		X2 = X1;
    		Y2 = n2y + 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, n2x + image2W - 10, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(n2x + image2W - 8, Y2, n2x + 30, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    
    	if (position == "DOWN" && extremite == "synrcvd-finwait1")
    	{
    		X1 =  n1x + image1W/2 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "DOWN" && extremite == "finwait1-finwait2")
    	{
    		X1 =  n1x + image1W/2 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	if (position == "DOWN" && extremite == "closing-timewait")
    	{
    		X1 =  n1x + image1W/2 - 15 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 25;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	
    	if (position == "DOWN" && extremite == "closewait-lastack")
    	{
    		X1 =  n1x + image1W/2 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	if (position == "DOWN" && extremite == "lastack-closed")
    	{
    		X1 =  n1x + image1W/2 ;
    		Y1 = n1y;
    		X2 = X1;
    		Y2 = n2y - 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2, n2y);
    		
    		//X1 = Y1 = X2 = Y2 = 0; 
    	}
    	
    	
    	if (position == "verticale" && extremite == "estab-finwait1")
    	{
    		X1 = n1x + image1W/2 - 20 ;
    		Y1 = n1y + image1H;
    		X2 = X1;
    		Y2 = n2y + 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, n2x + image2W - 10, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(n2x + image2W - 8, Y2, n2x + 40, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    	
    	if (position == "verticale" && extremite == "estab-closewait")
    	{
    		X1 = n1x + image1W/2 + 20 ;
    		Y1 = n1y + image1H;
    		X2 = X1;
    		Y2 = n2y + 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, n2x + 5, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(n2x + 5, Y2, n2x+30, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    	
    	if (position == "verticale" && extremite == "listen-synsent")
    	{
    		X1 = n1x + image1W/2 + 20 ;
    		Y1 = n1y + image1H;
    		X2 = X1;
    		Y2 = n2y + 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, n2x + 5, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(n2x + 5, Y2, n2x+30, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    	
    	
    	if (position == "verticale" && extremite == "synrcvd-estab")
    	{
    		X1 = n1x + 30;
    		Y1 = n1y + image1H/2 + 10;
    		X2 = n2x + image2W/2 - 20;
    		Y2 = Y1;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, X2, Y2 + 5);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2 + 5, X2, n2y);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    	
    	if (position == "verticale" && extremite == "synsent-estab")
    	{
    		X1 = n1x + 30;
    		Y1 = n1y + image1H/2 + 10;
    		X2 = n2x + 125;//- image2W/2 - 10;
    		Y2 = Y1;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, X2, Y2 + 5);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2 + 5, X2, n2y);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    	
    	if (position == "verticale" && extremite == "finwait1-closing")
    	{
    		X1 = n1x + 50;
    		Y1 = n1y + image1H/2 + 10;
    		X2 = n2x + image2W/2 - 20;
    		Y2 = Y1;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, X2, Y2 + 40);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2 + 40, n2x + 34, n2y);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    	
    	if (position == "horizontale" && extremite == "timewait-closed")
    	{
    		X1 = n1x + 100;
    		Y1 = n1y + image1H/2;
    		X2 = X1 + 150 ;
    		Y2 = Y1;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(X2, Y2, X2 + 23, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		//System.out.println("X1 : " + X1);
    	}
    	
    	
    	if (position == "verticale" && extremite == "finwait2-timewait")
    	{
    		X1 = n1x + image1W/2 + 20 ;
    		Y1 = n1y + image1H;
    		X2 = X1;
    		Y2 = n2y + 10;
    		g.setColor(Color.GREEN);
    		g.drawLine(X1, Y1, X2, Y2);
    		g.drawLine(X2, Y2, n2x + 5, Y2);
    		g.setColor(Color.RED);
    		g.drawLine(n2x + 5, Y2, n2x+33, Y2);
    		//X1 = Y1 = X2 = Y2 = 0; 
    		
    	}
    }
    
    
    public void draw_automate() 
    {
        // CLOSED INIT STATE
        map_jlabels.get("CLOSED_INIT").setBounds(230, 20, 213, 56);
        add(map_jlabels.get("CLOSED_INIT"));
        //draw_Line(image1, image2, g, x1, x2, y1, y2)
        // LISTEN
        map_jlabels.get("LISTEN").setBounds(230, 130, 213, 56);
        add(map_jlabels.get("LISTEN"));

        // SYN RCVD
        map_jlabels.get("SYN_RCVD").setBounds(10, 212, 160, 47);
        add(map_jlabels.get("SYN_RCVD"));

        // SYN SENT
        map_jlabels.get("SYN_SENT").setBounds(500, 212, 163, 47);
        add(map_jlabels.get("SYN_SENT"));

        // ESTAB
        map_jlabels.get("ESTAB").setBounds(230, 260, 214, 50);
        add(map_jlabels.get("ESTAB"));

        // FIN WAIT 1
        map_jlabels.get("FIN_WAIT_1").setBounds(10, 309, 168, 51);
        add(map_jlabels.get("FIN_WAIT_1"));

        // CLOSE WAIT
        map_jlabels.get("CLOSE_WAIT").setBounds(500, 309, 154, 65);
        add(map_jlabels.get("CLOSE_WAIT"));

        // FIN WAIT 2
        map_jlabels.get("FIN_WAIT_2").setBounds(40, 400, 105, 60);
        add(map_jlabels.get("FIN_WAIT_2"));

        // CLOSING WAIT
        map_jlabels.get("CLOSING").setBounds(280, 400, 109, 60);
        add(map_jlabels.get("CLOSING"));

        // LAST ACK
        map_jlabels.get("LAST_ACK").setBounds(520, 400, 108, 60);
        add(map_jlabels.get("LAST_ACK"));

        // TIME WAIT
        map_jlabels.get("TIME_WAIT").setBounds(250, 500, 169, 59);
        add(map_jlabels.get("TIME_WAIT"));

        // CLOSED
        map_jlabels.get("CLOSED").setBounds(520, 500, 108, 59);
        add(map_jlabels.get("CLOSED"));
    }

   
    public void update_states(String state_active) 
    {
        String key;
        //state_active = "LISTEN";
        for (Iterator<String> i = map_jlabels.keySet().iterator(); i.hasNext();) {
            key = i.next();
            if (key.equals(state_active)) 
            {
                map_jlabels.get(state_active).setIcon((map_textures.get(state_active)).get("ON"));
              //PanelConsole.insertLine("State switched to : " + state_active, "Normal");
                this.console.insertLine("State switched to : " + state_active, "Normal");
            } 
            else 
            {
                map_jlabels.get(key).setIcon((map_textures.get(key)).get("OFF"));

            }
        }
    }

    
    @Override
    public void paintComponent(Graphics g) {
        Image bg;
        super.paintComponent(g);
        if (panel_background.getImage() == null) {
            return;
        }
        bg = panel_background.getImage();
        //g.drawImage(bg, 0, 0, 345, 559, this);
        g.drawImage(bg, 0, 0, 650, 650, this);
       // draw_Line(g, 270, 270, 200, 100);
       draw_Line(g, 230, 230, 20, 130, 213, 56, 213, 56, "closed-listen","DOWN");
       draw_Line(g, 230, 230, 20, 130, 213, 56, 213, 56, "closed-listen", "UP");
       draw_Line(g, 230, 500, 20, 212, 213, 56, 163, 47, "closed-synsent", "horizontale");
       draw_Line(g, 230, 500, 20, 212, 213, 56, 163, 47, "closed-synsent", "verticale");
       draw_Line(g, 530, 10, 212, 212, 163, 47, 160, 47, "synsent-synrcvd", "horizontale");
       draw_Line(g, 230, 10, 130, 212, 213, 56, 160, 47, "listen-synrcvd", "verticale");
       draw_Line(g, 10, 10, 212, 309, 160, 47, 168, 51, "synrcvd-finwait1", "DOWN");
       draw_Line(g, 10, 40, 309, 400, 168, 51, 105, 60, "finwait1-finwait2", "DOWN");
       draw_Line(g, 280, 250, 400, 500, 109, 60, 169, 59, "closing-timewait", "DOWN");
       draw_Line(g, 500, 520, 309, 400, 154, 51, 108, 60, "closewait-lastack", "DOWN");
       draw_Line(g, 230, 10, 260, 309, 214, 50, 168, 51, "estab-finwait1", "verticale");
       draw_Line(g, 230, 500, 260, 309, 214, 50, 154, 51, "estab-closewait", "verticale");
       draw_Line(g, 230, 500, 130, 212, 213, 56, 163, 47, "listen-synsent", "verticale");
       draw_Line(g, 230, 500, 20, 212, 213, 56, 163, 47, "closed-synsent2", "horizontale");
       draw_Line(g, 230, 500, 20, 212, 213, 56, 163, 47, "closed-synsent2", "verticale");
       draw_Line(g, 520, 520, 400, 500, 108, 60, 108, 59, "lastack-closed", "DOWN");
       draw_Line(g, 10, 230, 212, 260, 160, 47, 214, 50, "synrcvd-estab", "verticale");
       draw_Line(g, 500, 230, 212, 260, 163, 47, 214, 50, "synsent-estab", "verticale");
       draw_Line(g, 10, 280, 309, 400, 168, 51, 109, 60, "finwait1-closing", "verticale");
       draw_Line(g, 250, 230, 500, 20, 169, 59, 213, 56, "timewait-closed", "horizontale");
       draw_Line(g, 40, 250, 400, 500, 105, 60, 169, 59, "finwait2-timewait", "verticale");
       
       
    }
    @Override
    public void actionPerformed(ActionEvent action) 
    {
        
        if (action.getSource() == Bnextstep &&  (GUI.getSbsflag() == true ||  automaton.getboxStepByStep() == true))
        {
        	//if (automaton.getClosed1() == false)
        	//	System.out.println("FAUX");
        	
        	if (automaton.getClosed1() == true && stepon == true)
        	{
        		update_states("CLOSED_INIT");
        		System.out.println("toto");
        		stepon = false;
        		//automaton.setClosed1(false);
        		automaton.setClosed1(false);
        		//automaton.setListen(true);
        		
        	}
        	else if (automaton.getAutoAck() == true)
        	{	
        		this.console.insertLine("Cannot switched state to : CLOSED INIT", "Normal");
        		
        		error = true;
        		System.out.println("FAUX11");
        	}
        	if (automaton.getListen() == true && stepon == true)
        	{
        		update_states("LISTEN");
        		//return (automaton.getListen());
        		stepon = false;
        		automaton.setListen(false);
        	}
        	//if (error == false)
        	//	System.out.println("FAUX3");
        	else if (automaton.getAutoAck() == true)
        	{
        		 this.console.insertLine("Cannot switched state to : LISTEN", "Normal");
        		System.out.println("OK1");
        		//return (automaton.getListen());
        		error = true;
        	}
        	
        	if (automaton.getSynrcvd() == true && stepon == false)
        	{
        		update_states("SYN_RCVD");
        		stepon = true;
        		automaton.setSynrcvd(false);
        		
        		//return (automaton.getSynrcvd());
        	}
        	else if (automaton.getAutoAck() == true  && error == false)
        	{
        	   this.console.insertLine("Cannot switched state to : SYN RCVD", "Normal");
        		//return (automaton.getSynrcvd());
        	   System.out.println("FAUX12");
        	   error = true;
        	   
        	}
        	if (automaton.getSynsent() == true && stepon == false)
        	{
        		update_states("SYN_SENT");
        		stepon = true;
        		automaton.setSynsent(false);
        	}
        	else if (automaton.getAutoAck() == true  && error == false)
        	{
        		this.console.insertLine("Cannot switched state to : SYN SENT", "Normal");
        		//return (automaton.getSynsent());
        		error = true;
        	}
        	if (automaton.getEstab() == true && stepon == false)
        	{
        		update_states("ESTAB");
        		stepon = true;
        		automaton.setEstab(false);
        	}
        	else if (automaton.getAutoAck() == true && error == false)
        	{
        		 this.console.insertLine("Cannot switched state to : ESTAB next state", "Normal");
        		//return (automaton.getEstab());
        		error = true;
        	}
        	
        	if (automaton.getFinwait1() == true)
        	{
        		update_states("FIN_WAIT_1");
        		stepon = true;
        		automaton.setFinwait1(false);
        	}
        	else if (automaton.getAutoAck() == true  && error == false)
        	{
        		this.console.insertLine("Cannot switched state to : FIN WAIT-1 next state", "Normal");
        		//return (automaton.getFinwait1());
        		error = true;
        	}
        	
        	if (automaton.getFinwait2() == true && stepon == false)
        	{
        		update_states("FIN_WAIT_2");
        		stepon = true;
        		automaton.setFinwait2(false);
        	}
        	else if (automaton.getAutoAck() == true && error == false)
        	{
        		this.console.insertLine("Cannot switched state to : FIN WAIT-2 next state", "Normal");
        		//return (automaton.getFinwait2());
        		error = true;
        	}
        	
        	if (automaton.getClosing() == true && stepon == false)
        	{
        		update_states("CLOSING");
        		stepon = true;
        		automaton.setClosing(false);
        	}
        	else if (automaton.getAutoAck() == true && error == false) 
        	{
        	   this.console.insertLine("Cannot switched state to : CLOSING", "Normal");
        		//return (automaton.getClosing());
        		error = true;
        	}
        	
        	if (automaton.getLastack() == true && stepon == false)
        	{
        		update_states("LAST_ACK");
        		stepon = true;
        		automaton.setLastack(false);
        	}
        	else if (automaton.getAutoAck() == true && error == false)
        	{
        		 this.console.insertLine("Cannot switched state to : LAST ACK next state", "Normal");
        		//return (automaton.getLastack());
        		error = true;
        	}
        	
        	if (automaton.getTimewait() == true && stepon == false)
        	{
        		update_states("TIME_WAIT");
        		stepon = true;
        		automaton.setTimewait(false);
        	}
        	else if (automaton.getAutoAck() == true  && error == false)
        	{
        		 this.console.insertLine("Cannot switched state to : TIME WAIT next state", "Normal");
        		//return (automaton.getTimewait());
        		error = true;
        	}
        	
        	if (automaton.getClosed2() == true && stepon == false)
        	{
        		update_states("CLOSED");
        		stepon = true;
        		automaton.setClosed2(false);
        	}
        	else if (automaton.getAutoAck() == true  && error == false)
        	{
        		 this.console.insertLine("Cannot switched state to : CLOSED END", "Normal");
        		//return (automaton.getClosed2());
        		error = true;
        	}
        }    
    }
    
    
    
    
    @Override
    public void itemStateChanged(ItemEvent item) 
    {
        
        if (item.getSource() == Boxstepbystep) 
        {
            if (item.getStateChange() == ItemEvent.SELECTED) 
            {
                Bnextstep.setEnabled(true);
                automaton.boxSetStepByStep(true);
                 this.console.insertLine("Step-by-Step Mode : ENABLED", "Normal");
            } 
            else 
            {
                Bnextstep.setEnabled(false);
                automaton.setStepByStep(false);
                 this.console.insertLine("Step-by-Step Mode : DISABLED", "Normal");
            }
        }
        if (item.getSource() == Boxautoack) 
        {
            if (item.getStateChange() == ItemEvent.SELECTED) 
            {
                automaton.setAutoAck(true);
                 this.console.insertLine("ACK Auto Mode : ENABLED", "Normal");
            } 
            else 
            {
                automaton.setAutoAck(false);
                this.console.insertLine("ACK Auto Mode : DISABLED", "Normal");
            }
        }
    }
	
}