package edu.bjtu.ee4j.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nick_name")  
    @NotEmpty(message = "NickName is required.")
    @Size(min = 2, message = "NickName must be at least 2 characters.")
    private String nick_name;
    
    @Column(name = "password")
    @NotEmpty(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters.")
    private String password;
    
  
    @Column(name = "email")
    @Email(message="Wrong Email.")
    @NotEmpty(message = "Email is required.")
    private String email;
    
   
    @Column(name = "phone_no")
    @Size(min = 11, max = 11, message = "Mobile no. must be 11 digits.")
    private String phone_no;    
    
    @Column(name = "address")
    @NotEmpty(message = "Address is required.")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }
    public void setNick_name(String nickName) {
        this.nick_name=nickName;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setEmail(String email) {
        this.email= email;
    }
    public String getEmail() {
        return email;
    }

    public void setPhone_no(String mobileNumber) {
        this.phone_no = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
