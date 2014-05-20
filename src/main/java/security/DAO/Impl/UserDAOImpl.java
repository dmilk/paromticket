package security.DAO.Impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import security.DAO.UserDAO;
import security.domain.User;
import util.HibernateUtil;

import javax.swing.*;

/**
 * Created by 1 on 20.05.2014.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public User getUserByLogin(String login) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "from User where login=:login";
            user = (User) session.createQuery(query)
                    .setParameter("login", login)
                    .uniqueResult();

            Hibernate.initialize(user);

        } catch (org.hibernate.NonUniqueResultException e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
}
