package android.eservices.spacex.data.api.model.rocket;

import java.io.Serializable;
import java.util.List;

public class Rocket implements Serializable {
    private String id;
    private Height height;
    private Diameter diameter;
    private Mass mass;
    private Engines engines;
    private String name;
    private String description;
    private int Cost_per_launch;
    private int success_rate;
    private String first_flight;
    private List<String> flickr_images;

    public String getId() {
        return id;
    }

    public double getHeight() {
        return height.getMeters();
    }

    public double getDiameter() { return diameter.getMeters(); }

    public double getMass() {
        return mass.getKg();
    }

    public double getEngines() {
        return engines.getNumber();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost_per_launch() {
        return Cost_per_launch;
    }

    public int getSuccess_rate() {
        return success_rate;
    }

    public String getFirst_flight() { return first_flight; }

    public List<String> getFlickr_images() { return flickr_images; }
}
