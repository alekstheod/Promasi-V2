/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.SwingUtilities;
import org.joda.time.DateTime;
import org.promasi.client_swing.gui.DesktopJPanel;
import org.promasi.desktop_swing.IMainFrame;
import org.promasi.game.IGame;
import org.promasi.game.model.generated.CompanyModel;
import org.promasi.game.model.generated.GameModelModel;
import org.promasi.game.singleplayer.IClientGameListener;
import org.promasi.utilities.logger.ILogger;
import org.promasi.utilities.logger.LoggerFactory;
import org.promasi.utils_swing.GuiException;

/**
 *
 * @author alekstheod
 */
public class WaitingPlayersJPanel extends javax.swing.JPanel implements IMultiPlayerGamesServerListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MultiPlayerGamesServer _server;
    private final IGame _game;
    private final IMainFrame _mainFrame;
    private final ILogger _logger = LoggerFactory.getInstance(WaitingPlayersJPanel.class);

    /**
     * Creates new form WaitingPlayersJPanel
     *
     * @param mainFrame
     * @param server
     * @param game
     */
    public WaitingPlayersJPanel(IMainFrame mainFrame, MultiPlayerGamesServer server, IGame game) {
        initComponents();
        _server = server;
        _server.addListener(this);
        _game = game;
        _mainFrame = mainFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        _cancelButton = new javax.swing.JButton();
        _startButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        _usersList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        _messageField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        _chatBox = new javax.swing.JTextArea();

        _cancelButton.setText("Cancel");

        _startButton.setText("Start");
        _startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _startButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(_cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(_startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_cancelButton)
                    .addComponent(_startButton)))
        );

        jScrollPane3.setViewportView(_usersList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        _chatBox.setColumns(20);
        _chatBox.setRows(5);
        jScrollPane2.setViewportView(_chatBox);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(_messageField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void _startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__startButtonMouseClicked
            _game.addListener(new IClientGameListener() {
            @Override
            public void gameStarted(IGame game, GameModelModel gameModel, DateTime dateTime) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            _mainFrame.changePanel(new DesktopJPanel(_mainFrame, _game, _game.getName()));
                        } catch (GuiException ex) {
                            _logger.error(ex.toString());
                        }
                    }
                });
            }

            @Override
            public void onTick(IGame game, DateTime dateTime) {
            }

            @Override
            public void gameFinished(IGame game, Map<String, CompanyModel> players) {
            }

            @Override
            public void onExecuteStep(IGame game, CompanyModel company, DateTime dateTime) {
            }
        });

        _server.startGame(_game);
    }//GEN-LAST:event__startButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _cancelButton;
    private javax.swing.JTextArea _chatBox;
    private javax.swing.JTextField _messageField;
    private javax.swing.JButton _startButton;
    private javax.swing.JList _usersList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateGamesList(List<IGame> games) {
    }

    @Override
    public void onJoinGame(IGame game) {
    }

    @Override
    public void receiveMessage(final String clientId, final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WaitingPlayersJPanel.this._chatBox.append(clientId + " says : " + message);
            }
        });
    }

    @Override
    public void updatePlayersList(final List<String> players) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WaitingPlayersJPanel.this._usersList.setListData(new Vector(players));
            }
        });
    }
}
