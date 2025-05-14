package core.ShowDesigner.repositories;

import core.Shared.domain.ValueObjects.Email;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;

public interface ShowDesignerRepository extends DomainRepository<EmailAddress, ShowDesigner> {

    /**
     * Returns the show designer with the given email address.
     *
     * @param email the email address of the show designer
     * @return the show designer with the given email address
     */
    ShowDesigner findByEmail(Email email);

    /**
     * Returns the show designer with the given email address.
     *
     * @param email the email address of the show designer
     * @return the show designer with the given email address
     */
    ShowDesigner findByEmail(EmailAddress email);
}
