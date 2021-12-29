package tamrinshop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tamrinshop.model.products.ReadableItem;

public class ReadableItemDao extends ProductsDao {
    public void create(ReadableItem readableItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(readableItem);
        transaction.commit();
        session.close();
    }

    public void delete(ReadableItem readableItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(readableItem);
        transaction.commit();
        session.close();
    }
}
