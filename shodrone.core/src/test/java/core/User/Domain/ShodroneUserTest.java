package core.User.Domain;

import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.Entities.ShodroneUser;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ShodroneUserTest {
    private ShodroneUser user;
    private SystemUser systemUser;
    private PhoneNumber phoneNumber;
    private Email email;

    @BeforeEach
    void setUp() {
        Username username = Username.valueOf("testUser");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        PasswordPolicy policy = password -> true;

        Optional<Password> optionalPassword = Password.encodedAndValid("Password1", policy, encoder);
        assertTrue(optionalPassword.isPresent());
        EmailAddress emailAddress = EmailAddress.valueOf("user@example.com");
        Name name = Name.valueOf("Test", "User");
        SystemUserBuilder userBuilder = new SystemUserBuilder(policy, encoder)
                .withUsername(username)
                .withPassword("Password1")
                .withName(name)
                .withEmail(emailAddress);
        systemUser = userBuilder.build();

        phoneNumber = new PhoneNumber("+351", "912345678");
        email = new Email("user@example.com");
        user = new ShodroneUser(systemUser, phoneNumber);
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
        assertEquals(systemUser, user.user());
        assertEquals(email, user.email());
        assertEquals(phoneNumber, user.phoneNumber());
        assertEquals(systemUser.username(), user.identity());
    }

    @Test
    void testChangeEmail() {
        Email newEmail = new Email("new@example.com");
        user.changeEmail(newEmail);
        assertEquals(newEmail, user.email());
    }

    @Test
    void testChangePhoneNumber() {
        PhoneNumber newPhone = new PhoneNumber("+351", "987654321");
        user.changePhoneNumber(newPhone);
        assertEquals(newPhone, user.phoneNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        ShodroneUser sameUser = new ShodroneUser(systemUser, phoneNumber);
        assertEquals(user, sameUser);
        assertEquals(user.hashCode(), sameUser.hashCode());
    }

    @Test
    void testSameAs() {
        ShodroneUser sameUser = new ShodroneUser(systemUser, phoneNumber);
        assertTrue(user.sameAs(sameUser));
    }

    @Test
    void testIdentity() {
        assertEquals(systemUser.username(), user.identity());
    }
}
