package core.Daemon.reporting.proposals;

import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowRequest.domain.ValueObjects.Location;
import eapli.framework.representations.dto.DTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@DTO
public class DeliveryReporting {
    public ShowProposalNumber proposalNumber;
    public LocalDate dateOfShow;
    public LocalTime timeOfShow;
    public Duration showDuration;
    public Location showLocation;

    public DeliveryReporting(final ShowProposalNumber proposalNumber, final LocalDate dateOfProposal,
                             final LocalTime timeOfProposal, final Duration showDuration,
                             final Location showLocation) {
        this.proposalNumber = proposalNumber;
        this.dateOfShow = dateOfProposal;
        this.timeOfShow = timeOfProposal;
        this.showDuration = showDuration;
        this.showLocation = showLocation;
    }
}
