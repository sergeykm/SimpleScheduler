package kml.testproj.scheduler;

import junit.framework.TestCase;
import java.util.Vector;

/**
 * Unit test for HibernateInAction.
 */
public class HibernateInActionTest extends TestCase {

    HibernateInAction hibernateInAction = new HibernateInAction();
    Integer existDbRow = 0;
    Integer expectedLength = 5;

    public void testGetSessionFactory() throws Exception {

    }

    /**
     * Test method GetEmployees
     */
    public void testGetEmployees() throws Exception {

        Employees[] actual = hibernateInAction.getEmployees();

        assertNotNull(actual);
    }

    /**
     * Test method GetEmployeeTasks
     */
    public void testGetEmployeeTasks() throws Exception {
        Integer existId = hibernateInAction.getEmployees()[existDbRow].getEmployee_id();
        Vector<Object> receivedResult = hibernateInAction.getEmployeeTasks(existId);

        for(int i = 0; i<receivedResult.size(); i++){
            Integer actual = receivedResult.elementAt(i).toString().split(",").length;
            Integer expected = expectedLength;

            assertEquals(expected,actual);
        }
    }
}
