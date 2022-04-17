package service;

import model.Tester;
import repository.TestersRepository;

public class TestersService {

    private final TestersRepository testersRepository;

    public TestersService(TestersRepository testersRepository) {
        this.testersRepository = testersRepository;
    }

    public Tester login(String username, String password) {
        return testersRepository.findOneByUsernameAndPassword(username, password);
    }

    public void updatePassword(Tester currentTester) {
        testersRepository.update(currentTester);
    }
}
