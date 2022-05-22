package repository;

import model.Entity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateRepository<E extends Entity<T>, T extends Serializable> implements Repository<E, T> {

    private Class<E> elemType;

    public AbstractHibernateRepository(Class<E> elemType) {
        this.elemType = elemType;
    }

    public void add(E elem) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error on saving " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public void delete(E elem) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error on deleting " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public void update(E elem) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error on updating " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public E findOne(T id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(elemType, (Serializable) id);
        }
    }

    public List<E> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Table table = elemType.getAnnotation(Table.class);
                String entityName = elemType.getName();
                List<E> messages = session.createQuery(" from " + entityName + " C", elemType).list();
                tx.commit();
                return messages;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select " + ex);
                if (tx != null)
                    tx.rollback();
                return null;
            }
        }
    }
}
