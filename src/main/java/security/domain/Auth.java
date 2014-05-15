package security.domain;

import javax.persistence.*;

/**
 * Created by 1 on 13.05.2014.
 */
@Entity
@Table(name="auth")

public class Auth {

    @Id
    @Column(name="auth_id")
    @GeneratedValue
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="user_id", nullable = false)
    private Long userId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
