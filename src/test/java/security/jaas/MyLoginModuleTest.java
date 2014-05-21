package security.jaas;

import org.junit.Before;
import org.junit.Test;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MyLoginModuleTest {
    private MyLoginModule loginModule;

    @Before
    public void init() {
        loginModule = new MyLoginModule();
    }

    @Test
    public void initializeTest() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("debug", "true");
        loginModule.initialize(null, null, null, options);
        assertTrue(loginModule.isDebug());

        options.clear();
        options.put("debug", "True");
        loginModule.initialize(null, null, null, options);
        assertTrue(loginModule.isDebug());

        options.clear();
        options.put("debug", "True1");
        loginModule.initialize(null, null, null, options);
        assertFalse(loginModule.isDebug());
    }

    @Test(expected = LoginException.class)
    public void testJaasLoginFailure() throws LoginException {
        LoginContext lc = null;
        System.setProperty("java.security.auth.login.config", "=c:\\dev\\apache-tomcat-7.0.42\\conf\\jaas.config ");
        lc = new LoginContext("RealmLogin", new JAASCallbackHandler("admin", "123456"));
        lc.login();
    }

    @Test
    public void testJaasLoginOk() throws LoginException {
        LoginContext lc = null;
        System.setProperty("java.security.auth.login.config", "=c:\\dev\\apache-tomcat-7.0.42\\conf\\jaas.config ");
        lc = new LoginContext("RealmLogin", new JAASCallbackHandler("admin", "12345"));
        lc.login();
        Subject subject = lc.getSubject();
        Set<Principal> principals = subject.getPrincipals();
        System.out.println(principals);
        assertEquals(2, principals.size());
        for(Principal principal : principals) {
            if (principal instanceof UserPrincipal) {
                UserPrincipal userPrincipal = (UserPrincipal) principal;
                assertEquals("admin", userPrincipal.getName());
            } else if (principal instanceof RolePrincipal) {
                RolePrincipal rolePrincipal = (RolePrincipal) principal;
                assertEquals("admin", rolePrincipal.getName());
            }
        }


    }

}
