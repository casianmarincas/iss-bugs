package service;

import model.Bug;
import model.Programmer;
import model.Tester;

import java.util.List;

public class Service {

    private final ProgrammersService programmersService;
    private final TestersService testersService;
    private final BugsService bugsService;
    private final BugsAssignmentsService bugsAssignmentsService;
    private Programmer currentProgrammer;

    private Tester currentTester;
    private Bug currentBug;

    public Service(ProgrammersService programmersService, TestersService testersService,
                   BugsService bugsService, BugsAssignmentsService bugsAssignmentsService) {
        this.programmersService = programmersService;
        this.testersService = testersService;
        this.bugsService = bugsService;
        this.bugsAssignmentsService = bugsAssignmentsService;
    }

    public Programmer getCurrentProgrammer() {
        return currentProgrammer;
    }

    public Tester getCurrentTester() {
        return currentTester;
    }

    public void loginProgrammer(String username, String password) {
        currentProgrammer = programmersService.login(username, password);
    }

    public void loginTester(String username, String password) {
        currentTester = testersService.login(username, password);
    }

    public void logoutProgrammer() {
        currentProgrammer = null;
    }

    public void logoutTester() {
        currentTester = null;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (currentProgrammer != null) {
            if (! oldPassword.equals(currentProgrammer.getPassword())) {
                throw new RuntimeException("The old password is wrong!");
            }
            currentProgrammer.setPassword(newPassword);
            programmersService.updatePassword(currentProgrammer);
        } else if (currentTester != null) {
            if (! oldPassword.equals(currentTester.getPassword())) {
                throw new RuntimeException("The old password is wrong!");
            }
            currentTester.setPassword(newPassword);
            testersService.updatePassword(currentTester);
        }
    }

    public void addBug(String name, String projectName, String description) {
        bugsService.addBug(name, currentTester, projectName, description);
    }

    public List<Bug> getAllBugs() {
        return bugsService.getAllBugs();
    }

    public void setCurrentBug(Bug e) {
        this.currentBug = e;
    }

    public Bug getCurrentBug() {
        return currentBug;
    }

    public void assignCurrentBug() {
        bugsAssignmentsService.addAssignment(currentProgrammer, currentBug);
        bugsService.updateBug(currentBug, "assigned");
    }

    public void markCurrentBugAsResolved() {
        bugsService.updateBug(currentBug, "solved");
    }

    public List<Bug> getBugsForCurrentProgrammer() {
        return bugsAssignmentsService.getAllForProgrammer(currentProgrammer);
    }

    public boolean currentProgrammerHasTheCurrentBug() {
        return bugsAssignmentsService.hasBug(currentProgrammer, currentBug);
    }

    public void approveCurrentBug() {
        bugsAssignmentsService.deleteAssignment(currentBug);
        bugsService.deleteBug(currentBug);
    }
}
