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
        openframe.setSize(400, 250);
        openframe.setContentPane(panelsend);
        openframe.setVisible(true);
    }

    private class PanelSend extends JPanel implements ActionListener {

        private JButton EnvoiBtnAccept;
        private JCheckBox Actif;
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
            setPreferredSize(new Dimension(320, 270));
            setBounds(5, 5, 315, 320);
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

            // Actif Passif
            Actif = new JCheckBox("Actif");
            Actif.setFont(new Font("arial", 0, 12));
            Actif.setBounds(10, 185, 60, 20);
            add(Actif);



            EnvoiBtnAccept = new JButton("Valider le" + ((GUI.get().getSelectedPane().isClient()) ? " client " : " serveur"));
            EnvoiBtnAccept.addActionListener(this);
            add(EnvoiBtnAccept);
            EnvoiBtnAccept.setBounds(10, 205, 160, 20);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == EnvoiBtnAccept) {
                /**
                 * ************* Fonction open **********************
                 */
                int portLocal = Integer.parseInt(PortLocale.getText());

                String ipDistante = labelIpDst.getText();
                int portDistant = Integer.parseInt(boundedPortDst.getText());

                ItemCard card = GUI.get().getSelectedPane();
                if (card.isClient()) {
                    boolean succeed = card.getAutomate().open(portLocal, ipDistante, portLocal, true);
                    if (succeed) {
                        (new Thread(card.getAutomate())).start();
                        GUI.get().getSelectedPane().getConsole().insertLine("Client créé", "Green");
                    } else {
                        GUI.get().getSelectedPane().getConsole().insertLine("Informations de connexion invalides", "Red");
                    }
                } else {//Cas d'un serveur
                    Serveur s = GestionDesConnexions.get().lancerServeur("toto", portLocal);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger("OpenFrame::" + OpenFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (s.isAlive()) {
                        GUI.get().getSelectedPane().getConsole().insertLine("Serveur créé", "Green");
                    } else {
                        GUI.get().getSelectedPane().getConsole().insertLine("Informations de connexion invalides", "Red");
                    }
                }
                openframe.dispose();
            }
        }
    }
}
