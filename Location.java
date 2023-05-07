/* Location class for assignment */

public class Location {

    private float latitude;
    private float longitude;
    private String coordinates;

    public Location(float pLatitude, float pLongitude, String pCoordinates) {
        latitude = pLatitude;
        longitude = pLongitude;
        coordinates = pCoordinates;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getCoordinates() {
        return coordinates;
    }

    // no need for mutators yet ?
}
