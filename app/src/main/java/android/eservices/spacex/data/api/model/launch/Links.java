package android.eservices.spacex.data.api.model.launch;

import java.util.List;

public class Links {
    private Patch patch;
    private Flickr flickr;
    private String webcast;

    public class Flickr {
        private List<String> original;

        public List<String> getOriginal() {
            return original;
        }
    }

    public class Patch {
        private String small;

        public String getSmall() {
            return small;
        }
    }

    public Patch getPatch() {
        return patch;
    }

    public Flickr getFlickr() {
        return flickr;
    }

    public String getWebcast() { return webcast; }
}
