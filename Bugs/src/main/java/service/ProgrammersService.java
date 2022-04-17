package service;

import model.Programmer;
import repository.ProgrammersRepository;

public class ProgrammersService {

    private final ProgrammersRepository programmersRepository;

    public ProgrammersService(ProgrammersRepository programmersRepository) {
        this.programmersRepository = programmersRepository;
    }

    public Programmer login(String username, String password) {
        return programmersRepository.findOneByUsernameAndPassword(username, password);
    }

    public void updatePassword(Programmer currentProgrammer) {
        programmersRepository.update(currentProgrammer);
    }
}
