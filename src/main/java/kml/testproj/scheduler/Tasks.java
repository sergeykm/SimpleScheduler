package kml.testproj.scheduler;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Tasks")
public class Tasks {
    @Id
    @Column(name="task_id")
    private int task_id;
    private Integer employee_id;
    private Boolean is_finished;
    @Lob
    private String description;
    @Temporal(TemporalType.DATE)
    private Date start_date;
    @Temporal(TemporalType.DATE)
    private Date finish_date;
    @Temporal(TemporalType.DATE)
    private Date completion_date;

    
    public int getTask_id() {
        return task_id;
    }
    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    public Boolean getIs_finished() {
        return is_finished;
    }
    public void setIs_finished(Boolean is_finished) {
        this.is_finished = is_finished;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    
    public Date getFinish_date() {
        return finish_date;
    }
    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }
    
    public Date getCompletion_date() {
        return completion_date;
    }
    public void setCompletion_date(Date completion_date) {
        this.completion_date = completion_date;
    }

}
