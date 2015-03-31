import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NearestNeighbors {
    private final ArrayList<Neighbor> neighborList;
    
    public NearestNeighbors(String filename) throws IOException {
        Scanner input = null;
        neighborList = new ArrayList(1);
        try {
            input = new Scanner( new File(filename));
        } catch (IOException ioe) {
            System.err.println("Error: Cannot open '" + filename + "' for processing.");
            System.exit(1);
        }
        int line = 1;
        while (input.hasNext()) {
            String neighborStr = input.nextLine().trim();
            String[] neighbor = neighborStr.split(",");
            if (neighbor.length != 3) {
                throw new IOException("Error: Invalid format on line" + line + ".");
            } 
            float x = 0;
            try {
                x = Float.parseFloat(neighbor[1]);
            } catch (NumberFormatException nfe) {
                System.err.print("Error: Invald x-coordinate '" + neighbor[1] + "on line "+ line + ".");
                System.exit(1);
            } 
            float y = 0;
            try {
                y = Float.parseFloat(neighbor[2]);
            } catch (NumberFormatException nfe) {
                System.err.print("Error: Invald y-coordinate '" + neighbor[2] + "on line "+ line + ".");
                System.exit(1);
            }
            Neighbor n = new Neighbor(neighbor[0], x, y);
            this.neighborList.add(n);
            line++;
            
        }
        
        input.close();
    }
    public void displayNeighbors() {
        System.out.println("Neighbors:");
        for (int i = 0; i < 3; i++) {
            Neighbor neighbor = neighborList.get(i);
            System.out.print("  ");
            System.out.println(neighbor.toString());
        }
        System.out.println();
    }
    public Neighbor[] findNearestPath() {
        float min = Float.MAX_VALUE, distance = 0;
        ArrayList<Neighbor> close = new ArrayList(0);
        for (int i = 0; i< neighborList.size(); i++){ 
            for(int j = i+1; j< neighborList.size();j++){
                distance = NearestNeighbors.distance(neighborList.get(i), neighborList.get(j));
                if (distance < min){
                    min = distance;
                    close.clear();
                    close.add(neighborList.get(i));
                    close.add(neighborList.get(j));
                }
                if(distance == min){
                    close.add(neighborList.get(j));
                }
            }
        }
        Neighbor[] nearestNeighbor = new Neighbor[close.size()];
        for(int i = 0; i < close.size(); i++){
            nearestNeighbor[i] = close.get(i);
        }
        return nearestNeighbor;
    }
    public static float distance(Neighbor n1, Neighbor n2) {
        return distance(n1.x, n1.y, n2.x, n2.y);
    }
    public static float distance(float x1, float y1, float x2, float y2) {
        float deltaX = Math.abs(x1 - x2);
        float deltaY = Math.abs(y1 - y2);
        float distance = (float)Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance;
    }
    public static void main(String[] args) {
        if (!(args.length == 1)) {
            System.err.println("Usage: java NearestNeighbors <input file>");
            System.exit(1);
        }
        NearestNeighbors nei = null;
        try {
            nei = new NearestNeighbors(args[0]);
        } catch (IOException ioe) {
            System.err.println("Error: Cannot open '" + args[0] + "' for processing.");
        }
        nei.displayNeighbors();
        System.out.println("Closest neighbors:");
        Neighbor[] close = nei.findNearestPath();
        Neighbor n = null;
        for (int i = 0; i <close.length; i++) {
            if (close[i] != n) {
                System.out.println("   " + close[i].toString());
            } n = close[i];
        } System.out.println();
        System.out.printf("Distance: %.3f", distance(close[0].x, close[0].y, close[1].x, close[1].y));
    }
}
class Neighbor {
    String name;
    float x, y;
    
    public Neighbor(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return name + ": (" + x + ", " + y + ")";
    }
}

//trim removes white space