package security.DAO;

import security.DAO.Impl.UserSecurityDAOImpl;

public class Factory {
    private static Factory instance = null;
    private static UserSecurityDAO userSecurityDAO = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public UserSecurityDAO getSecurityUserDAO() {
        if (userSecurityDAO == null) {
            userSecurityDAO = new UserSecurityDAOImpl();
        }
        return userSecurityDAO;
    }

}