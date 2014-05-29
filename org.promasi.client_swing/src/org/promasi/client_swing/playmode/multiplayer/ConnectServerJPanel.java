/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.promasi.client_swing.playmode.multiplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.JXPanel;
import org.promasi.client_swing.gui.GamesJPanel;
import org.promasi.desktop_swing.IMainFrame;
import org.promasi.network.tcp.NetworkException;
import org.promasi.network.tcp.TcpClient;
import org.promasi.protocol.client.ProMaSiClient;
import org.promasi.protocol.compression.ZipCompression;
import org.promasi.utils_swing.GuiException;
import org.promasi.utils_swing.Painters;

/**
 *
 * @author alekstheod
 */
public class ConnectServerJPanel extends JXPanel {

    private final IMainFrame _mainFrame;
    
    /**
     * Creates new form ConnectServerJPanel
     * @param mainFrame
     */
    public ConnectServerJPanel(IMainFrame mainFrame){
        initComponents();
        _mainFrame = mainFrame;
        setBackgroundPainter(Painters.Background);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _ipField = new javax.swing.JTextField();
        _portField = new javax.swing.JFormattedTextField();
        _connectButton = new javax.swing.JButton();
        _idField = new javax.swing.JTextField();
        _nickLabel = new javax.swing.JLabel();
        _hostLabel = new javax.swing.JLabel();
        _logoPanel = new javax.swing.JPanel();

        _ipField.setText("localhost");
        _ipField.setName("_ipField"); // NOI18N

        _portField.setText("55555");
        _portField.setName("_portField"); // NOI18N

        _connectButton.setText("Connect");
        _connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _connectButtonMouseClicked(evt);
            }
        });

        _idField.setText("Company");
        _idField.setName("CompanyName"); // NOI18N

        _nickLabel.setText("Nick");

        _hostLabel.setText("Host");

        javax.swing.GroupLayout _logoPanelLayout = new javax.swing.GroupLayout(_logoPanel);
        _logoPanel.setLayout(_logoPanelLayout);
        _logoPanelLayout.setHorizontalGroup(
            _logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        _logoPanelLayout.setVerticalGroup(
            _logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(_connectButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(_nickLabel)
                                    .addComponent(_hostLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(_ipField, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(_portField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(_idField))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_nickLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_hostLabel)
                    .addComponent(_portField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(_connectButton)
                .addContainerGap())
        );

        _portField.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void _connectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__connectButtonMouseClicked
        try {
            ProMaSiClient client = new ProMaSiClient(new TcpClient(_ipField.getText(), Integer.parseInt(_portField.getText() )), new ZipCompression() );
            MultiPlayerGamesServer server = new MultiPlayerGamesServer(_idField.getText(), client);
            GamesJPanel newPanel = new GamesJPanel(_mainFrame, server, _idField.getText());
            _mainFrame.changePanel(newPanel);
        } catch (NetworkException | GuiException ex) {
            Logger.getLogger(ConnectServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event__connectButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _connectButton;
    private javax.swing.JLabel _hostLabel;
    private javax.swing.JTextField _idField;
    private javax.swing.JTextField _ipField;
    private javax.swing.JPanel _logoPanel;
    private javax.swing.JLabel _nickLabel;
    private javax.swing.JFormattedTextField _portField;
    // End of variables declaration//GEN-END:variables
}
