package kml.testproj.scheduler;

import junit.framework.TestCase;

/**
 * Unit test for Employees.
 */
public class EmployeesTest extends TestCase {

    Employees employees = new Employees();


    public void testGetEmployee_id() throws Exception {

        Integer expected = 1;
        employees.setEmployee_id(expected);
        Integer actual = employees.getEmployee_id();

        assertEquals(expected,actual);
    }

    public void testGetSurname() throws Exception {
        String expected = "Ivanov";
        employees.setSurname(expected);
        String actual = employees.getSurname();

        assertEquals(expected,actual);
    }

    public void testGetInitials() throws Exception {
        String expected = "I.I.";
        employees.setInitials(expected);
        String actual = employees.getInitials();

        assertEquals(expected,actual);
    }

}
