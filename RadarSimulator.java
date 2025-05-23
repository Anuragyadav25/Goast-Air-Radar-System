// Add this import if needed
import java.util.*;

public class RadarSimulator {
    private List<Aircraft> aircraftList;
    private Random rand = new Random();

    public RadarSimulator() {
        aircraftList = new ArrayList<>();

        // Sample aircraft names
        String[] aircraftNames = {"Boeing 737", "Airbus A320", "Boeing 787", "Embraer 190", "Cessna 172"};

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(600) + 100;
            int y = rand.nextInt(600) + 100;
            int dx = rand.nextInt(5) - 2;
            int dy = rand.nextInt(5) - 2;

            String flightNumber = "FL" + (100 + i);
            String airline = "AirSim";
            String origin = "City" + (i + 1);
            String destination = "Hub" + (i + 1);
            double altitude = 30000 + rand.nextInt(10000);
            String type = i % 2 == 0 ? "Passenger" : "Cargo";
            String status = "En Route";

            // ✅ New fields
            String name = aircraftNames[i % aircraftNames.length];
            double duration = 1.0 + rand.nextDouble() * 10.0;

            // ✅ Pass all 13 arguments to match constructor
            aircraftList.add(new Aircraft(
                    x, y, dx, dy,
                    flightNumber, airline, origin, destination,
                    altitude, type, status,
                    name, duration
            ));
        }
    }

    public void update() {
        for (Aircraft ac : aircraftList) {
            ac.move();
        }
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }
}
