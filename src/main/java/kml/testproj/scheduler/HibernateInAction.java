package kml.testproj.scheduler;

import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateInAction {

    private static Logger LOG =  Logger.getLogger(HibernateInAction.class.getName());

    public HibernateInAction(){

        LOG.setLevel(Level.ALL);
        employee = new Employees();
        task = new Tasks();
        dataFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        employeeTask = new Vector<Object>();
    }


    private static final SessionFactory sessionFactory;
        static {
          try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
          } catch (Throwable ex) {

              LOG.error("Catch Exception", ex);

              ResourceBundle bundle = ResourceBundle.getBundle("bundle");
              JOptionPane.showMessageDialog(null, ex.getMessage(), bundle.getString("msgSessionFailed"),
                    JOptionPane.OK_OPTION);
            throw new ExceptionInInitializerError(ex);
          }
        }

    public static SessionFactory getSessionFactory() {
         return sessionFactory;
       }

    /**
     * Creates a list of employees
     * @return Employees object
     */
    public Employees[] getEmployees(){
        try {
        session = getSessionFactory().openSession();

          session.beginTransaction();
          LOG.info("CacheMode " +session.getCacheMode());
          listEmployees = session.createCriteria(Employees.class).list();

        } catch (Exception e) {

            LOG.error("Catch Exception", e);
            ResourceBundle bundle = ResourceBundle.getBundle("bundle");
            JOptionPane.showMessageDialog(null, e.getMessage(), bundle.getString("msgRequestFailed"),
                     JOptionPane.OK_OPTION);
            } finally {
                if(session.isOpen()){
                    session.close();
                }
            }

       requestEmployees = new Employees[listEmployees.size()];
          Integer arraySize = 0;
            for(Employees employee: listEmployees){
                requestEmployees[arraySize]=new Employees();
                requestEmployees[arraySize].setEmployee_id(employee.getEmployee_id());
                requestEmployees[arraySize].setSurname(employee.getSurname());
                requestEmployees[arraySize].setInitials(employee.getInitials());
                ++arraySize;
            };

      return requestEmployees;
    }

    /**
     * Creates a list of employee tasks
     * @param employeeId employee ID
     * @return tasks for employee
     */
    public Vector<Object> getEmployeeTasks(Integer employeeId){
        
        try {
        session = getSessionFactory().openSession();
        session.beginTransaction();

        listTasks = session.createCriteria(Tasks.class).list();
        } catch (Exception e) {

            LOG.error("Catch Exception", e);
            ResourceBundle bundle = ResourceBundle.getBundle("bundle");
             JOptionPane.showMessageDialog(null, e.getMessage(), bundle.getString("msgTaskRequestFailed"),
                     JOptionPane.OK_OPTION);
            } finally {
                if(session.isOpen()){
                session.close();
                }
            }
        
        employeeTask.clear();
        employeeTasks.clear();
        
            for(Tasks task: listTasks){
                if (task.getEmployee_id() == employeeId){
                    employeeTask.addElement(task.getIs_finished());
                    employeeTask.addElement(task.getDescription());
                    employeeTask.addElement(dataFormat.format(task.getStart_date()));
                    employeeTask.addElement(dataFormat.format(task.getFinish_date()));
                    employeeTask.addElement(dataFormat.format(task.getCompletion_date()));
                    employeeTasks.addElement(employeeTask.clone());
                    employeeTask.clear();
                }
            };
        return employeeTasks;
    }

    private Vector<Object> employeeTask;
    private Vector<Object> employeeTasks = new Vector<Object>();
    private List<Tasks> listTasks;
    private List<Employees> listEmployees;
    private Employees[] requestEmployees;
    private Employees employee;
    private Tasks task;

    private Session session;
    private DateFormat dataFormat;

}
