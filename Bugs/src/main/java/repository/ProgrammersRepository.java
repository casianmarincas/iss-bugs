package repository;

import model.Programmer;
import model.Tester;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class ProgrammersRepository extends AbstractHibernateRepository<Programmer, Long>{
    public ProgrammersRepository() {
        super(Programmer.class);
    }

    public Programmer findOneByUsernameAndPassword(String username, String password) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Programmer where username=:username and password=:password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            var result = query.getResultList();
            if (result.size() == 0) {
                throw new RuntimeException("No such user!");
            }
            tx.commit();
            return (Programmer) result.get(0);
        }
    }
}
