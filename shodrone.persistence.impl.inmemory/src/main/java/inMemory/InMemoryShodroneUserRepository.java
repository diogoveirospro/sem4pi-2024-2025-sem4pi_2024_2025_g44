package inMemory;

import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

public class InMemoryShodroneUserRepository extends InMemoryDomainRepository<ShodroneUser, Username> implements ShodroneUserRepository {

    /**
     * Constructor for InMemoryShodroneUserRepository.
     */
    static {
        InMemoryInitializer.init();
    }

    /**
     * Finds a ShodroneUser by username.
     * @param username the username of the ShodroneUser
     * @return the ShodroneUser with the given username
     */
    @Override
    public ShodroneUser findByUsername(Username username) {
        Iterable<ShodroneUser> users = findAll();

        for (ShodroneUser user : users) {
            if (user.user().username().equals(username)) {
                return user;
            }
        }

        return null;
    }
}
