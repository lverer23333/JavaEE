package edu.bjtu.ee4j.domain_second;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
    

    
    @Column(name = "course_name")
    private String course_name;
    

  
    @Column(name = "date")
    private String date;
    
    @Column(name = "time")
    private String time;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coach getCoach() {
        return coach;
    }
    
    public void setCoach(Coach coach) {
        this.coach=coach;
    }
  
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
 
    public String getCourse_name() {
        return course_name;
    }

    public String getDate() {
        return date;
    }

    public void seDate(String date) {
        this.date=date;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
  
}
