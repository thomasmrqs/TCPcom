//package graphique;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Fond extends JPanel
{
	private ImageIcon icone;
   private  JLabel image = null;
	public Fond ()
	{
		setLayout(null);
        setBounds(980, 0,(int)getToolkit().getScreenSize().getWidth()/2 - 980,(int)getToolkit().getScreenSize().getHeight() - 100);
        icone=  new ImageIcon("IMAGES/fond-ecran-paysage.jpg");
        image = new JLabel(icone);
        image.setSize((int)getToolkit().getScreenSize().getWidth()/2 - 980,(int)getToolkit().getScreenSize().getHeight() - 100);
        this.add(image);
        this.repaint();
	}
}
