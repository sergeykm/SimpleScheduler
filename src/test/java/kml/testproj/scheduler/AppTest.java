package kml.testproj.scheduler;

import junit.framework.TestCase;

/**
 * Unit test for App.
 */
public class AppTest  extends TestCase {

    App app = new App();
    Integer testLength = 3;
    Employees[] employees = new Employees[testLength];


    public void setUp() throws Exception {

        for(int i = 0; i<employees.length; i++){
            employees[i] = new Employees();
            employees[i].setEmployee_id(i);
            employees[i].setSurname("Ivanov");
            employees[i].setInitials("I.I.");
        }
    }

    public void tearDown() throws Exception {

    }

    /**
     * Test method EmployeesFullNames
     */
    public void testGetEmployeesFullNames() throws Exception {

        String[] actual = app.getEmployeesFullNames(employees);

        for (int i = 0; i<employees.length; i++){
            String expected = employees[i].getSurname() + " " + employees[i].getInitials();

            assertEquals(expected, actual[i]);
        }
    }

    /**
     * Test method FindEqualsFullNames
     */
    public void testGetEqualsFullNames() throws Exception {

        String[] fullNames = app.getEmployeesFullNames(employees);
        String[] actual = app.getEqualsFullNames(fullNames);

        for(int i=1; i<employees.length; i++){
            int equalsNumber = i + 1;
            String expected = fullNames[i] + "(" + equalsNumber + ")";

            assertEquals(expected,actual[i]);
        }
    }

    /**
     * Test method GetEmployeesComboItems
     */
    public void testGetEmployeesComboItems() throws Exception {
        String[] actual = app.getEmployeesComboItems(employees);

        for(int i=1; i<employees.length; i++){

            int equalsNumber = i + 1;
            String expected = employees[i].getSurname() + " " + employees[i].getInitials() + "(" + equalsNumber + ")";

            assertEquals(expected,actual[i]);
        }
    }

    /**
     * Test exist msgWrongData (bundle properties)
     */
    public void testWrongData() throws Exception {

        assertNotNull(app.msgWrongData);
    }

}
