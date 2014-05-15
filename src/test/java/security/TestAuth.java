package security;

import static org.junit.Assert.*;
import org.junit.Test;
import security.DAO.Factory;

import java.sql.SQLException;

public class TestAuth {
    @Test
    public void testAdminLogin() {
        long id = 0;
        try {
            id = Factory.getInstance().getUserDAO().authUser("admin", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(id, 1L);

    }
}
