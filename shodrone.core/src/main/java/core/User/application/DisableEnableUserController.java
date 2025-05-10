package core.User.application;

import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;

@UseCaseController
public class DisableEnableUserController {
    private final UserRepository userRepository = PersistenceContext.repositories().users();


    public Iterable<SystemUser> listAllUsers() {
        return userRepository.findByActive(true);
    }


    public void enableUser(SystemUser user) {
        Preconditions.nonNull(user, "User cannot be null");
        if (!user.isActive()) {
            user.activate();
            userRepository.save(user);
        }
    }


    public void disableUser(SystemUser user) {
        Preconditions.nonNull(user, "User cannot be null");
        if (user.isActive()) {
            // Passando a data atual para a desativação
            Calendar deactivatedOn = Calendar.getInstance();
            user.deactivate(deactivatedOn);  // Passando o Calendar para o método deactivate
            userRepository.save(user);
        }
    }
}
