package graphique;

import connexion.Automate;
import connexion.Client;
import connexion.Serveur;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class GUI extends JFrame {

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
    private static Boolean sbsflag = false;
    private Boolean defaultflag = false;
    private Boolean sslflag = false;
    private JOptionPane jop1 = null;
    private ClientConsolePanel console = null;
    private ClientAutomatePanel automate = null;
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
    private int count_serv = 1;
    private int count_serv_tmp = 1;
    private String serverconnectedclients = null;
    DegradPanel degradPanel = new DegradPanel();

    public JTabbedPane getOnglets() {
        return onglets;
    }

    public ItemCard getSelectedPane() {
        return (ItemCard) this.onglets.getSelectedComponent();
    }
    //Permet de retourner l'onglet du serveur en fonction du serveur 
    public ItemCard obtainCard(Serveur s) {
        for (int i = 0; i < this.onglets.getTabCount(); i++) {
            ItemCard item = (ItemCard) this.onglets.getComponentAt(i);
            if (!item.isClient()) {
                if (item.getServeur() == s) {
                    return item;
                }
            }
        }
        return null;
    }
    //Permet de retourner l'onglet du client en fonction du client 
    public ItemCard obtainCard(Client s) {
        for (int i = 0; i < this.onglets.getTabCount(); i++) {
            ItemCard item = (ItemCard) this.onglets.getComponentAt(i);
            if (item.isClient()) {
                if (item.getAutomate().getTcb().getConnexion() == s) {
                    return item;
                }
            }
        }
        return null;
    }
    
    private static GUI inst = null;

    public static GUI get() {
        if (GUI.inst == null) {
            try {
                GUI.inst = new GUI();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return GUI.inst;
    }

    private GUI() throws IOException {

        fenetre = new JFrame();
        mb = new JMenuBar();
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
        cards = new JPanel(new CardLayout());
        //console = new ClientConsolePanel();
        automate = new ClientAutomatePanel(console);
        cards.setLayout(null);
//        console.setLocation(10, 5);
//        cards.add(console);
        cards.add(automate);
        fenetre.setTitle(" TRANSMISSION CONTROL PROTOCOL (T.C.P)");
        fenetre.setBackground(Color.BLACK);
        fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("IMAGES/connect.gif")));

        fenetre.setSize((int) getToolkit().getScreenSize().getWidth(), (int) getToolkit().getScreenSize().getHeight() - 10);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.getContentPane().add(onglets);
        fenetre.setVisible(true);
        fenetre.setJMenuBar(mb);
        cards_tmp = new JPanel();
        serverconnectedclients = new String ();
    }

    public void CreateTCPJMenuBar() throws SocketException {
        connexion.setText("Create".toUpperCase());
        deconnexion.setText("destroy".toUpperCase());
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

        valider.addActionListener(new java.awt.event.ActionListener() {
            @SuppressWarnings("static-access")
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if ((clientflag || serverflag) && (sbsflag || sslflag || defaultflag)) {

                    try {
                        valider_actionPerformed(e);
                    } catch (SocketException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    //  ConnectionFrame cof = new ConnectionFrame();
                } //new PanelSend();
                //System.out.println("connect");
                else {
                    jop1.showMessageDialog(null, "SELECTIONNER UN MODE ET UNE OPTION", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        client.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                client_actionPerformed(e);
                //
                //new PanelSend();
                //System.out.println("connect");
            }
        });

        server.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                server_actionPerformed(e);
                //new PanelSend();
                //System.out.println("connect");
            }
        });

        sbs.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                sbs_actionPerformed(e);

                //new PanelSend();
                //System.out.println("connect");
            }
        });

        ssl.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                ssl_actionPerformed(e);
                //new PanelSend();
                //System.out.println("connect");
            }
        });
        defaultOption.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                defaultOption_actionPerformed(e);
                //new PanelSend();
                //System.out.println("connect");
            }
        });

    }

    public void creer_onglet_client() throws SocketException {
        ItemCard card = new ItemCard(new Automate());
        onglets.addTab("Client " + count++, new ImageIcon(getClass().getResource("./IMAGES/Client.gif")), card, "Client" + count);
    }

    public void creer_onglet_server() throws SocketException {
        ItemCard card = new ItemCard();
        onglets.addTab("Serveur " + count_serv++, new ImageIcon(getClass().getResource("./IMAGES/network.png")), card, "Serveur" + count_serv);
                /*  ClientConsolePanel console_tmp = new ClientConsolePanel();
         ClientAutomatePanel automate_tmp = new ClientAutomatePanel(console_tmp);
         console_tmp.setLocation(10, 5);
         JPanel cards_tmp = new JPanel(new CardLayout());
         cards_tmp.setLayout(null);
         cards_tmp.add(console_tmp);
         cards_tmp.add(automate_tmp);
         if (count_serv == 1) {
         onglets.addTab("Serveur " + count_serv, new ImageIcon(getClass().getResource("./IMAGES/network.png")), cards_tmp, "Serveur");
         }
         if (count_serv > count_serv_tmp) {

         onglets.addTab("Serveur " + count_serv, new ImageIcon(getClass().getResource("./IMAGES/network.png")), cards_tmp, "Serveur");
         }
         count_serv_tmp = count_serv;
         count_serv += 1;

         cards_tmp = null;*/
    }

    public void valider_actionPerformed(ActionEvent e) throws SocketException {
        if (clientflag == true) {
            creer_onglet_client();
            clientflag = false;
        }
        if (serverflag == true) {
            creer_onglet_server();
            serverflag = false;
        }
    }

    public void client_actionPerformed(ActionEvent e) {
        if (e.getSource() == client) {
            clientflag = true;
        }
    }

    public void server_actionPerformed(ActionEvent e) {
        if (e.getSource() == server) {
            serverflag = true;
        }
    }

    public void ssl_actionPerformed(ActionEvent e) {
        if (e.getSource() == ssl) {
            sslflag = true;
        }
    }

    public void defaultOption_actionPerformed(ActionEvent e) {
        if (e.getSource() == defaultOption) {
            defaultflag = true;
        }
    }

    public void sbs_actionPerformed(ActionEvent e) {
        if (e.getSource() == sbs) {
            sbsflag = true;
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        //SplashScreenMain splash =	new SplashScreenMain();
        //splash.splash();
        //GUI gui = new GUI();
        GUI.get().CreateTCPJMenuBar();
    }

    public JButton getSbs() {
        return sbs;
    }

    public void setSbs(JButton sbs) {
        this.sbs = sbs;
    }

    public static Boolean getSbsflag() {
        return sbsflag;
    }

    public static void setSbsflag(Boolean sbsflag) {
        GUI.sbsflag = sbsflag;
    }

    /*@Override
     public void actionPerformed(ActionEvent e) {
     // TODO Auto-generated method stub
		
     }*/
}