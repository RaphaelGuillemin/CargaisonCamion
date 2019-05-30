public class Coordinates {
    private double latitude; // North-south position of a point on the Earth's surface
    private double longitude; // East-west position of a point on the Earth's surface
    private static final int EARTH_RADIUS = 6371000;

    public Coordinates (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Returns the distance between two geographic coordinates, calculated with the Haversine formula
    public double distanceTo (Coordinates other) {
        double lat1 = this.getLatitude();
        double lat2 = other.getLatitude();
        double long1 = this.getLongitude();
        double long2 = other.getLongitude();

        return 2 * EARTH_RADIUS * Math.asin(Math.sqrt(Math.pow(Math.sin((lat2 - lat1) / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((long2 - long1) / 2), 2)));
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
