package core.Daemon.reporting.shows;

import core.Drone.domain.Entities.Drone;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.domain.ValueObjects.Insurance;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.domain.ValueObjects.Video;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import eapli.framework.representations.dto.DTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@DTO
public class ShowReporting {
    public ShowProposalNumber proposalNumber;
    public ShowRequestID requestNumber;
    public Map<Model, List<Drone>> droneConfiguration;
    public Video video;
    public Set<Figure> figuresConfiguration;
    public ShowDescription showDescription;
    public Location showLocation;
    public LocalDate showDate;
    public LocalTime showTime;
    public QuantityOfDrones quantityOfDrones;
    public Insurance insurance;
    public Duration showDuration;

    public ShowReporting(final ShowProposalNumber proposalNumber, final ShowRequestID requestNumber,
                         final Map<Model, List<Drone>> droneConfiguration, final Video video,
                         final Set<Figure> figuresConfiguration, final ShowDescription showDescription,
                         final Location showLocation, final LocalDate showDate, final LocalTime showTime,
                         final QuantityOfDrones quantityOfDrones, final Insurance insurance,
                         final Duration showDuration) {
        this.proposalNumber = proposalNumber;
        this.requestNumber = requestNumber;
        this.droneConfiguration = droneConfiguration;
        this.video = video;
        this.figuresConfiguration = figuresConfiguration;
        this.showDescription = showDescription;
        this.showLocation = showLocation;
        this.showDate = showDate;
        this.showTime = showTime;
        this.quantityOfDrones = quantityOfDrones;
        this.insurance = insurance;
        this.showDuration = showDuration;
    }
}
