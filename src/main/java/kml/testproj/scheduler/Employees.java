package kml.testproj.scheduler;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity(name="Employees")
public class Employees {
    @Id
    @Column(name="employee_id")
    private int employee_id;
    private String surname;
    private String initials;

    public int getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getInitials() {
        return initials;
    }
    public void setInitials(String initials) {
        this.initials = initials;
    }
}
