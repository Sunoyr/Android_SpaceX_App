package android.eservices.spacex.data.api.model;

import java.util.Date;
import java.util.List;

public class Launch {
    private int id;
    private Date date_utc;
    private Rocket rocket;
    private String details;
    private String name;
    private String webcast;//todo
    private Links links;

    public int getId() {
        return id;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public String getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public String getWebcast() {
        return webcast;
    }

    public Date getDate_utc() { return date_utc; }

    public String getSmallLogo() { return links.getPatch().getSmall(); }

    public List<String> getOriginalPictures() { return links.getFlickr().getOriginal(); }

}
