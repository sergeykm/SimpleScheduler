package kml.testproj.scheduler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;

import java.util.*;

/**
 * Simple Scheduler application with combobox, tabs and tables
 */
public class App extends JFrame{

    private static final Logger LOG =  Logger.getLogger(App.class.getName());

    /**
     * @param args
     */
    public static void main(String[] args){

        LOG.info("Starting up...");
        LOG.setLevel(Level.ALL);

        App schedulerMain = new App();
        schedulerMain.setTitle("Application");
        schedulerMain.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        schedulerMain.setMinimumSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
        schedulerMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        schedulerMain.setVisible(true);
    }

    public App(){

        ResourceBundle bundle = ResourceBundle.getBundle("bundle");
        msgWrongData = bundle.getString("msgWrongData");

        dateFormatSymbols = new DateFormatSymbols(Locale.getDefault());
        dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        
        hibernateInAction = new HibernateInAction();
        employees = hibernateInAction.getEmployees();

        employeeCombo = new JComboBox(new DefaultComboBoxModel(getEmployeesComboItems(employees)));
        employeeCombo.setPreferredSize(new Dimension(EMPLOYEECOMBO_WIDTH,EMPLOYEECOMBO_HEIGHT));

        employeeLabelText = bundle.getString("employeeLabelText");
        employeeLabel = new JLabel(employeeLabelText);
        
        tasksPanel = new JPanel();
        tasksPanel.add(employeeLabel);
        tasksPanel.add(employeeCombo);
        add("West", tasksPanel);

        COLUMN_DONE = bundle.getString("COLUMN_DONE");
        COLUMN_DESCRIPTION = bundle.getString("COLUMN_DESCRIPTION");
        COLUMN_START = bundle.getString("COLUMN_START");
        COLUMN_END = bundle.getString("COLUMN_END");
        COLUMN_COMPLETED = bundle.getString("COLUMN_COMPLETED");

        tasksHeader.addElement(COLUMN_DONE);
        tasksHeader.addElement(COLUMN_DESCRIPTION);
        tasksHeader.addElement(COLUMN_START);
        tasksHeader.addElement(COLUMN_END);
        tasksHeader.addElement(COLUMN_COMPLETED);


        tasksData = hibernateInAction.getEmployeeTasks(getSelectedEmployeeId());

        tasksTab = new JTable(new DefaultTableModel(tasksData, tasksHeader)){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                  return false; }
            @Override
            public Class getColumnClass(int col) {
                if (col == 0) {
                   return Boolean.class;
                } else {
                   return super.getColumnClass(col);
                }
              } 
        };
        
        tasksTab.getTableHeader().setReorderingAllowed(false);
        tasksTab.setCellSelectionEnabled(true);

        mainTabbedPane = new JTabbedPane();

        tasksTab.setPreferredScrollableViewportSize(new Dimension(
                tasksTab.getPreferredScrollableViewportSize().width,
                TABLESCROLLHEIGHT * tasksTab.getRowHeight()
        ));

        JScrollPane jsp1 = new JScrollPane(tasksTab);

        tabTasks = bundle.getString("tabTasks");
        mainTabbedPane.addTab(tabTasks, jsp1);

        monthLabelText = bundle.getString("monthLabelText");
        monthLabel = new JLabel(monthLabelText);
        monthCombo = new JComboBox(dateFormatSymbols.getMonths());
        monthCombo.setPreferredSize(new Dimension(MONTHCOMBO_WIDTH,MONTHCOMBO_HEIGHT));

        planMonthPanel = new JPanel();
        planMonthPanel.add(monthLabel);
        planMonthPanel.add(monthCombo);
        
        planPanel = new JPanel();
        planPanel.setLayout(new BorderLayout());

        planData = getPlanData();

