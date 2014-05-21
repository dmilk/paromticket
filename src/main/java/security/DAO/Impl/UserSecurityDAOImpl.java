package security.DAO.Impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import security.DAO.UserSecurityDAO;
import security.domain.SecurityUser;
import domain.User;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by 1 on 12.05.2014.
 */
public class UserSecurityDAOImpl implements UserSecurityDAO {

    @Override
    public User getUserById(Long id) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.load(User.class, id);
            Hibernate.initialize(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }



    @Override
    public String getUserGreetings(Long id) {
        Session session = null;
        User user = null;
        String result = "";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.load(User.class, id);
            result = "Hello " + user.getFirstName() + " " + user.getLastName();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }


    @Override
    public SecurityUser authUser(String login, String password) throws SQLException {
        Session session = null;
        SecurityUser securityUser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "from SecurityUser where login=:login and password=:password";
            securityUser = (SecurityUser) session.createQuery(query)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .uniqueResult();

            if (securityUser != null) {
                Hibernate.initialize(securityUser);
                if (securityUser.getRoles() != null)
                    Hibernate.initialize(securityUser.getRoles());
            }


        } catch (org.hibernate.NonUniqueResultException e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
            return securityUser;
    }
}
