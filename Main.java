import java.util.*;

public class Main {
    public static void main(String[] args) {
        MetroGraph metro = new MetroGraph();

        // Sample metro connections
        metro.addConnection("Rajiv Chowk", "Kashmere Gate", 6);
        metro.addConnection("Rajiv Chowk", "Central Secretariat", 4);
        metro.addConnection("Kashmere Gate", "Civil Lines", 2);
        metro.addConnection("Civil Lines", "Vidhan Sabha", 3);
        metro.addConnection("Central Secretariat", "Udyog Bhawan", 2);
        metro.addConnection("Udyog Bhawan", "Lok Kalyan Marg", 3);

        System.out.print("Rajiv Chowk , Kashmere Gate ,Central Secretariat, Civil Lines, Vidhan Sabha ,Udyog Bhawan, Lok Kalyan Marg");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source station: ");
        String source = scanner.nextLine().trim();
        System.out.print("Enter destination station: ");
        String destination = scanner.nextLine().trim();

        List<String> path = metro.getShortestPath(source, destination);
        if (path == null || path.isEmpty()) {
            System.out.println("No path found between " + source + " and " + destination);
        } else {
            System.out.println("Shortest path: " + String.join(" → ", path));
            System.out.println("Total distance: " + metro.getDistance(destination) + " km");
        }
    }
}
