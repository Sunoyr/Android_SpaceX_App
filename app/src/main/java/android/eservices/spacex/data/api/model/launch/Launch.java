package android.eservices.spacex.data.api.model.launch;

import android.eservices.spacex.data.api.model.rocket.Rocket;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Launch implements Serializable {
    private Cores cores;
    private Date date_utc;
    private Rocket rocket;
    private String details;
    private String name;
    private Links links;

    public String getId() {
        return cores.getId();
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
        return links.getWebcast();
    }

    public Date getDate_utc() { return date_utc; }

    public String getSmallLogo() { return links.getPatch().getSmall(); }

    public List<String> getOriginalPictures() { return links.getFlickr().getOriginal(); }
}
