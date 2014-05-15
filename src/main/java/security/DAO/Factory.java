package security.DAO;

import security.DAO.Impl.UserDAOImpl;

public class Factory {
    private static Factory instance = null;
    private static UserDAO userDAO = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

}