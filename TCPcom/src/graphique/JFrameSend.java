/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import connexion.Commande;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author greg
 */
public class JFrameSend extends javax.swing.JFrame {

    /**
     * Creates new form JFrameSend
     */
    public JFrameSend() {
        this.fichier = null;
        initComponents();
    }
    private File fichier;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtxtNomLocal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtCompteur = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtTextAEnvoyer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtTemporisation1 = new javax.swing.JTextField();
        jCheckUrg = new javax.swing.JCheckBox();
        jCheckPush = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Send Frame");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("OU");

        jLabel2.setText("Nom local");

        jLabel3.setText("Compteur");

        jtxtCompteur.setText("0");

        jLabel5.setText("Envoyer texte");

        jLabel6.setText("Temporisation");

        jtxtTemporisation1.setText("0");

        jCheckUrg.setText("Urg");

        jCheckPush.setText("Push");

        jButton1.setText("Valider");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Choisir un fichier");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jtxtNomLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jtxtCompteur, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jCheckPush)
                        .addGap(101, 101, 101)
                        .addComponent(jCheckUrg))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jtxtTemporisation1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jtxtTextAEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jtxtNomLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTextAEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addComponent(jtxtTemporisation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(jtxtCompteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckPush)
                    .addComponent(jCheckUrg))
                .addGap(7, 7, 7)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.fichier = chooser.getSelectedFile();
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private String stockerDonnees() {
        if (this.fichier == null) {
            return this.jtxtTextAEnvoyer.getText();
        }
        try {
            InputStream fileReader = new FileInputStream(this.fichier);
            Reader utfReader = new InputStreamReader(fileReader);

            BufferedReader input = new BufferedReader(utfReader);
            String separator = System.getProperty("line.separator");
            final StringBuilder builder = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append(separator);
            }
            return builder.toString();

        } catch (Exception ex) {
            Logger.getLogger("JFrameSend :: " + JFrameSend.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (this.jtxtNomLocal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nom local non précisé");
            return;
        }
        if (this.jtxtTextAEnvoyer.getText().isEmpty() && this.fichier == null) {
            JOptionPane.showMessageDialog(this, "Il faut préciser les données à envoyer");
            return;
        }
        if (!this.jtxtTextAEnvoyer.getText().isEmpty() && this.fichier != null) {
            JOptionPane.showMessageDialog(this, "Il faut préciser soit une donnée, soit un fichier");
            return;
        }
        if (this.jtxtTemporisation1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Temporisation non précisée");
            return;
        }
        if (this.jtxtCompteur.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Compteur non précisée");
            return;
        }
        String nomLocal = this.jtxtNomLocal.getText();
        String txt = this.stockerDonnees();
        int tempo = Integer.parseInt(this.jtxtTemporisation1.getText());
        int cpt = Integer.parseInt(this.jtxtCompteur.getText());
        boolean push = this.jCheckPush.isSelected();
        boolean urg = this.jCheckUrg.isSelected();
        Commande c  = new Commande();
        c.Send(nomLocal, txt, cpt, push, urg, tempo);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckPush;
    private javax.swing.JCheckBox jCheckUrg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jtxtCompteur;
    private javax.swing.JTextField jtxtNomLocal;
    private javax.swing.JTextField jtxtTemporisation1;
    private javax.swing.JTextField jtxtTextAEnvoyer;
    // End of variables declaration//GEN-END:variables
}
