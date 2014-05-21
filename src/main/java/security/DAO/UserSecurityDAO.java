package security.DAO;

import security.domain.SecurityUser;
import domain.User;

import java.sql.SQLException;

/**
 * Created by 1 on 12.05.2014.
 */
public interface UserSecurityDAO {
    //public void addUser(User user) throws SQLException;
    public User getUserById(Long id) throws SQLException;    //получить стедента по id
    public String getUserGreetings(Long id);
    public SecurityUser authUser(String userName, String password) throws SQLException;
}
