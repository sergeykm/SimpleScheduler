package kml.testproj.scheduler;

import junit.framework.TestCase;
import java.util.Date;

/**
 * Unit test for Tasks.
 */
public class TasksTest extends TestCase {

    Tasks tasks = new Tasks();
    Date testDate = new Date();


    public void testGetTask_id() throws Exception {
        Integer expected = 1;
        tasks.setTask_id(expected);
        Integer actual = tasks.getTask_id();

        assertEquals(expected,actual);
    }

    public void testGetEmployee_id() throws Exception {
        Integer expected = 1;
        tasks.setEmployee_id(expected);
        Integer actual = tasks.getEmployee_id();

        assertEquals(expected,actual);
    }

    public void testGetIs_finished() throws Exception {
        Boolean expected = true;
        tasks.setIs_finished(expected);
        Boolean actual = tasks.getIs_finished();

        assertEquals(expected,actual);
    }

    public void testGetDescription() throws Exception {
        String expected = "Task one";
        tasks.setDescription(expected);
        String actual = tasks.getDescription();

        assertEquals(expected,actual);
    }

    public void testGetStart_date() throws Exception {
        Date expected = testDate;
        tasks.setStart_date(expected);
        Date actual = tasks.getStart_date();

        assertEquals(expected,actual);
    }

    public void testGetFinish_date() throws Exception {
        Date expected = testDate;
        tasks.setFinish_date(expected);
        Date actual = tasks.getFinish_date();

        assertEquals(expected,actual);
    }

    public void testGetCompletion_date() throws Exception {
        Date expected = testDate;
        tasks.setCompletion_date(expected);
        Date actual = tasks.getCompletion_date();

        assertEquals(expected,actual);
    }

}
