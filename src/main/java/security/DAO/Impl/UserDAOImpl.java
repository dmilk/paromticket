package security.DAO.Impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import security.DAO.UserDAO;
import security.domain.SecurityUser;
import security.domain.Role;
import security.domain.User;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 12.05.2014.
 */
public class UserDAOImpl implements UserDAO {

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
    public SecurityUser authUser(String userName, String password) throws SQLException {
        Session session = null;
        SecurityUser securityUser = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String query = "from SecurityUser where userName=:username and password=:password";
            securityUser = (SecurityUser) session.createQuery(query)
                    .setParameter("username", userName)
                    .setParameter("password", password)
                    .uniqueResult();

            Hibernate.initialize(securityUser);
            Hibernate.initialize(securityUser.getRoles());

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
