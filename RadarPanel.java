import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RadarPanel extends JPanel {
    private RadarSimulator simulator;
    private Timer timer;
    private Rectangle restrictedZone = new Rectangle(350, 350, 100, 100);

    public RadarPanel() {
        simulator = new RadarSimulator();
        timer = new Timer(100, e -> {
            simulator.update();
            repaint();
        });
        timer.start();
    }

    public void startSimulation() {
        if (!timer.isRunning()) timer.start();
    }

    public void stopSimulation() {
        if (timer.isRunning()) timer.stop();
    }

    public void resetSimulation() {
        simulator = new RadarSimulator();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRadar(g);
        drawAircraft(g);
    }

    private void drawRadar(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.GREEN);
        for (int radius = 100; radius <= 300; radius += 50) {
            g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        }
        g.drawLine(centerX, 0, centerX, getHeight());
        g.drawLine(0, centerY, getWidth(), centerY);

        g.setColor(Color.RED);
        g.drawRect(restrictedZone.x, restrictedZone.y, restrictedZone.width, restrictedZone.height);
    }

    private void drawAircraft(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Aircraft aircraft : simulator.getAircraftList()) {
            int x = aircraft.getX();
            int y = aircraft.getY();

            g2d.setColor(Color.LIGHT_GRAY);
            List<Point> path = aircraft.getPath();
            for (int i = 1; i < path.size(); i++) {
                Point p1 = path.get(i - 1);
                Point p2 = path.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

            g2d.setColor(Color.RED);
            g2d.fillOval(x - 5, y - 5, 10, 10);
            g2d.drawString("ID: " + aircraft.getId(), x + 10, y);
            g2d.drawString(String.format("Vel: %.2f", aircraft.getVelocity()), x + 10, y + 15);
            g2d.drawString("Dir: " + aircraft.getDirection(), x + 10, y + 30);

            if (restrictedZone.contains(x, y)) {
                g2d.setColor(Color.ORANGE);
                g2d.drawString("ALERT!", x, y - 10);
                SoundPlayer.playAlertSound();
            }
        }
    }
}