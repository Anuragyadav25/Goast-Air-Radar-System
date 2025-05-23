import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class Aircraft {
    private static int idCounter = 0;
    private int id;
    private int x, y, dx, dy;
    private List<Point> path = new ArrayList<>();

    // Flight detail fields
    private String flightNumber;
    private String airline;
    private String origin;
    private String destination;
    private double altitude;
    private String type; // e.g., passenger, cargo
    private String status; // e.g., en route, landed

    // New fields
    private String name;     // Aircraft name/model (e.g., Airbus A320)
    private double duration; // Duration of flight in hours

    public Aircraft(int x, int y, int dx, int dy,
                    String flightNumber, String airline, String origin, String destination,
                    double altitude, String type, String status,
                    String name, double duration) {
        this.id = ++idCounter;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.altitude = altitude;
        this.type = type;
        this.status = status;
        this.name = name;
        this.duration = duration;
    }

    public void move() {
        path.add(new Point(x, y));
        x += dx;
        y += dy;
    }

    public int getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }
    public List<Point> getPath() { return path; }

    public double getVelocity() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String getDirection() {
        if (dx == 0 && dy == 0) return "Stationary";
        if (Math.abs(dx) > Math.abs(dy)) return dx > 0 ? "East" : "West";
        return dy > 0 ? "South" : "North";
    }

    // Getters for flight details
    public String getFlightNumber() { return flightNumber; }
    public String getAirline() { return airline; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public double getAltitude() { return altitude; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public String getName() { return name; }
    public double getDuration() { return duration; }

    // Formatted display
    public String getFlightInfo() {
        return "Flight " + flightNumber + " (" + airline + ")"
                + "\nAircraft: " + name + " | Type: " + type + " | Duration: " + duration + " hrs"
                + "\nFrom " + origin + " to " + destination
                + "\nAltitude: " + altitude + " ft | Status: " + status
                + "\nPosition: (" + x + ", " + y + ") | Direction: " + getDirection() + " | Speed: " + getVelocity();
    }

    @Override
    public String toString() {
        return getFlightInfo();
    }
}
