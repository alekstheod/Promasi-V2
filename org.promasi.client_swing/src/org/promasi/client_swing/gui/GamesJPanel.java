/**
 *
 */
package org.promasi.client_swing.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.jdesktop.swingx.JXPanel;
import org.promasi.client_swing.playmode.multiplayer.NewGameGamesServer;
import org.promasi.desktop_swing.IMainFrame;
import org.promasi.game.AGamesServer;
import org.promasi.game.IGame;
import org.promasi.game.IGamesServerListener;
import org.promasi.utilities.logger.ILogger;
import org.promasi.utilities.logger.LoggerFactory;
import org.promasi.utils_swing.Colors;
import org.promasi.utils_swing.GuiException;
import org.promasi.utils_swing.Painters;
import org.promasi.utils_swing.components.HtmlPanel;
import org.promasi.utils_swing.components.jlist.MenuCellRenderer;

/**
 * @author alekstheod Represents the games panel, contains a list of the games
 * available on the given games server. Provides to the user the opportunity to
 * select and run the selected game.
 */
public class GamesJPanel extends JXPanel implements IGamesServerListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public static final int CONST_GAMES_LIST_WIDTH = 200;

    /**
     *
     */
    private JList<IGame> _gamesList;

    /**
     * @brief instance of the logger.
     */
    private final ILogger _logger = LoggerFactory.getInstance(GamesJPanel.class);

    /**
     *
     */
    private IMainFrame _mainFrame;

    /**
     *
     */
    private HtmlPanel _infoPane;

    /**
     *
     */
    private AGamesServer _gamesServer;

    /**
     *
     */
    private Timer _timer;

    /**
     *
     */
    private String _username;

    /**
     * @param mainFrame
     * @param gamesServer
     * @param username
     * @throws GuiException
     *
     */
    public GamesJPanel(IMainFrame mainFrame, AGamesServer gamesServer, String username) throws GuiException {
        super();
        if (mainFrame == null || gamesServer == null || username == null || username.isEmpty()) {
            throw new GuiException("Invalid argument");
        }

        _mainFrame = mainFrame;
        _gamesServer = gamesServer;
        setBackgroundPainter(Painters.Background);

        setLayout(new BorderLayout());

        JXPanel gamesPanel = new JXPanel();
        gamesPanel.setOpaque(false);
        gamesPanel.setBackground(Colors.White.alpha(0f));
        gamesPanel.setLayout(new BorderLayout());
        gamesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOpaque(false);
        splitPane.setBackground(Colors.White.alpha(0f));
        add(splitPane, BorderLayout.CENTER);
        DefaultListModel<IGame> listModel = new DefaultListModel<>();

        _gamesList = new JList<>(listModel);
        _gamesList.setBackground(Colors.White.alpha(0f));
        _gamesList.setOpaque(false);
        _gamesList.setCellRenderer(new MenuCellRenderer());

        JScrollPane gamesScroll = new JScrollPane(_gamesList);
        gamesPanel.add(gamesScroll, BorderLayout.CENTER);
        splitPane.setLeftComponent(gamesPanel);

        _gamesList.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent arg0) {
                Point p = new Point(arg0.getX(), arg0.getY());
                _gamesList.setSelectedIndex(_gamesList.locationToIndex(p));
                IGame game = (IGame) _gamesList.getSelectedValue();
                if (game != null) {
                    _infoPane.setText(game.getGameDescription());
                }
            }

            @Override
            public void mouseDragged(MouseEvent arg0) {}
        });

        _gamesList.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseClicked(MouseEvent arg0) {
                IGame game = (IGame) _gamesList.getSelectedValue();
                if (game != null) {
                    _gamesServer.joinGame(game);
                }
            }
        });

        EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);
        _gamesList.setBorder(edge);
        _infoPane = new HtmlPanel(true);
        splitPane.setRightComponent(_infoPane);
        splitPane.setDividerLocation(200);
        _username = username;
        _gamesServer.addListener(this);
        _timer = new Timer();
        _timer.schedule(new TimerTask() {

            @Override
            public void run() {
                _gamesServer.requestGamesList();
            }

        }, 500);

        if (_gamesServer.isNewGameAllowed()) {
            JButton newGameButton = new JButton("New Game");
            gamesPanel.add(newGameButton, BorderLayout.SOUTH);
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    AGamesServer newServer;
                    try {
                        newServer = new NewGameGamesServer(_gamesServer, _mainFrame);
                        GamesJPanel nextPanel = new GamesJPanel(_mainFrame, newServer, _username);
                        _mainFrame.changePanel(nextPanel);
                    } catch (GuiException | IOException ex) {
                        _logger.error(ex.toString());
                    }
                    
                }
            });
        }
    }

    @Override
    public void updateGamesList(final List<IGame> games) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                _gamesList.setListData(new Vector<>(games));
            }
        });
    }

    @Override
    public void onJoinGame(final IGame game) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    _mainFrame.changePanel(new LoadingJPanel(_mainFrame, game, _username));
                } catch (GuiException e) {
                    _logger.error(e.toString());
                }
            }
        });
    }
}
