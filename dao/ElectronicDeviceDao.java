package tamrinshop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tamrinshop.model.products.ElectronicDevice;

public class ElectronicDeviceDao extends ProductsDao {
    public void create(ElectronicDevice electronicDevice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(electronicDevice);
        transaction.commit();
        session.close();
    }

    public void delete(ElectronicDevice electronicDevice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(electronicDevice);
        transaction.commit();
        session.close();
    }
}
