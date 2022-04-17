package service;

import model.Programmer;
import model.Tester;

public class Service {

    private final ProgrammersService programmersService;
    private final TestersService testersService;
    private Programmer currentProgrammer;

    private Tester currentTester;

    public Service(ProgrammersService programmersService, TestersService testersService) {
        this.programmersService = programmersService;
        this.testersService = testersService;
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
}
