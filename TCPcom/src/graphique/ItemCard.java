package graphique;

import java.net.SocketException;


import connexion.Automate;
import connexion.Serveur;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemCard extends JPanel //implements Runnable
{

    /**
     *
     */
    private final boolean pasApas;
    private static final long serialVersionUID = 1L;
    private ClientConsolePanel console = null;
    private ClientAutomatePanel panel_automate = null;
    private JComboBox comboBoxServeur;
    private final DefaultComboBoxModel comboBoxServeurModel;
    private final int count = 1;
    private boolean client; // Permet de savoir su c'est un ItemCard de client ou de serveur
    private Automate automate; // Si c'est un client, il faut directement un Automate
    private Serveur serveur;
    Map<Automate, ClientConsolePanel> map_console = new HashMap<Automate, ClientConsolePanel>();//Représente la liste des consoles des clients du serveur

    public boolean isClient() {
        return client;
    }

    public ClientConsolePanel getConsole() {
        return console;
    }

    public Automate getAutomate() {
        return automate;
    }

    public void addClientToServeur(Automate a) {
        try {
            ClientConsolePanel clientConsolePanel = new ClientConsolePanel(true);
            this.map_console.put(a, clientConsolePanel);
        } catch (SocketException ex) {
            Logger.getLogger(ItemCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.comboBoxServeurModel.addElement(a);
    }

    public Serveur getServeur() {
        return serveur;
    }

    public void setServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    public Map<Automate, ClientConsolePanel> getMap_console() {
        return map_console;
    }

    public ItemCard(boolean sbs) throws SocketException//Constructeur pour un serveur
    {
        this.map_console = new HashMap<Automate, ClientConsolePanel>();
        this.client = false;
        this.automate = null;
        this.pasApas = sbs;
        this.serveur = null;
        this.console = new ClientConsolePanel(this.client);
        console.setLocation(10, 5);
        this.panel_automate = new ClientAutomatePanel(console);
        this.setLayout(null);
        /*Debut ComboBox*/
        JLabel nomComboBox = new JLabel("Server connected clients : ");
        this.comboBoxServeurModel = new DefaultComboBoxModel();
        this.comboBoxServeur = new JComboBox(this.comboBoxServeurModel);
        this.comboBoxServeur.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Automate a = (Automate) e.getItem();
                    a.setModePasAPas(pasApas);
                    ClientConsolePanel c = GUI.get().obtainCard(serveur).getMap_console().get(a);
                    c.setLocation(10, 5);
                    GUI.get().obtainCard(serveur).remove(0);
                    GUI.get().obtainCard(serveur).add(c, 0);
                    GUI.get().obtainCard(serveur).repaint();
                }
            }
        });
        nomComboBox.setBounds(500, 0, 200, 20);
        this.comboBoxServeur.setBounds(650, 0, 600, 20);
        this.add(nomComboBox);
        this.add(console);
        
        this.add(this.comboBoxServeur);
        /*Fin ComboBox*/

        this.add(panel_automate);
    }

    public ItemCard(Automate a) throws SocketException//Constructeur pour un client
    {
        this.map_console = new HashMap<Automate, ClientConsolePanel>();
        this.comboBoxServeurModel = null;
        this.client = true;
        this.automate = a;
        this.pasApas = this.automate.isModePasAPas();
        this.serveur = null;
        this.console = new ClientConsolePanel(this.client);
        console.setLocation(10, 5);
        this.panel_automate = new ClientAutomatePanel(console);
        this.setLayout(null);
        this.add(console);
        this.add(panel_automate);
    }

    public ClientAutomatePanel getPanel_automate() {
        return panel_automate;
    }

    public int increase(int count) {
        return (count++);
    }
}