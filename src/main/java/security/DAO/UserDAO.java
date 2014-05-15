package security.DAO;

import security.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 12.05.2014.
 */
public interface UserDAO {
    //public void addUser(User user) throws SQLException;
    public User getUserById(Long id) throws SQLException;    //получить стедента по id
    public Set getUserRolesById(Long id);
    public String getUserGreetings(Long id);
    public Long authUser(String userName, String password) throws SQLException;
}
