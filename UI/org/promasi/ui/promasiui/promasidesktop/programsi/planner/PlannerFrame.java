package org.promasi.ui.promasiui.promasidesktop.programsi.planner;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.promasi.game.IGame;
import org.promasi.game.project.SerializableProject;
import org.promasi.ui.promasiui.promasidesktop.resources.ResourceManager;


/**
 *
 * Program for scheduling tasks. This class needs rewriting. Code hard to read
 * on the actionPerformed.
 *
 * @author eddiefullmetal
 *
 */
public class PlannerFrame extends JInternalFrame implements ActionListener, TableModelListener
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The {@link GanttEditor}.
     */
    private GanttEditor _editor;

    /**
     * The {@link JScrollPane} of the editor. We need a reference here in order
     * to update its scrollbars when the editor changes.
     */
    private JScrollPane _editorScrollPane;

    /**
     * A {@link JTable} that keeps all the created tasks.
     */
    private JTable _tasksTable;

    /**
     * Main planner toolbar.
     */
    private JToolBar _toolBar;

    /**
     * Button that performs a zoom in on the {@link GanttEditor}.
     */
    private JButton _zoomInButton;

    /**
     * Button that performs a zoom out on the {@link GanttEditor}.
     */
    private JButton _zoomOutButton;

    /**
     * Button that inserts a task.
     */
    private JButton _insertScheduleButton;

    /**
     * Button that edits the selected task.
     */
    private JButton _editScheduleButton;

    /**
     * Button that applies the changes to the project.
     */
    private JButton _applyButton;

    /**
     * Button that removes the selected task.
     */
    private JButton _removeScheduleButton;

    /**
     * Button that views the resources of the selected {@link GanttTaskSchedule}
     * .
     */
    private JButton _viewTeamButton;

    /**
     * 
     */
    private JDesktopPane _parentPane;
    
    /**
     * 
     */
    private IGame _game;

    /**
     * All the created task schedules.
     */
    private final List<GanttTaskSchedule> _taskSchedules;

    /**
     * All the pending {@link GanttTaskSchedule}s to remove.
     */
    private final List<GanttTaskSchedule> _schedulesToRemove;

    /**
     * 
     */
    private SerializableProject _currentProject;
    
    /**
     * 
     */
    private DateTime _projectStartDate;
    
    /**
     * Default logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger( PlannerFrame.class );

    /**
     * Initializes the object.
     */
    public PlannerFrame(JDesktopPane parentPane,IGame game, SerializableProject project, DateTime projectStartDate )throws NullArgumentException
    {
        super( "Planner", true, true, true, true );
        
        if(parentPane==null)
        {
        	throw new NullArgumentException("Wrong argument parentPane==null");
        }
        
        if(game==null)
        {
        	throw new NullArgumentException("Wrong argument game==null");
        }
        
        if(project==null){
        	throw new NullArgumentException("Wrong argument project==null");
        }
        
        if(projectStartDate==null){
        	throw new NullArgumentException("Wrong argument projectStartDate==null");
        }
        
        _projectStartDate=projectStartDate;
        _currentProject=project;
        _parentPane=parentPane;
        _game=game;
        _taskSchedules = new Vector<GanttTaskSchedule>( );
        _schedulesToRemove = new Vector<GanttTaskSchedule>( );
        initializeComponents( );
        
        _editor.setStartDate( _projectStartDate.toLocalDate( ) );
        _editor.setEndDate( _projectStartDate.plusMinutes(_currentProject.getProjectDuration()).toLocalDate( ) );
        initializeLayout( );
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents ( )
    {
        // Create the editor
        _editor = new GanttEditor( );
        _editor.setDrawColumnSeparators( false );
        _editor.setDrawRowSeparators( false );
        _editorScrollPane = new JScrollPane( _editor );

        // Create the toolbar buttons
        _zoomInButton = new JButton( ResourceManager.getIcon( "zoomIn" ) );
        _zoomInButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "zoomInButton", "tooltip" ) );
        _zoomInButton.addActionListener( this );
        if ( !_editor.canZoomIn( ) )
        {
            _zoomInButton.setEnabled( false );
        }
        _zoomOutButton = new JButton( ResourceManager.getIcon( "zoomOut" ) );
        _zoomOutButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "zoomOutButton", "tooltip" ) );
        _zoomOutButton.addActionListener( this );
        if ( !_editor.canZoomOut( ) )
        {
            _zoomOutButton.setEnabled( false );
        }

        _insertScheduleButton = new JButton( ResourceManager.getIcon( "insertTask" ) );
        _insertScheduleButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "insertScheduleButton", "tooltip" ) );
        _insertScheduleButton.addActionListener( this );

        _editScheduleButton = new JButton( ResourceManager.getIcon( "editTask" ) );
        _editScheduleButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "editScheduleButton", "tooltip" ) );
        _editScheduleButton.addActionListener( this );

        _removeScheduleButton = new JButton( ResourceManager.getIcon( "removeTask" ) );
        _removeScheduleButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "removeScheduleButton", "tooltip" ) );
        _removeScheduleButton.addActionListener( this );

        _viewTeamButton = new JButton( ResourceManager.getIcon( "viewTeam" ) );
        _viewTeamButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "viewTeamButton", "tooltip" ) );
        _viewTeamButton.addActionListener( this );

        _applyButton = new JButton( ResourceManager.getIcon( "apply" ) );
        _applyButton.setToolTipText( ResourceManager.getString( PlannerFrame.class, "applyButton", "tooltip" ) );
        _applyButton.addActionListener( this );

        // Create the toolbar
        _toolBar = new JToolBar( );
        _toolBar.setLayout( new FlowLayout( FlowLayout.LEADING ) );
        _toolBar.setFloatable( false );
        _toolBar.add( _insertScheduleButton );
        _toolBar.add( _editScheduleButton );
        _toolBar.add( _removeScheduleButton );
        _toolBar.add( new JSeparator( JSeparator.VERTICAL ) );
        _toolBar.add( _viewTeamButton );
        _toolBar.add( _zoomInButton );
        _toolBar.add( _zoomOutButton );
        _toolBar.add( new JSeparator( JSeparator.VERTICAL ) );
        _toolBar.add( _applyButton );
        _toolBar.add( new JSeparator( JSeparator.VERTICAL ) );

        // Create the table
        _tasksTable = new JTable( );
        GanttTaskScheduleTableModel model = new GanttTaskScheduleTableModel( _taskSchedules );
        model.addTableModelListener( this );
        _tasksTable.setModel( model );
        _tasksTable.setRowHeight( _editor.getRowHeight( ) );
    }

    /**
     * Initializes the layout.
     */
    private void initializeLayout ( )
    {
        setLayout( new MigLayout( new LC( ).fill( ) ) );
        add( _toolBar, new CC( ).dockNorth( ) );

        JSplitPane splitPane = new JSplitPane( );
        splitPane.setLeftComponent( new JScrollPane( _tasksTable ) );
        splitPane.setRightComponent( _editorScrollPane );

        add( splitPane, new CC( ).grow( ) );
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        // Do the appropriate action depending on the button clicked.
       /*if ( e.getSource( ).equals( _zoomInButton ) )
        {
            _editor.changeZoomLevel( _editor.getCurrentZoomLevel( ) - 1 );
            _editor.repaint( );
        }
        else if ( e.getSource( ).equals( _zoomOutButton ) )
        {
            _editor.changeZoomLevel( _editor.getCurrentZoomLevel( ) + 1 );
            _editor.repaint( );
        }
        else if ( e.getSource( ).equals( _insertScheduleButton ) )
        {
            TaskSelector taskSelector = new TaskSelector(_mainFrame,_shell );
            taskSelector.setVisible( true );
            if ( taskSelector.isSelected( ) && taskSelector.getSelectedTask( ) != null )
            {
                GanttTaskSchedule taskSchedule = new GanttTaskSchedule( taskSelector.getSelectedTask( ), _editor.getStartDate( ), _editor
                        .getStartDate( ), 1 );
                _taskSchedules.add( taskSchedule );
                GanttTaskScheduleUi taskScheduleUi = new GanttTaskScheduleUi( taskSchedule );
                _editor.addTaskScheduleUi( taskScheduleUi );
                _editorScrollPane.repaint( );
                _editor.repaint( );
                _tasksTable.repaint( );
            }
        }
        else if ( e.getSource( ).equals( _editScheduleButton ) )
        {
            int selectedRow = _tasksTable.getSelectedRow( );
            if ( selectedRow >= 0 )
            {
                GanttTaskSchedule taskSchedule = ( (GanttTaskScheduleTableModel) _tasksTable.getModel( ) ).getTaskScheduleAt( selectedRow );
                GanttTaskScheduleEditor editor = new GanttTaskScheduleEditor( _mainFrame,_taskSchedules, taskSchedule,_shell );
                editor.setVisible( true );
                _editorScrollPane.repaint( );
                _editor.repaint( );
                _tasksTable.repaint( );
            }
        }
        else if ( e.getSource( ).equals( _removeScheduleButton ) )
        {
            int selectedRow = _tasksTable.getSelectedRow( );
            if ( selectedRow >= 0 )
            {
                GanttTaskSchedule selectedTaskSchedule = ( (GanttTaskScheduleTableModel) _tasksTable.getModel( ) ).getTaskScheduleAt( selectedRow );
                _taskSchedules.remove( selectedTaskSchedule );
                _schedulesToRemove.add( selectedTaskSchedule );
                for ( GanttTaskSchedule taskSchedule : _taskSchedules )
                {
                    taskSchedule.removePredecessor( selectedTaskSchedule );
                }
                _editor.removeTaskSchedule( selectedTaskSchedule );
                _editorScrollPane.repaint( );
                _editor.repaint( );
                _tasksTable.repaint( );
            }
        }
        else if ( e.getSource( ).equals( _applyButton ) )
        {
            applyGantt( );
        }
        else if ( e.getSource( ).equals( _viewTeamButton ) )
        {
            int selectedRow = _tasksTable.getSelectedRow( );
            if ( selectedRow >= 0 )
            {
                GanttTaskSchedule selectedTaskSchedule = ( (GanttTaskScheduleTableModel) _tasksTable.getModel( ) ).getTaskScheduleAt( selectedRow );
                ViewResourcesDialog viewResourcesDialog = new ViewResourcesDialog(_mainFrame, selectedTaskSchedule );
                viewResourcesDialog.setVisible( true );
            }
        }
        // Change component state.
        if ( !_editor.canZoomOut( ) )
        {
            _zoomOutButton.setEnabled( false );
        }
        else
        {
            _zoomOutButton.setEnabled( true );
        }
        if ( !_editor.canZoomIn( ) )
        {
            _zoomInButton.setEnabled( false );
        }
        else
        {
            _zoomInButton.setEnabled( true );
        }
        _tasksTable.setRowHeight( _editor.getRowHeight( ) );*/
    }

    @Override
    public void tableChanged ( TableModelEvent e )
    {
        _editor.repaint( );
        _tasksTable.repaint( );
    }

    /**
     * Sets the Planner program as read only.
     */
    public void readOnly ( )
    {
        ( (GanttTaskScheduleTableModel) _tasksTable.getModel( ) ).setReadOnly( true );
        _insertScheduleButton.setEnabled( false );
        _editScheduleButton.setEnabled( false );
        _removeScheduleButton.setEnabled( false );
        _applyButton.setEnabled( false );
    }

    /**
     * Loads the gantt from a {@link Project} based on the projects
     * {@link TaskSchedule}s.
     *
     * @param project
     */
    public void loadFromProject ( SerializableProject project )
    {
        /*_taskSchedules.clear( );
        _editor.clear( );
        _tasksTable.setModel( new GanttTaskScheduleTableModel( _taskSchedules ) );
        _editor.setStartDate( project.getStartDate( ).toLocalDate( ) );
        _editor.setEndDate( project.getEndDate( ).toLocalDate( ) );
        for ( Task task : project.getTasks( ) )
        {
            for ( TaskSchedule schedule : task.getTaskSchedules( ) )
            {
                GanttTaskSchedule taskSchedule = new GanttTaskSchedule( task, _editor.getStartDate( ), schedule.getStartDate( ).toLocalDate( ),
                        schedule.getEndDate( ).getDayOfYear( ) - schedule.getStartDate( ).getDayOfYear( ) + 1 );
                taskSchedule.setResources( schedule.getTeam( ).getTeamData( ) );
                _taskSchedules.add( taskSchedule );
                GanttTaskScheduleUi taskScheduleUi = new GanttTaskScheduleUi( taskSchedule );
                _editor.addTaskScheduleUi( taskScheduleUi );
                _editorScrollPane.repaint( );
                _editor.repaint( );
                _tasksTable.repaint( );
            }
        }
        initializeLayout( );*/
    }

    /**
     * Applies the current Gantt to the project.
     */
    private void applyGantt ( )
    {
        LOGGER.info( "Applying changes from gantt to project." );
        /*for ( GanttTaskSchedule taskSchedule : _schedulesToRemove )
        {
            TaskSchedule scheduleToRemove = taskSchedule.getReferencedScheduled( );
            if ( scheduleToRemove != null )
            {
                taskSchedule.getParentTask( ).removeTaskSchedule( scheduleToRemove );
            }
        }
        _schedulesToRemove.clear( );
        for ( GanttTaskSchedule taskSchedule : _taskSchedules )
        {
            TaskSchedule schedule = taskSchedule.getReferencedScheduled( );
            if ( schedule == null )
            {
                schedule = new TaskSchedule( taskSchedule.getStartDate( ).toDateTime( _shell.getCompany( ).getStartTimeAsLocalTime( ) ),
                        taskSchedule.getEndDate( ).toDateTime( _shell.getCompany( ).getEndTimeAsLocalTime( ) ) );
                taskSchedule.setReferencedScheduled( schedule );
                taskSchedule.getParentTask( ).addTaskSchedule( schedule );
            }
            else
            {
                schedule.setStartDate( taskSchedule.getStartDate( ).toDateTime( _shell.getCompany( ).getStartTimeAsLocalTime( ) ) );
                schedule.setEndDate( taskSchedule.getEndDate( ).toDateTime( _shell.getCompany( ).getEndTimeAsLocalTime( ) ) );
            }
            Team team = new Team( );
            for ( EmployeeTeamData teamData : taskSchedule.getResources( ) )
            {
                team.assignEmployee( teamData );
            }
            schedule.setTeam( team );
            LOGGER.info( "Added/Edited " + schedule + " to/from " + taskSchedule.getParentTask( ) );
        }*/
    }
}