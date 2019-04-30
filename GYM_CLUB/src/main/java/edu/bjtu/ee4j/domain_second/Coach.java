package edu.bjtu.ee4j.domain_second;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<Course> courses;
    public Set<Course> getCourses() {
        return courses;
    }
    @Column(name = "coach_name")  
    private String coach_name;
    
    @Column(name = "sex")
    private String sex;
    
  
    @Column(name = "age")
    private int age;
    
  

    @Column(name = "address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoach_name() {
        return coach_name;
    }
    public void setCoach_name(String coachName) {
        this.coach_name=coachName;
    }
  
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
  

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    

}
