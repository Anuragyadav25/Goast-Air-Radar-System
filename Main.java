import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Air Radar System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        RadarPanel radar = new RadarPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu controlMenu = new JMenu("Controls");
        JMenuItem start = new JMenuItem("Start");
        JMenuItem stop = new JMenuItem("Stop");
        JMenuItem reset = new JMenuItem("Reset");

        start.addActionListener(e -> radar.startSimulation());
        stop.addActionListener(e -> radar.stopSimulation());
        reset.addActionListener(e -> radar.resetSimulation());

        controlMenu.add(start);
        controlMenu.add(stop);
        controlMenu.add(reset);
        menuBar.add(controlMenu);

        frame.setJMenuBar(menuBar);
        frame.add(radar);
        frame.setVisible(true);
    }
}