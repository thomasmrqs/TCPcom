package graphique;

import java.awt.CardLayout;
import java.net.SocketException;
import java.util.logging.ConsoleHandler;


import connexion.Automate;
import javax.swing.JPanel;

public class ItemCard extends JPanel //implements Runnable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ClientConsolePanel console = null;
    private ClientAutomatePanel panel_automate = null;
    private final int count = 1;
    private boolean client;
    private Automate automate;

    public boolean isClient() {
        return client;
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
        this.add(console);
        this.add(panel_automate);
    }

    public ItemCard(Automate a) throws SocketException//Constructeur pour un client
    {
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