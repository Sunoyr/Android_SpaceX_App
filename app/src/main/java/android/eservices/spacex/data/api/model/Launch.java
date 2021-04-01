package android.eservices.spacex.data.api.model;

import java.util.Date;

public class Launch {
    private int id;
    private Date date_utc;
    private Rocket rocket;
    private String details;
    private String name;
    private String webcast;
    //Check pour les photos


    public int getId() {
        return id;
    }

    public Date getDate() {
        return date_utc;
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
}
