package android.eservices.spacex.data.api.model.launch;

import java.io.Serializable;
import java.util.List;

public class Links implements Serializable {
    private Patch patch;
    private Flickr flickr;
    private String youtube_id;

    public class Flickr implements Serializable {
        private List<String> original;

        public List<String> getOriginal() { return original; }
    }

    public class Patch implements Serializable {
        private String small;
        private String large;

        public String getSmall() {
            return small;
        }

        public String getLarge() { return large; }
    }

    public Patch getPatch() {
        return patch;
    }

    public Flickr getFlickr() {
        return flickr;
    }

    public String getYoutube_id() { return youtube_id; }
}
