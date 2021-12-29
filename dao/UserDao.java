package tamrinshop.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tamrinshop.model.person.User;

import java.util.List;

public class UserDao extends  BaseDao {
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        List<User> result;
        session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.username=:username", User.class);
        query.setParameter("username", username);
        result = query.list();
        assert result != null;
        try {
            return result.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        session.saveOrUpdate(user.getAddress());
        transaction.commit();
        session.close();
    }

    public List<User> readAll() {
        Session session = sessionFactory.openSession();
        List<User> result;
        session.beginTransaction();
        Query<User> query = session.createQuery("FROM User", User.class);
        result = query.list();
        return result;
    }
}

