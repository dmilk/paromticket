package domain;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by 1 on 24.04.2014.
 */
@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    @Column(name = "date_reg")
    private Date dateReg;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "login")
    private String login;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}



