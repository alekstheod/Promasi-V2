/**
 * 
 */
package org.promasi.client_swing.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import org.jdesktop.swingx.JXPanel;
import org.promasi.game.AGamesServer;
import org.promasi.game.IGame;
import org.promasi.game.IGamesServerListener;
import org.promasi.utils_swing.Colors;
import org.promasi.utils_swing.GuiException;
import org.promasi.utils_swing.PainterFactory;
import org.promasi.utils_swing.components.RoundedJPanel;
import org.promasi.utils_swing.components.jeditorpane.ExtendedJEditorPane;
import org.promasi.utils_swing.components.jlist.MenuCellRenderer;

/**
 * @author alekstheod
 *
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
	 * 
	 */
	private IMainFrame _mainFrame;
	
	/**
	 * 
	 */
	private ExtendedJEditorPane _infoPane;
	
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
	 * @throws GuiException 
	 * 
	 */
	public GamesJPanel( IMainFrame mainFrame , AGamesServer gamesServer, String username ) throws GuiException{
		super();
		
		if( mainFrame == null ){
			throw new GuiException("Wrong argument mainFrame == null ");
		}
		
		if( gamesServer == null ){
			throw new GuiException("Wrong argument gamesServer == null ");
		}
		
		if( username == null || username.isEmpty() ){
			throw new GuiException("Wrong argument username");
		}
		
		_mainFrame = mainFrame;
		_gamesServer = gamesServer;
		setBackgroundPainter(PainterFactory.getInstance(PainterFactory.ENUM_PAINTER.Background));
		
		setLayout( new BorderLayout() );
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOpaque(false);
		splitPane.setBackground(Colors.White.alpha(0f));
		add(splitPane, BorderLayout.CENTER);
		DefaultListModel<IGame> listModel = new DefaultListModel<IGame>();
		
		RoundedJPanel gamesPanel = new RoundedJPanel( );
		gamesPanel.setLayout(new BorderLayout());
		_gamesList = new JList<IGame>(listModel);
		_gamesList.setBackground(new Color(200, 200, 200, 0));
		gamesPanel.add(_gamesList, BorderLayout.CENTER);
		_gamesList.setCellRenderer(new MenuCellRenderer());
		splitPane.setLeftComponent(gamesPanel);
		
		_gamesList.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Point p = new Point(arg0.getX(),arg0.getY());
				_gamesList.setSelectedIndex(_gamesList.locationToIndex(p));
				IGame game = (IGame)_gamesList.getSelectedValue();
				if( game != null ){
					_infoPane.setText(game.getGameDescription());
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
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
				IGame game = (IGame)_gamesList.getSelectedValue();
				if( game != null ){
					_gamesServer.joinGame(game);
				}
			}
		});
		
		EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);
		_gamesList.setBorder(edge);

		_infoPane = new ExtendedJEditorPane();
		_infoPane.setEditable(false);
		_infoPane.setContentType("text/html" );
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
	}

	@Override
	public void updateGamesList(final List<IGame> games) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_gamesList.setListData( new Vector< IGame >( games) );
			}
		});	
	}

	@Override
	public void onJoinGame(final IGame game) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					_mainFrame.changePanel(new LoadingJPanel(_mainFrame,game, _username));
				} catch (GuiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
