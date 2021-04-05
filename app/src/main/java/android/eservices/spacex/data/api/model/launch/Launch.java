package android.eservices.spacex.data.api.model.launch;

import android.eservices.spacex.data.api.model.rocket.Rocket;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Launch implements Serializable {
    private Cores[] cores;
    private Date date_utc;
    private String rocket;
    private String details;
    private String name;
    private Links links;

    public String getId() {
        return cores[0].getId();
    }

    public String getRocket() {
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

    public String getLargeLogo() { return links.getPatch().getLarge(); }

    public String getOriginalPicture() {
        if (links.getFlickr().getOriginal().size() > 0) {
            return links.getFlickr().getOriginal().get(0);
        } return getLargeLogo();
    }
}
