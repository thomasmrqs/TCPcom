package graphique;

import connexion.GestionDesConnexions;
import connexion.Serveur;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class OpenFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JFrame openframe = null;
    private PanelSend panelsend = null;

    /**
     * Constructeur
     */
    public OpenFrame() {
        openframe = new JFrame();
        panelsend = new PanelSend();
        openframe.setTitle("Open");
        openframe.setLocationRelativeTo(null);
        openframe.setSize(500, 400);
        openframe.setContentPane(panelsend);
        openframe.setVisible(true);
    }

    private class PanelSend extends JPanel implements ActionListener {

        private JButton EnvoiBtnAccept;
        private JLabel LabPortLocale, LabTempo;
        private JLabel labelIpDst;
        private JLabel labelPortDst;
        private BoundedTextField boundedIpDst;
        private BoundedTextField boundedPortDst;
        private BoundedTextField PortLocale, Tempo;

        /**
         * Constructeur
         */
        public PanelSend() {
            setLayout(null);
            setPreferredSize(new Dimension(350, 350));
            setBounds(5, 5, 350, 350);
            setBorder(new TitledBorder("Open"));

            // Port Locale
            LabPortLocale = new JLabel("Numero de port locale");
            LabPortLocale.setFont(new Font("arial", 0, 12));
            PortLocale = new BoundedTextField("", 32);
            LabPortLocale.setBounds(10, 15, 180, 20);
            PortLocale.setBounds(10, 35, 285, 20);
            PortLocale.setText("");
            add(LabPortLocale);
            add(PortLocale);

            // Numero ip distante
            labelIpDst = new JLabel("Adresse Ip distante");
            labelIpDst.setFont(new Font("arial", 0, 12));
            boundedIpDst = new BoundedTextField("", 32);

            labelIpDst.setBounds(10, 55, 180, 20);
            boundedIpDst.setBounds(10, 75, 285, 20);
            add(labelIpDst);
            add(boundedIpDst);

            // Numero port distant
            labelPortDst = new JLabel("Port applicatif distant");
            labelPortDst.setFont(new Font("arial", 0, 12));
            boundedPortDst = new BoundedTextField("", 32);
            labelPortDst.setBounds(10, 95, 180, 20);
            boundedPortDst.setBounds(10, 115, 285, 20);
            add(labelPortDst);
            add(boundedPortDst);



            // Temporisation
            LabTempo = new JLabel("Temporisation (min)");
            LabTempo.setFont(new Font("arial", 0, 12));
            Tempo = new BoundedTextField("", 4);
            Tempo.setText("5");
            LabTempo.setBounds(10, 135, 125, 20);
            Tempo.setBounds(10, 155, 285, 20);
            add(LabTempo);
            add(Tempo);




            EnvoiBtnAccept = new JButton("Valider le" + ((GUI.get().getSelectedPane().isClient()) ? " client " : " serveur"));
            EnvoiBtnAccept.addActionListener(this);
            add(EnvoiBtnAccept);
            EnvoiBtnAccept.setBounds(10, 205, 160, 20);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == EnvoiBtnAccept) {
                int portLocal = Integer.parseInt(PortLocale.getText());
                ItemCard card = GUI.get().getSelectedPane();
                String ipDistante = boundedIpDst.getText();
                int portDistant = 0;
                if (boundedPortDst.getText() != null && !boundedPortDst.getText().isEmpty()) {
                    portDistant = Integer.parseInt(boundedPortDst.getText());
                }
                
                if (card.isClient()) {

                    card.getAutomate().open(portLocal, ipDistante, portDistant, true);
                    boolean succeed = card.getAutomate().getOpenOk();
                    if (succeed) {
                        (new Thread(card.getAutomate())).start();
                        
                        card.getConsole().insertLine("Client créé", "Green");
                    } else {
                        card.getConsole().insertLine("Informations de connexion invalides", "Red");
                    }
                } else {//Cas d'un serveur                    
                   
                    Serveur s = GestionDesConnexions.get().lancerServeur(portLocal);
                    s.setIp_saisie(ipDistante);
                    s.setPort_saisie(portDistant);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger("OpenFrame::" + OpenFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (s.isAlive()) {
                        card.getConsole().insertLine("Serveur créé", "Green");
                        card.setServeur(s);
                    } else {
                        card.getConsole().insertLine("Informations de connexion invalides", "Red");
                    }
                }
                openframe.removeAll();
                openframe.dispose();
            }
        }
    }
}
