package security.jaas;

import org.apache.log4j.Logger;
import security.DAO.Factory;
import security.domain.Role;
import security.domain.SecurityUser;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class MyLoginModule implements LoginModule {

    private static Logger LOGGER = Logger.getLogger(MyLoginModule.class);

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    private boolean debug = false;

    private boolean loginSucceeded = false;
    private boolean commitSucceeded = false;

    private SecurityUser securityUser;

    private UserPrincipal userPrincipal = null;
    private RolePrincipal rolePrincipal = null;


    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {

        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        debug = "true".equalsIgnoreCase((String) options.get("debug"));
    }

    @Override
    public boolean login() throws LoginException {

        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        // echoOn = false
        callbacks[1] = new PasswordCallback("password", false);

        try {
            callbackHandler.handle(callbacks);
            String login = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1])
                    .getPassword());

            if (debug) {
                LOGGER.debug("Login: " + login);
                LOGGER.debug("Password: " + password);
            }

            if (login == null || password == null) {
                LOGGER.error("Callback handler does not return authentication data properly");
                throw new LoginException("Callback handler does not return authentication data properly");
            }

            securityUser = Factory.getInstance().getUserDAO().authUser(login, password);
            if (securityUser != null) {
                System.out.println("Login: " + securityUser.getLogin());
                loginSucceeded = true;
                return true;
            }

//            throw new LoginException("Authentication failed");

        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (loginSucceeded) {
            return false;
        } else {
            userPrincipal = new UserPrincipal(securityUser.getLogin());

            if (!subject.getPrincipals().contains(userPrincipal)) {
                subject.getPrincipals().add(userPrincipal);
                LOGGER.debug("User principal added: " + userPrincipal);
            }

            if (securityUser.getRoles() != null && securityUser.getRoles().size() > 0) {
                for (Role role : securityUser.getRoles()) {
                    rolePrincipal = new RolePrincipal(role.getRoleName());
                    if (!subject.getPrincipals().contains(rolePrincipal)) {
                        subject.getPrincipals().add(rolePrincipal);
                        LOGGER.debug("Role principal added: " + rolePrincipal);
                    }
                }

            }

            commitSucceeded = true;

            LOGGER.info("Login subject were successfully commited");

            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (!loginSucceeded) {
            return false;
        }
        logout();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        userPrincipal = null;
        rolePrincipal = null;
        securityUser = null;
        loginSucceeded = false;
        commitSucceeded = false;
        return true;
    }

}