package security.DAO.Impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import security.DAO.UserDAO;
import security.domain.Auth;
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
    public Set getUserRolesById(Long id) {
        Session session = null;
        Set<Role> roles = new HashSet();
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            User user = (User) session.load(User.class, id);
            roles = user.getRoles();

            for (Role role : roles) {
                System.out.println("Role: " + role.getRoleName());
            }
//                String query = "from Role where userId=:id";
//            roles = (Set<Role>) session.createQuery(query)
//                    .setParameter("id", id)
//                    .list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return roles;
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
    public Long authUser(String userName, String password) throws SQLException {
        Session session = null;
        Auth auth = null;
        try {
            System.out.println("Before auth");
            session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("After session");

            String query = "from Auth where userName=:username and password=:password";
            auth = (Auth) session.createQuery(query)
                    .setParameter("username", userName)
                    .setParameter("password", password)
                    .uniqueResult();

        } catch (org.hibernate.NonUniqueResultException e) {
            System.out.println("Perviy catch");
        } catch (Exception e) {
            System.out.println("Second catch");
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if (auth != null)
            return auth.getUserId();
        else
            return null;
    }
}
