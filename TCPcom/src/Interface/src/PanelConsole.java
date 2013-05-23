
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;


	@SuppressWarnings("serial")
	public class PanelConsole extends JPanel implements ActionListener 
	{

	    
		
		class MyTextPane extends JTextPane 
        {
            public MyTextPane() 
            {
                super();
             //   setText("Hello World");
                setOpaque(false);

                // this is needed if using Nimbus L&F - see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6687960
                setBackground(new Color(0,0,0,0));
            }

            @Override
            protected void paintComponent(Graphics g) 
            {
                // set background green - but can draw image here too
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, getWidth(), getHeight());

                // uncomment the following to draw an image
                BufferedImage image;
				try {
					image = ImageIO.read(PanelConsole.class.getResource("Automate/black-world.jpg"));
					 g.drawImage(image, 0, 0, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 


                super.paintComponent(g);
            }
        }
        
		
		
		
		
		
		
		
		
		
		
		
		StyledDocument doc;
	  //  JTextPane textPane;
		MyTextPane textPane;
	    JScrollPane scrollPane;
	    JButton Bclear, BcmdField;
	    JTextField cmdField;

	    /**
	     * Constructeur
	     */
	    public PanelConsole() 
	    {
	        // propriété du panel
	        setLayout(null);
	        setBounds(675, 305, 305, 295);
	        setBorder(new TitledBorder("CONSOLE TCP"));

	        // création de la console texte
	        
	        
	   
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	       // textPane = new JTextPane();
	        textPane =  new MyTextPane(); 
	        doc = textPane.getStyledDocument();
	        scrollPane = new JScrollPane(textPane);
	        textPane.setBounds(10, 20, 285, 250);
	        
	       // textPane.setBackground(new Color(59, 59, 59));
	        textPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	        textPane.setEditable(false);
	        scrollPane.setAutoscrolls(true);
	        scrollPane.setBounds(10, 20, (int)getToolkit().getScreenSize().getWidth()/2 - 80, (int)getToolkit().getScreenSize().getHeight()-150);
	        add(scrollPane);

	        // chargement des styles
	        LoadStyle();

	        // ajout bouton clear
	        Bclear = new JButton("CLEAR");
	        Bclear.addActionListener(this);
	        Bclear.setBounds(10, (int)getToolkit().getScreenSize().getHeight()-100, 80, 17);
	        add(Bclear);

	        // ajout champ de texte commande
	        cmdField = new JTextField();
	        cmdField.setBounds(150, (int)getToolkit().getScreenSize().getHeight()-100, 153, 18);
	        add(cmdField);

	        // ajout bouton validation commande
	        BcmdField = new JButton("OK");
	        BcmdField.addActionListener(this);
	        BcmdField.setBounds(380, (int)getToolkit().getScreenSize().getHeight()-100, 60, 17);
	        add(BcmdField);
	    }

	    /**
	     * Charge les styles pour le JTextPane
	     * Liste des styles :
	     * Normal,
	     * Normal EM,
	     * White Bold,
	     * Green Bold,
	     * Cyan Bold,
	     * Red Bold,
	     * Green,
	     * Red
	     */
	    public void LoadStyle() {
	        Style styleRacine = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	        StyleConstants.setFontSize(styleRacine, 11);
	        StyleConstants.setFontFamily(styleRacine, "Consolas");

	        Style racine = doc.addStyle("Normal", styleRacine);
	        StyleConstants.setBold(racine, false);
	        StyleConstants.setForeground(racine, Color.white);

	        Style racineem = doc.addStyle("Normal EM", styleRacine);
	        StyleConstants.setBold(racineem, false);
	        StyleConstants.setItalic(racineem, true);
	        StyleConstants.setForeground(racineem, Color.white);

	        Style GreenBold = doc.addStyle("Green Bold", styleRacine);
	        StyleConstants.setBold(GreenBold, true);
	        StyleConstants.setForeground(GreenBold, Color.green);

	        Style Green = doc.addStyle("Green", styleRacine);
	        StyleConstants.setBold(Green, false);
	        StyleConstants.setForeground(Green, Color.green);

	        Style RedBold = doc.addStyle("Red Bold", styleRacine);
	        StyleConstants.setBold(RedBold, true);
	        StyleConstants.setForeground(RedBold, Color.red);

	        Style Red = doc.addStyle("Red", styleRacine);
	        StyleConstants.setBold(Red, false);
	        StyleConstants.setForeground(Red, Color.red);

	        Style WhiteBold = doc.addStyle("White Bold", styleRacine);
	        StyleConstants.setBold(WhiteBold, true);
	        StyleConstants.setForeground(WhiteBold, Color.white);

	        Style CyanBold = doc.addStyle("Cyan Bold", styleRacine);
	        StyleConstants.setBold(CyanBold, true);
	        StyleConstants.setForeground(CyanBold, Color.cyan);
	    }

	    /**
	     * Fonction de calcul de l'heure actuelle.
	     * @return L'heure actuelle (juste le chiffre de l'heure)
	     */
	    public String getCurrentHour() {
	        GregorianCalendar d = new GregorianCalendar();
	        int heure = d.get(Calendar.HOUR);

	        if (heure < 10) {
	            return "0" + heure;
	        } else {
	            return String.valueOf(heure);
	        }
	    }

	    /**
	     * Fonction de calcul de l'heure actuelle.
	     * @return La minute actuelle (juste le chiffre de la minute)
	     */
	    public String getCurrentMin() {
	        GregorianCalendar d = new GregorianCalendar();
	        int min = d.get(Calendar.MINUTE);

	        if (min < 10) {
	            return "0" + min;
	        } else {
	            return String.valueOf(min);
	        }
	    }

	    /**
	     * Génere le prompt avec l'heure
	     */
	    private void insertPrompt() {
	        try {
	            doc.insertString(doc.getLength(), "[", textPane.getStyle("Cyan Bold"));
	            doc.insertString(doc.getLength(), getCurrentHour(), textPane.getStyle("Normal"));
	            doc.insertString(doc.getLength(), ":", textPane.getStyle("Red"));
	            doc.insertString(doc.getLength(), getCurrentMin(), textPane.getStyle("Normal"));
	            doc.insertString(doc.getLength(), "]", textPane.getStyle("Cyan Bold"));
	            doc.insertString(doc.getLength(), " ", textPane.getStyle("Normal"));
	        } catch (BadLocationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Insert une ligne dans la console
	     * @param str La chaîne de caractère à écrire
	     * @param style Le style à utiliser
	     */
	    public void insertLine(String str, String style) {
	        try {
	            textPane.scrollRectToVisible(textPane.modelToView(textPane.getDocument().getLength()));
	            insertPrompt();
	            doc.insertString(doc.getLength(), str + "\n", textPane.getStyle(style));
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Vide tout le texte de la console
	     */
	    public void clearConsole() {
	        try {
	            doc.remove(0, doc.getLength());
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	    }

	    /**
	     * Getter du style document
	     * @return Le style document
	     */
	    public StyledDocument getDoc() {
	        return doc;
	    }

	    /**
	     * Getter du JTextPane
	     * @return Le JTextPane
	     */
	    public JTextPane getPane() {
	        return textPane;
	    }

	    /**
	     * Liste des actions du panel
	     */
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == Bclear) {
	            clearConsole();
	        }
	        if (e.getSource() == BcmdField) {
	            if (!(cmdField.getText().equals(""))) {
	                insertLine(cmdField.getText(), "White Bold");
	                cmdField.setText("");
	            }
	        }
	    }

}
