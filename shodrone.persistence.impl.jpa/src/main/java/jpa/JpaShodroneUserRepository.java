package jpa;

import core.Persistence.Application;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
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
        super(autoTx, "username");
    }

    /**
     * Constructor for JPA ShodroneUser Repository.
     *
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaShodroneUserRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "username");
    }

    /**
     * Finds a ShodroneUser by username.
     *
     * @param username the username of the ShodroneUser
     * @return the ShodroneUser with the given username
     */
    @Override
    public ShodroneUser findByUsername(Username username) {
        // Test query: does phoneNumber 910000002 exist?
        PhoneNumber testPhoneNumber = new PhoneNumber("+351","910000002");
        final TypedQuery<ShodroneUser> phoneQuery = entityManager().createQuery(
                "SELECT u FROM ShodroneUser u WHERE u.phoneNumber = :phone", ShodroneUser.class);
        phoneQuery.setParameter("phone", testPhoneNumber);

        ShodroneUser usersWithPhone = phoneQuery.getSingleResult();
        if (usersWithPhone == null ) {
            System.out.println("No user found with phone number 910000002");
        } else {
            System.out.println("Found user(s) with phone number 910000002: " + usersWithPhone.user().username());
        }

        // Original query
        final TypedQuery<ShodroneUser> shodroneUserQuery = entityManager().createQuery(
                "SELECT u FROM ShodroneUser u WHERE u.systemUser.username = :username", ShodroneUser.class);
        shodroneUserQuery.setParameter("username", username.toString());

        return shodroneUserQuery.getSingleResult();
    }


    @Override
    public ShodroneUser findByEmail(Email email) {
        final TypedQuery<ShodroneUser> query = entityManager().createQuery(
                "SELECT u FROM ShodroneUser u WHERE u.email = :email", ShodroneUser.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
