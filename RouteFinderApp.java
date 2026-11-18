import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * RouteFinderApp.java
 * Route Finding Navigation System - by Urvashi Singh
 *
 * Front-end: Java Swing
 * Back-end / DSA: Graph (Adjacency List) + Dijkstra's Algorithm
 *
 * How it works:
 * - User selects Source and Destination
 * - Clicks "Find Shortest Route"
 * - Shortest path is shown in text and highlighted on the map
 */
public class RouteFinderApp extends JFrame {

    private Graph graph;
    private JComboBox<String> sourceBox, destBox;
    private JTextArea resultArea;
    private MapPanel mapPanel;

    // Fixed positions of places on the map (x, y) - only for drawing
    private Map<String, Point> positions = new HashMap<>();

    public RouteFinderApp() {
        setTitle("Route Finding Navigation System - Urvashi Singh");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        buildGraph();

        // ---------- Top Panel ----------
        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(new Color(33, 37, 41));
        JLabel title = new JLabel("Route Finding Navigation System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        top.add(title);
        add(top, BorderLayout.NORTH);

        // ---------- Left Control Panel ----------
        JPanel control = new JPanel();
        control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));
        control.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        control.setPreferredSize(new Dimension(280, 0));

        List<String> places = new ArrayList<>(graph.getPlaces());
        Collections.sort(places);

        control.add(new JLabel("From (Source):"));
        sourceBox = new JComboBox<>(places.toArray(new String[0]));
        control.add(sourceBox);
        control.add(Box.createVerticalStrut(15));

        control.add(new JLabel("To (Destination):"));
        destBox = new JComboBox<>(places.toArray(new String[0]));
        destBox.setSelectedIndex(1);
        control.add(destBox);
        control.add(Box.createVerticalStrut(20));

        JButton findBtn = new JButton("Find Shortest Route");
        findBtn.setBackground(new Color(25, 135, 84));
        findBtn.setForeground(Color.WHITE);
        findBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        control.add(findBtn);
        control.add(Box.createVerticalStrut(10));

        JButton resetBtn = new JButton("Clear");
        control.add(resetBtn);
        control.add(Box.createVerticalStrut(20));

        resultArea = new JTextArea(10, 22);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setText("Select source and destination,\nthen click Find Shortest Route.");
        control.add(new JScrollPane(resultArea));

        add(control, BorderLayout.WEST);

        // ---------- Map Panel ----------
        mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);

        // ---------- Button Actions ----------
        findBtn.addActionListener(e -> findRoute());
        resetBtn.addActionListener(e -> {
            resultArea.setText("Select source and destination,\nthen click Find Shortest Route.");
            mapPanel.setHighlightPath(new ArrayList<>());
        });
    }

    // Lucknow city map - 10 places, simple and easy to explain
    private void buildGraph() {
        graph = new Graph();

        // positions for drawing: x (0-100), y (0-100)
        positions.put("Chowk",        new Point(15, 35));
        positions.put("Aminabad",     new Point(35, 45));
        positions.put("Hazratganj",   new Point(55, 40));
        positions.put("Charbagh",     new Point(40, 70));
        positions.put("Alambagh",     new Point(30, 88));
        positions.put("Rajajipuram",  new Point(12, 70));
        positions.put("Aliganj",      new Point(50, 15));
        positions.put("Jankipuram",   new Point(35, 8));
        positions.put("Gomti Nagar",  new Point(82, 35));
        positions.put("Indira Nagar", new Point(78, 12));

        // Roads with distance in km (approx)
        graph.addRoad("Hazratganj", "Aminabad", 2);
        graph.addRoad("Hazratganj", "Charbagh", 4);
        graph.addRoad("Hazratganj", "Gomti Nagar", 6);
        graph.addRoad("Hazratganj", "Aliganj", 5);
        graph.addRoad("Aminabad", "Chowk", 3);
        graph.addRoad("Aminabad", "Charbagh", 3);
        graph.addRoad("Chowk", "Rajajipuram", 5);
        graph.addRoad("Charbagh", "Alambagh", 4);
        graph.addRoad("Alambagh", "Rajajipuram", 6);
        graph.addRoad("Aliganj", "Jankipuram", 3);
        graph.addRoad("Aliganj", "Indira Nagar", 4);
        graph.addRoad("Jankipuram", "Rajajipuram", 7);
        graph.addRoad("Jankipuram", "Indira Nagar", 5);
        graph.addRoad("Indira Nagar", "Gomti Nagar", 4);
    }

    private void findRoute() {
        String source = (String) sourceBox.getSelectedItem();
        String dest = (String) destBox.getSelectedItem();

        if (source.equals(dest)) {
            resultArea.setText("Source and Destination are same.\nPlease choose different places.");
            mapPanel.setHighlightPath(new ArrayList<>());
            return;
        }

        Graph.Result result = graph.findShortestPath(source, dest);

        if (result.path.isEmpty()) {
            resultArea.setText("No route found between\n" + source + " and " + dest);
            mapPanel.setHighlightPath(new ArrayList<>());
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Shortest Route:\n");
        sb.append(String.join("  ->  ", result.path));
        sb.append("\n\nTotal Distance: ").append(result.totalDistance).append(" km");
        sb.append("\nTotal Stops: ").append(result.path.size());
        resultArea.setText(sb.toString());

        mapPanel.setHighlightPath(result.path);
    }

    // Panel that draws the map
    class MapPanel extends JPanel {
        private List<String> highlight = new ArrayList<>();

        void setHighlightPath(List<String> path) {
            this.highlight = path;
            repaint();
        }

        private Point toPixel(Point p) {
            int x = (int) (p.x / 100.0 * (getWidth() - 80)) + 40;
            int y = (int) (p.y / 100.0 * (getHeight() - 80)) + 40;
            return new Point(x, y);
        }

        private boolean isHighlighted(String a, String b) {
            for (int i = 0; i + 1 < highlight.size(); i++) {
                if ((highlight.get(i).equals(a) && highlight.get(i + 1).equals(b)) ||
                    (highlight.get(i).equals(b) && highlight.get(i + 1).equals(a))) return true;
            }
            return false;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            setBackground(Color.WHITE);

            // Draw all roads
            Set<String> drawn = new HashSet<>();
            for (String from : graph.getPlaces()) {
                for (Graph.Edge e : graph.getMap().get(from)) {
                    String key = from.compareTo(e.to) < 0 ? from + "-" + e.to : e.to + "-" + from;
                    if (!drawn.add(key)) continue;
                    Point p1 = toPixel(positions.get(from));
                    Point p2 = toPixel(positions.get(e.to));
                    if (isHighlighted(from, e.to)) {
                        g2.setColor(new Color(25, 135, 84));
                        g2.setStroke(new BasicStroke(4));
                    } else {
                        g2.setColor(Color.LIGHT_GRAY);
                        g2.setStroke(new BasicStroke(2));
                    }
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                    // distance label in middle
                    g2.setColor(Color.DARK_GRAY);
                    g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
                    g2.drawString(e.distance + " km", (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
                }
            }

            // Draw places
            for (String place : graph.getPlaces()) {
                Point p = toPixel(positions.get(place));
                boolean inPath = highlight.contains(place);
                g2.setColor(inPath ? new Color(25, 135, 84) : new Color(13, 110, 253));
                g2.fillOval(p.x - 10, p.y - 10, 20, 20);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.BOLD, 12));
                g2.drawString(place, p.x - 30, p.y - 16);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RouteFinderApp().setVisible(true));
    }
}
