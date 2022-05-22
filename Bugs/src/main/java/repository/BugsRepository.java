package repository;

import model.Bug;

public class BugsRepository extends AbstractHibernateRepository<Bug, Long>{
    public BugsRepository() {
        super(Bug.class);
    }
}
