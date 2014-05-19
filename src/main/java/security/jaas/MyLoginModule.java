package security.jaas;

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

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;

    private SecurityUser securityUser;

    @Override

    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {

        handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);

        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1])
                    .getPassword());

            System.out.println("Name: " + name);
            System.out.println("Pass: " + password);


            securityUser = Factory.getInstance().getUserDAO().authUser(name, password);
            if (securityUser != null) {
                System.out.println("Login: " + securityUser.getLogin());
                return true;
            }

            throw new LoginException("Authentication failed");

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

        userPrincipal = new UserPrincipal(securityUser.getLogin());
        subject.getPrincipals().add(userPrincipal);

        if (securityUser.getRoles() != null && securityUser.getRoles().size() > 0) {
            for (Role role : securityUser.getRoles()) {
                System.out.printf("Role: " + role.getRoleName());
                rolePrincipal = new RolePrincipal(role.getRoleName());
                subject.getPrincipals().add(rolePrincipal);
            }
        }
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }

}