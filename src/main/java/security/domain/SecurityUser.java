package security.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 13.05.2014.
 */
@Entity
@Table(name="security_user")

public class SecurityUser {

    @Id
    @Column(name="security_user_id")
    @GeneratedValue
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name="password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "security_user_role", joinColumns = {
            @JoinColumn(name = "security_user_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "role_id",
                    nullable = false, updatable = false) })
    private Set<Role> roles = new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
