package android.eservices.spacex.data.api.model.rocket;

import java.io.Serializable;

public class Engines implements Serializable {
    private int number;
    private String type;

    public int getNumber() { return number; }

    public String getType() { return type; }
}
