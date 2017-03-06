/**
 * 
 */
package com.assignment.enterprise.task;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.assignment.model.task.User;

/**
 * @author Vinayak Kanade
 *
 */
@Repository
public class UserDaoImpl implements UserDaoIF {
    @Autowired
    private SessionFactory    sessionFactory;

    @Autowired
    private HibernateTemplate hibernatetemplate;

    @Override
    public void saveUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUser() {
        return hibernatetemplate.find("from user");
    }

    @Override
    public User getUserById(Integer userId) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("userId", userId));
        List<User> user = hibernatetemplate.findByCriteria(dc);
        return user.get(0);
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