        planTab = new JTable(new DefaultTableModel(planData, planHeader)){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false; }
        };

        this.updatePlanTable();

        planTab.setPreferredScrollableViewportSize(new Dimension(planTab.getPreferredScrollableViewportSize().width,
                TABLESCROLLHEIGHT * planTab.getRowHeight()
        ));

        JScrollPane jsp = new JScrollPane(planTab);
        planPanel.add("South", new JScrollPane(jsp));
        planPanel.add("West", planMonthPanel);

        tabPlan = bundle.getString("tabPlan");
        mainTabbedPane.addTab(tabPlan, planPanel);
        add("South", mainTabbedPane);

        validate();

        employeeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    tasksData = hibernateInAction.getEmployeeTasks(getSelectedEmployeeId());
                    tasksModel = new DefaultTableModel(tasksData, tasksHeader);
                    tasksTab.setModel(tasksModel);
                    tasksTab.repaint();
                    updatePlanTable();
                }
            }
        });

        monthCombo.addItemListener(new  ItemListener() {
            public void itemStateChanged (ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setSelectedMonth(monthCombo.getSelectedIndex());
                    updatePlanTable();
                }
            }
        });
    }

    /**
     * Creation table data for table on tab "plan"
     * @return data include marker (" ") for checked cell
     */
    private Vector<Object> getPlanData(){

        planLoad = hibernateInAction.getEmployeeTasks(getSelectedEmployeeId());
        planData.clear();
            for (int i =0;i< planLoad.size();i++){
                planElements = planLoad.elementAt(i).toString().split(",");

                        try {
                            setDateDrawFirst(dateFormat.parse(planElements[2]));
                            setDateDrawSecond(dateFormat.parse(planElements[3]));
                                } catch (ParseException exc) {
                                    LOG.error("Catch Exception", exc);
                                    JOptionPane.showMessageDialog(null, exc.getMessage(),
                                            msgWrongData, JOptionPane.OK_OPTION);
                                }

                    //use resived date from db and create planHeader
                    updatePlanHeader();

                    // Add description about tasks for first column
                    planLoadNew.addElement(planElements[1]);

                    for(int j = 0; j<=getMax_date();j++){
                        // Cells receives marker by condition
                        jIteratorDate= getRequestCalendar(j);
                        if (!(jIteratorDate.before(getDateDrawFirst())) &
                                (jIteratorDate.before(getDateDrawSecond()))){
                                    planLoadNew.add(" ");
                                }else {
                                    planLoadNew.add("");
                                    }
                    } // end for

                    planData.addElement(planLoadNew.clone());
                    planLoadNew.clear();
                } // end for

    return planData;
    }

    /**
     * Creation table header for table on tab "plan"
     * @return header for request date
     */
    private Vector<String> updatePlanHeader(){

        calendarClone = (Calendar)Calendar.getInstance().clone();
        calendarClone.setTime(getDateDrawFirst());
        calendarClone.set(Calendar.MONTH, getSelectedMonth());
        calendarClone.set(Calendar.DAY_OF_MONTH, 1); //Always first day of month here
        setMax_date(calendarClone.getActualMaximum(Calendar.DAY_OF_MONTH));

        planHeader.clear();
        //Add in plan table header first column name
        planHeader.addElement(COLUMN_DESCRIPTION);
        //Add in plan table header columns by the number of days
        for(Integer i = 1; i <= max_date; i++){
            planHeader.addElement(i.toString());
        }
        return planHeader;
    }

    /**
     * Creation table for the selected month and the employee
     */
    private void updatePlanTable(){

    planData = getPlanData();

    planModel = new DefaultTableModel(planData, planHeader);
    planTab.setModel(planModel);
    
    planTab.setDragEnabled(false);
    planTab.setCellSelectionEnabled(true);
    planTab.getTableHeader().setReorderingAllowed(false);
    planTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    
    columnPlan = planTab.getColumnModel().getColumn(0);
    columnPlan.setMinWidth(COLUMNPLAN_WIDTH);
    columnPlan.setResizable(true);

    // Only first column plan table resizable
    columnNumber = planTab.getColumnModel().getColumnCount();
        for (int i=1;i<columnNumber;i++){
            planTab.getColumnModel().getColumn(i).setResizable(false);
        }

    // For cell with marker (" ") use light gray color
    DefaultTableCellRenderer rendererData = new DefaultTableCellRenderer(){
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column){
                    Component cell = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);
                        
                    if (value != null){
                        if (value.equals(" ")) {
                            cell.setBackground(Color.LIGHT_GRAY);
                            }else{
                                cell.setBackground(Color.WHITE);
                                }
                     }else{
                            cell.setBackground(Color.WHITE);
                            }
                     return cell;
                     }
            };
    planTab.setDefaultRenderer(Object.class, rendererData);
    }

    /**
     * Creation date for operations with marker
     * @param jDayIterator day of created date
     * @return return date for selected conditions
     */
    private Date getRequestCalendar(int jDayIterator){
        
        theCalendar = (Calendar)Calendar.getInstance().clone();
        theCalendar.setTime(getDateDrawFirst());
        theCalendar.set(Calendar.MONTH,getSelectedMonth());
        theCalendar.set(Calendar.DAY_OF_MONTH, jDayIterator);
        return theCalendar.getTime();
    }

    private Integer getSelectedEmployeeId(){
        Integer selected = employeeCombo.getSelectedIndex();
        selected = employees[selected].getEmployee_id();
        return selected;
    }

    public String[] getEmployeesComboItems(Employees[] employeesItems){

        return getEqualsFullNames(getEmployeesFullNames(employeesItems));
    }

    public String[] getEmployeesFullNames(Employees[] employeesItems){

        employeesFullNames = new String[employeesItems.length];
            for(int i=0;i<employeesItems.length;i++){
                employeesFullNames[i] = employeesItems[i].getSurname() + " " + employeesItems[i].getInitials();
            }

        return employeesFullNames;
    }

    /**
     * Creation items for employeesCombo
     * @param employeesFullNames
     * @return array with modified equals full names
     */
    public String[] getEqualsFullNames(String[] employeesFullNames){

        String[] requestFullNames = employeesFullNames.clone();
           for(int i=0;i<employeesFullNames.length;i++){
               int k = 1;
               for(int j=i+1;j<employeesFullNames.length;j++){
                   if(employeesFullNames[i].equalsIgnoreCase(requestFullNames[j])){
                       ++k;
                       requestFullNames[j] = employeesFullNames[j] +"("+k+")";
                   }
               }
           }

      return requestFullNames;
    }


    private int getSelectedMonth() {
        return selectedMonth;
    }
    private void setSelectedMonth(int selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    private int getMax_date() {
        return max_date;
    }
    private void setMax_date(int max_date) {
        this.max_date = max_date;
    }

    private Date getDateDrawFirst() {
        return dateDrawFirst;
    }
    private void setDateDrawFirst(Date dateDrawFirst) {
        this.dateDrawFirst = dateDrawFirst;
    }

    private Date getDateDrawSecond() {
        return dateDrawSecond;
    }
    private void setDateDrawSecond(Date dateDrawSecond) {
        this.dateDrawSecond = dateDrawSecond;
    }

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 320;
    private static final int COLUMNPLAN_WIDTH = 180;
    private static final int MONTHCOMBO_WIDTH = 200;
    private static final int MONTHCOMBO_HEIGHT = 22;
    private static final int EMPLOYEECOMBO_WIDTH = 150;
    private static final int EMPLOYEECOMBO_HEIGHT = 20;
    private static final int TABLESCROLLHEIGHT = 8;

    private String COLUMN_DONE;
    private String COLUMN_DESCRIPTION;
    private String COLUMN_START;
    private String COLUMN_END;
    private String COLUMN_COMPLETED;

    private JLabel employeeLabel;
    private JLabel monthLabel;
    private JComboBox<String[]> employeeCombo;
    private JComboBox<String[]> monthCombo;
    private JPanel tasksPanel;
    private JPanel planPanel;
    private JPanel planMonthPanel;
    private JTabbedPane mainTabbedPane;
    private JTable tasksTab;
    private JTable planTab;
    private TableColumn columnPlan;
    private DefaultTableModel planModel;
    private DefaultTableModel tasksModel;
    
    private DateFormatSymbols dateFormatSymbols;
    private Calendar calendarClone;
    private Calendar theCalendar;
    private Date jIteratorDate;
    
    private int max_date;
    private int columnNumber;
    private int selectedMonth = 0;
    private String[] planElements;
    private Date dateDrawFirst = new Date();
    private Date dateDrawSecond = new Date();
    private DateFormat dateFormat;
    
    private HibernateInAction hibernateInAction;
    private Employees[] employees;
    private String[] employeesFullNames;

    private Vector<Object> tasksData = new Vector<Object>();
    private Vector<String> tasksHeader = new Vector<String>();
    private Vector<Object> planData = new Vector<Object>();
    private Vector<Object> planLoad = new Vector<Object>();
    private Vector<Object> planLoadNew = new Vector<Object>();
    private Vector<String> planHeader = new Vector<String>();

    public String msgWrongData;
    private String employeeLabelText;
    private String tabTasks;
    private String monthLabelText;
    private String tabPlan;
}
