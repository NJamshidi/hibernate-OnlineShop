package tamrinshop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;
import tamrinshop.model.Cart;
import tamrinshop.enumaration.CartStatus;
import tamrinshop.model.person.User;



import java.util.List;

public class CartDao extends BaseDao {

    public void create(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cart);
        session.merge(cart.getProduct());
        session.merge(cart.getUser());
        transaction.commit();
        session.close();
    }

    public List<Cart> getCartsByStatus(User user, CartStatus cartStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Cart> query = session.createQuery("from Cart c where c.user.id=:userId and c.cartStatus=:cartStatus", Cart.class);
        query.setParameter("userId", user.getId());
        query.setParameter("cartStatus", cartStatus);
        List<Cart> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void remove(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cart);
        transaction.commit();
        session.close();
    }

    public void updateStatus(List<Cart> carts) {
        for (Cart cart : carts) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(cart);
            transaction.commit();
            session.close();
        }
    }
    public int findCountOfItemsByUserId(int id) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Cart.class, "c");
        SimpleExpression userIdCond = Restrictions.eq("c.user.id", id);
        criteria.add(userIdCond);
        criteria.setProjection(Projections.rowCount());
        return (Integer) criteria.uniqueResult();
    }
}
