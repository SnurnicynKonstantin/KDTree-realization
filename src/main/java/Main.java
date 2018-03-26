import structure.Geo;
import structure.KDTree;
import structure.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Main {
    private static int MAX = 100;
    private static int MIN = 0;

    public static void main(String[] args) {
        KDTree tree = new KDTree(MAX, MIN, getTestData());

        System.out.println("KDTree built");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                System.out.println("Enter X coordinate:");
                double x = Double.parseDouble(br.readLine());
                System.out.println("Enter Y coordinate:");
                double y = Double.parseDouble(br.readLine());
                System.out.println("Enter radius:");
                double radius = Double.parseDouble(br.readLine());
                Geo point = new Geo(x, y, null);
                List<Node> result = tree.near(point, radius);
                System.out.println("List of geos in point " + x + " : " + y + " with radius " + radius + ":");
                for (Node node : result) {
                    System.out.println(node.getGeo().getLatitude() + " : " + node.getGeo().getLongitude());
                }
                System.out.println("-----------------------------------------------------------------");
            } catch (IOException e) {
                System.out.print("Error: incorrect data.");
            }
        }
    }

    private static Hashtable<String, Geo> getTestData() {
        Hashtable ht = new Hashtable();
        Random r = new Random();
        int latitude;
        int longitude;
        String title;
        for(int i = 0; i < 60; i++) {
            latitude = (int) (MIN + (MAX - MIN) * r.nextDouble());
            longitude = (int) (MIN + (MAX - MIN) * r.nextDouble());
            title = "Shop" + i;
            ht.put(title, new Geo(latitude, longitude, title));
        }

        return ht;
    }
}
