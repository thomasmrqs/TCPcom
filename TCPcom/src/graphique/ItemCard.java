package graphique;
import java.awt.CardLayout;
import java.net.SocketException;
import java.util.logging.ConsoleHandler;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ItemCard extends JPanel //implements Runnable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientConsolePanel console = null;
	private ClientAutomatePanel automate = null;
	private final int count = 1;
	//private JTabbedPane onglets = null;
	public ItemCard() throws SocketException
	{
		//onglets = new JTabbedPane();
		//run();
		//onglets.addTab("Client " + increase(count), new ImageIcon(getClass().getResource("/IMAGES/Client.gif")) , this, "Client");
		this.console = new ClientConsolePanel();
		console.setLocation(10, 5);
		this.automate = new ClientAutomatePanel(console);
		this.setLayout(null);
		this.add(console);
		this.add(automate);
	}
	
	public int increase (int count)
	{
		return (count ++);
	}
	
	
	
}
