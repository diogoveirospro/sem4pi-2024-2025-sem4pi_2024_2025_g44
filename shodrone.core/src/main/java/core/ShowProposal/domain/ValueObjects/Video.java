package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Video implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String url;

    protected Video() {
    }

    public Video(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String title() {
        return title;
    }

    public String url() {
        return url;
    }

    @Override
    public String toString() {
        return "Title: " + title + " URL:" + url;
    }
}