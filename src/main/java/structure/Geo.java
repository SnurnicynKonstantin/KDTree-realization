package structure;

public class Geo {
    private final double latitude;
    private final double longitude;
    private final String title;

    public Geo(double latitude, double longitude, String title) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Boolean equals(Geo geo) {
        return latitude == geo.latitude && longitude == geo.longitude && title == geo.title;
    }
}
