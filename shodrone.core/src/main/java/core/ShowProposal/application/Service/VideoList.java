package core.ShowProposal.application.Service;

import core.ShowProposal.domain.ValueObjects.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoList {
    private final List<Video> videos;

    public VideoList() {
        this.videos = new ArrayList<>();
        initializeVideos();
    }

    private void initializeVideos() {
        videos.add(new Video("LiteBee Stars 200pcs Drones/ Outdoor Drone Light Shows",
                "https://www.youtube.com/watch?v=G4-2bls6-Z0&ab_channel=DroneSolutionProvider"));
        videos.add(new Video("Show de drones no Brasil",
                "https://www.youtube.com/watch?v=4oTR5SJQCw4&ab_channel=MaxDroneBrasil"));
        videos.add(new Video("Biggest drone display ever! - Guinness World Records",
                "https://www.youtube.com/watch?v=44KvHwRHb3A&ab_channel=GuinnessWorldRecords"));
        videos.add(new Video("5200 Drone light show, Breaking 4 World Records -- High Great",
                "https://www.youtube.com/watch?v=n9tu-L59YqQ&ab_channel=HighGreatdroneshow"));
        videos.add(new Video("Deadpool And Wolverine Drone Show is the Largest in America (2,400 drones) | Sky Elements",
                "https://www.youtube.com/watch?v=CVP1FeG0qlY&ab_channel=SkyElementsDroneShows"));
        videos.add(new Video("Drone Show anuncia a inauguração da nova Disneylândia em Abu Dhabi, Emirados Árabes Unidos",
                "https://www.youtube.com/watch?v=_Sqd1E59OPc&ab_channel=LumaskyDroneShow"));
    }

    public List<Video> getVideos() {
        return new ArrayList<>(videos);
    }

    public void addVideo(String title, String url) {
        videos.add(new Video(title, url));
    }
}