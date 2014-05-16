package security.domain;


import javax.persistence.*;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 24.04.2014.
 */
@Entity
@Table(name = "user")
public class User  {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

//    @Column(name = "username")
//    private String username;

//    @Column(name = "password")
//    private String password;

    @Column(name = "date_reg")
    private Date dateReg;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "comment")
    private String comment;

//    @Column(name = "auth_id")
//    private Long authId;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="auth_id")
    private Auth auth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "role_id",
                    nullable = false, updatable = false) })
    private Set<Role> roles = new HashSet<Role>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> categories) {
        this.roles = categories;
    }

//    public Long getAuthId() {
//        return authId;
//    }
//
//    public void setAuthId(Long authId) {
//        this.authId = authId;
//    }


    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

//    @Override
//    public String getName() {
//        return "user1";
//    }
}



