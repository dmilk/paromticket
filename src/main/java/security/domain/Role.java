package security.domain;

import javax.persistence.*;
import java.security.Principal;

/**
 * Created by 1 on 24.04.2014.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "comment")
    private String comment;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
