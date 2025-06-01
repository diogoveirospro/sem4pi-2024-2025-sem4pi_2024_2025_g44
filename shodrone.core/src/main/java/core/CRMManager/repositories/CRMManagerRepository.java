package core.CRMManager.repositories;

import core.CRMManager.domain.Entities.CRMManager;
import core.Shared.domain.ValueObjects.Email;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;

public interface CRMManagerRepository extends DomainRepository<EmailAddress, CRMManager> {
    CRMManager findByEmail(Email email);
}
