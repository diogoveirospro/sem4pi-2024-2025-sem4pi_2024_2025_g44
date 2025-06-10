package jpa;

import core.Persistence.Application;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JpaShodroneUserRepository extends JpaAutoTxRepository<ShodroneUser, Long, Username> implements ShodroneUserRepository {

    /**
     * Constructor for JPA ShodroneUser Repository.
     *
     * @param autoTx the transactional context
     */
    public JpaShodroneUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "SYSTEMUSER_USERNAME");
    }

    /**
     * Constructor for JPA ShodroneUser Repository.
     *
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaShodroneUserRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "SYSTEMUSER_USERNAME");
    }

    /**
     * Finds a ShodroneUser by username.
     *
     * @param username the username of the ShodroneUser
     * @return the ShodroneUser with the given username
     */
    @Override
    public ShodroneUser findByUsername(Username username) {
        System.out.println("Finding ShodroneUser by username: " + username);
        final TypedQuery<ShodroneUser> query = entityManager().createQuery(
                "SELECT u FROM ShodroneUser u WHERE u.systemUser.username = :username", ShodroneUser.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }



    @Override
    public ShodroneUser findByEmail(Email email) {
        final TypedQuery<ShodroneUser> query = entityManager().createQuery(
                "SELECT u FROM ShodroneUser u WHERE u.email = :email", ShodroneUser.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
