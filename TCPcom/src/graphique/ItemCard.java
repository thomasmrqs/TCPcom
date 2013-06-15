package graphique;

import java.awt.CardLayout;
import java.net.SocketException;
import java.util.logging.ConsoleHandler;


import connexion.Automate;
import java.awt.Color;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ItemCard extends JPanel //implements Runnable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ClientConsolePanel console = null;
    private ClientAutomatePanel panel_automate = null;
    private JComboBox comboBoxServeur;
    private final DefaultComboBoxModel comboBoxServeurModel;
    private final int count = 1;
    private boolean client; // Permet de savoir su c'est un ItemCard de client ou de serveur
    private Automate automate; // Si c'est un client, il faut directement un Automate

    public boolean isClient() {
        return client;
    }

    public ClientConsolePanel getConsole() {
        return console;
    }

    public Automate getAutomate() {
        return automate;
    }

    public ItemCard() throws SocketException//Constructeur pour un serveur
    {
        this.client = false;
        this.automate = null;
        this.console = new ClientConsolePanel();
        console.setLocation(10, 5);
        this.panel_automate = new ClientAutomatePanel(console);
        this.setLayout(null);
        /*Debut ComboBox*/
        this.comboBoxServeurModel = new DefaultComboBoxModel();
        this.comboBoxServeur = new JComboBox(this.comboBoxServeurModel);
        this.comboBoxServeur.setBounds(630, 0, 600, 20);
        this.add(comboBoxServeur);
        /*Fin ComboBox*/
        this.add(console);
        this.add(panel_automate);
    }

    public ItemCard(Automate a) throws SocketException//Constructeur pour un client
    {
        this.comboBoxServeurModel = null;
        this.client = true;
        this.automate = a;
        this.console = new ClientConsolePanel();
        console.setLocation(10, 5);
        this.panel_automate = new ClientAutomatePanel(console);
        this.setLayout(null);
        this.add(console);
        this.add(panel_automate);
    }

    public int increase(int count) {
        return (count++);
    }
}