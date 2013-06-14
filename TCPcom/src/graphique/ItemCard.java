package graphique;

import connexion.Automate;
import javax.swing.JPanel;

public class ItemCard extends JPanel //implements Runnable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private PanelConsole console = null;
    private PanelAutomate automate = null;
    private final int count = 1;
    //private JTabbedPane onglets = null;

    public ItemCard() {
        this.a = null;
        //onglets = new JTabbedPane();
        //run();
        //onglets.addTab("Client " + increase(count), new ImageIcon(getClass().getResource("/IMAGES/Client.gif")) , this, "Client");
        this.console = new PanelConsole();
        console.setLocation(10, 5);
        this.automate = new PanelAutomate(console);
        this.setLayout(null);
        this.add(console);
        this.add(automate);
    }
    public Automate a;

    public ItemCard(Automate a) {
        this.a = a;
        //onglets = new JTabbedPane();
        //run();
        //onglets.addTab("Client " + increase(count), new ImageIcon(getClass().getResource("/IMAGES/Client.gif")) , this, "Client");
        this.console = new PanelConsole();
        console.setLocation(10, 5);
        this.automate = new PanelAutomate(console);
        this.setLayout(null);
        this.add(console);
        this.add(automate);
    }

    public int increase(int count) {
        return (count++);
    }
}
