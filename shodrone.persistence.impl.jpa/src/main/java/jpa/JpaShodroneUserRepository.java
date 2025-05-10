package jpa;

import core.Persistence.Application;
import core.Shared.domain.ValueObjects.Email;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaShodroneUserRepository extends JpaAutoTxRepository<ShodroneUser, Long, Username> implements ShodroneUserRepository {

    /**
     * Constructor for JPA ShodroneUser Repository.
     * @param autoTx the transactional context
     */
    public JpaShodroneUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "username");
    }

    /**
     * Constructor for JPA ShodroneUser Repository.
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaShodroneUserRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "username");
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

    @Override
    public ShodroneUser findByEmail(Email email) {
        final TypedQuery<ShodroneUser> query = entityManager().createQuery(
                "SELECT u FROM ShodroneUser u WHERE u.email = :email", ShodroneUser.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
