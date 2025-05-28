package core.ShowProposal.application;

import core.Persistence.PersistenceContext;
import core.ShowProposal.application.Service.VideoList;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.Video;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class AddVideoToProposalController {

    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    private final VideoList videoList = new VideoList();

    public Iterable<ShowProposal> listProposals(ShowRequest showRequest) {
        return showProposalRepository.findAllTestingProposalsByShowRequest(showRequest);
    }

    public List<Video> listVideos() {
        List<Video> videos = videoList.getVideos();
        if (videos.isEmpty()) {
            throw new IllegalArgumentException("No videos available.");
        }
        return videos;
    }

    public void addVideoToProposal(ShowProposal showProposal, Video video) {

        if (showProposal == null) {
            throw new IllegalArgumentException("Show proposal cannot be null.");
        }
        if (video == null) {
            throw new IllegalArgumentException("Video cannot be null.");
        }
        if (showProposal.status() != ShowProposalStatus.TESTING) {
            throw new IllegalArgumentException("Cannot add video to a non-testing proposal.");
        }

        showProposal.addVideo(video);
        showProposalRepository.save(showProposal);
    }

    public Iterable<ShowRequest> listRequests() {
        Iterable<ShowRequest> showRequests = showRequestRepository.findAllCreatedShowRequests();
        if (!showRequests.iterator().hasNext()) {
            throw new IllegalArgumentException("No show requests available.");
        }
        return showRequests;
    }
}
