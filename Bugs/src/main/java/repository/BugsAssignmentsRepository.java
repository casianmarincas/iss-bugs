package repository;

import model.BugAssignment;

public class BugsAssignmentsRepository extends AbstractHibernateRepository<BugAssignment, Long> {
    public BugsAssignmentsRepository() {
        super(BugAssignment.class);
    }
}
