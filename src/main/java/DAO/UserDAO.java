package DAO;

import domain.User;

/**
 * Created by 1 on 20.05.2014.
 */
public interface UserDAO {
    public User getUserByLogin(String login);
}
