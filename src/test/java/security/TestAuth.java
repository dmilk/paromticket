package security;

import static org.junit.Assert.*;
import org.junit.Test;
import security.DAO.Factory;
import security.domain.SecurityUser;

import java.sql.SQLException;

public class TestAuth {
    @Test
    public void testAdminLogin() {
        SecurityUser securityUser = null;
        try {
            securityUser = Factory.getInstance().getSecurityUserDAO().authUser("admin", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(securityUser.getLogin(), "admin");

    }
}
