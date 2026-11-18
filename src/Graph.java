import java.util.*;

/**
 * Graph.java
 * Route Finding Navigation System - by Urvashi Singh
 *
 * Simple Graph using Adjacency List.
 * Places = Nodes, Roads = Edges, Distance (km) = Weight
 */
public class Graph {

    // One road connection
    static class Edge {
        String to;
        int distance; // in km

        Edge(String to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    // Adjacency List: Place Name -> List of connected roads
    private Map<String, List<Edge>> map = new HashMap<>();

    // Add a place (node)
    public void addPlace(String place) {
        map.putIfAbsent(place, new ArrayList<>());
    }

    // Add a road (undirected - road goes both ways)
    public void addRoad(String from, String to, int distance) {
        addPlace(from);
        addPlace(to);
        map.get(from).add(new Edge(to, distance));
        map.get(to).add(new Edge(from, distance));
    }

    public Set<String> getPlaces() {
        return map.keySet();
    }

    public Map<String, List<Edge>> getMap() {
        return map;
    }

    // Result of shortest path search
    public static class Result {
        public List<String> path;
        public int totalDistance;

        Result(List<String> path, int totalDistance) {
            this.path = path;
            this.totalDistance = totalDistance;
        }
    }

    /**
     * Dijkstra's Algorithm - finds shortest path from source to destination
     * Steps:
     * 1. Distance of source = 0, all others = Infinity
     * 2. Pick the unvisited place with smallest distance (PriorityQueue)
     * 3. Relax its neighbours - if a shorter way is found, update it
     * 4. Repeat till destination is reached
     */
    public Result findShortestPath(String source, String destination) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String place : map.keySet()) {
            dist.put(place, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        // Min-heap by distance
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(source);

        while (!pq.isEmpty()) {
            String current = pq.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            if (current.equals(destination)) break;

            for (Edge edge : map.getOrDefault(current, new ArrayList<>())) {
                int newDist = dist.get(current) + edge.distance;
                if (newDist < dist.get(edge.to)) {
                    dist.put(edge.to, newDist);
                    parent.put(edge.to, current);
                    pq.add(edge.to);
                }
            }
        }

        if (dist.get(destination) == Integer.MAX_VALUE) {
            return new Result(new ArrayList<>(), -1); // no route
        }

        // Build path by going back from destination using parent map
        List<String> path = new ArrayList<>();
        String step = destination;
        while (step != null) {
            path.add(step);
            step = parent.get(step);
        }
        Collections.reverse(path);
        return new Result(path, dist.get(destination));
    }
}
