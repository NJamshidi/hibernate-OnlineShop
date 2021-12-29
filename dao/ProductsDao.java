package tamrinshop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tamrinshop.model.products.Product;

//import javax.persistence.Query;
import java.util.List;

public abstract class ProductsDao extends BaseDao {
    public List<Product> readAll(String tableName) {
        Session session = sessionFactory.openSession();
        List<Product> result;
        Transaction transaction = session.beginTransaction();
        Query<Product> query = session.createQuery(String.format("FROM %s", tableName), Product.class);
        result = query.list();
        transaction.commit();
        session.close();
        return result;
    }

    public void increaseTheCountOfProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(product);
        transaction.commit();
        session.close();
    }

    public Product findById(String tableName, int id) {
        Session session = sessionFactory.openSession();
        List<Product> result;
        session.beginTransaction();
        Query<Product> query = session.createQuery(String.format("from %s p where p.id=:id", tableName), Product.class);
        query.setParameter("id", id);
        result = query.list();
        assert result != null;
        try {
            return result.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
