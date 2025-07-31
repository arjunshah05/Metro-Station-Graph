import java.util.*;

public class MetroGraph {
    private final Map<String, List<Edge>> graph = new HashMap<>();
    private final Map<String, Integer> distanceMap = new HashMap<>();
    private final Map<String, String> parentMap = new HashMap<>();

    public void addConnection(String from, String to, int distance) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(new Edge(to, distance));
        graph.get(to).add(new Edge(from, distance)); // Undirected graph
    }

    public List<String> getShortestPath(String source, String destination) {
        runDijkstra(source);

        if (!distanceMap.containsKey(destination) || distanceMap.get(destination) == Integer.MAX_VALUE) {
            return null;
        }

        List<String> path = new ArrayList<>();
        String current = destination;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    public int getDistance(String station) {
        return distanceMap.getOrDefault(station, -1);
    }

    private void runDijkstra(String source) {
        distanceMap.clear();
        parentMap.clear();

        for (String station : graph.keySet()) {
            distanceMap.put(station, Integer.MAX_VALUE);
        }
        distanceMap.put(source, 0);

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            String u = current.station;

            for (Edge edge : graph.getOrDefault(u, new ArrayList<>())) {
                String v = edge.to;
                int newDist = distanceMap.get(u) + edge.weight;

                if (newDist < distanceMap.get(v)) {
                    distanceMap.put(v, newDist);
                    parentMap.put(v, u);
                    pq.offer(new Pair(v, newDist));
                }
            }
        }
    }

    private static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Pair {
        String station;
        int distance;

        Pair(String station, int distance) {
            this.station = station;
            this.distance = distance;
        }
    }
}
