package android.eservices.spacex.data.api.model;

import java.io.Serializable;

public class Rocket implements Serializable {
    private int id;
    private int height;
    private int diameter;
    private int mass;
    private int engines;
    private String name;
    private String description;
    private int Cost_per_launch;
    private int success_rate;
    private String first_flight;
    //Check pour les photos
    //Check pour le payload

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getMass() {
        return mass;
    }

    public int getEngines() {
        return engines;
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
}
