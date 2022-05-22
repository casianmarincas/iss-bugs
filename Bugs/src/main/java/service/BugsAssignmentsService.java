package service;

import model.Bug;
import model.BugAssignment;
import model.Programmer;
import repository.BugsAssignmentsRepository;

import java.util.ArrayList;
import java.util.List;

public class BugsAssignmentsService {
    private final BugsAssignmentsRepository bugsAssignmentsRepository;

    public BugsAssignmentsService(BugsAssignmentsRepository bugsAssignmentsRepository) {
        this.bugsAssignmentsRepository = bugsAssignmentsRepository;
    }

    public void addAssignment(Programmer currentProgrammer, Bug currentBug) {
        bugsAssignmentsRepository.add(new BugAssignment(currentBug, currentProgrammer));
    }

    public List<Bug> getAllForProgrammer(Programmer programmer) {
        List<Bug> found = new ArrayList<>();
        List<BugAssignment> assignments = bugsAssignmentsRepository.findAll();
        for (BugAssignment ba : assignments) {
            if (ba.getProgrammer().equals(programmer)) {
                found.add(ba.getBug());
            }
        }
        return found;
    }

    public boolean hasBug(Programmer currentProgrammer, Bug currentBug) {
        for (BugAssignment ba : bugsAssignmentsRepository.findAll()) {
            if (ba.getProgrammer().equals(currentProgrammer) && ba.getBug().equals(currentBug)) {
                return true;
            }
        }
        return false;
    }

    public void deleteAssignment(Bug currentBug) {
        for (BugAssignment ba : bugsAssignmentsRepository.findAll()) {
            if (ba.getBug().equals(currentBug)) {
                bugsAssignmentsRepository.delete(ba);
                break;
            }
        }
    }
}
