package service;

import model.Bug;
import model.Tester;
import repository.BugsRepository;

import java.util.List;

public class BugsService {

    private final BugsRepository bugsRepository;

    public BugsService(BugsRepository bugsRepository) {
        this.bugsRepository = bugsRepository;
    }

    public void addBug(String name, Tester currentTester, String projectName, String description) {
        Bug bug = new Bug(name, currentTester, projectName, description);
        bugsRepository.add(bug);
    }

    public List<Bug> getAllBugs() {
        return bugsRepository.findAll();
    }

    public void updateBug(Bug currentBug, String assigned) {
        currentBug.setStatus(assigned);
        bugsRepository.update(currentBug);
    }

    public void deleteBug(Bug currentBug) {
        bugsRepository.delete(currentBug);
    }
}
