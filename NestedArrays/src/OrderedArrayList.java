import java.awt.List;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;


public class OrderedArrayList {
    public final List<String> stringList;
    
    public OrderedArrayList(List<String> stringList) {
        stringList = new ArrayList<String>();
    }
    public static void insertInOrder(String s) {
        int i = 0;
        for (int n= stringList.size(); i < n; i++) {
            if (stringList.get(i).compareToIgnoreCase(s) > 0) {
                break;
            }
               
        }
        stringList.add(i, s);
    }
    public void display() {
        Iterator<String> iter = stringList.iterator();
        while (iter.hasNext()) {
            System.out.println("- " + iter.next());
        }
        
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        OrderedArrayList orderedAl = new OrderedArrayList();
    }
}
