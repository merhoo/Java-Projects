
public class MoodSensor {
    
    public static void main(String[] args) {
        System.out.println("--- Mood Sensor v1.0 ---");
        System.out.println("Your true emotions are coming across my screen.");
        System.out.println("At this moment, you are...");
        System.out.println();
        int random = getRandomInt(0,3);
        if (random == 0) {
            caseOne();
        } else if (random == 1) {
            caseTwo();
        } else if (random == 2) {
            caseThree();
        } else {
            caseFour();
        }
            
    }
    public static int getRandomInt(int a, int b) {
        return (a + (int)(Math.random()*(b- a +1)));

    }
    public static void caseOne() {
        System.out.println("      HAPPY!");
        System.out.println("     -------");
        System.out.println("    |       |");
        System.out.println("    | O   O |");
        System.out.println("    |   <   |");
        System.out.println("    | .   . |");
        System.out.println("    |  ...  |");
        System.out.println("     -------");
    }
    
    public static void caseTwo() {
        System.out.println("     Neutral");
        System.out.println("     -------");
        System.out.println("    |       |");
        System.out.println("    | O   O |");
        System.out.println("    |   <   |");
        System.out.println("    |  ___  |");
        System.out.println("    |       |");
        System.out.println("     -------");
    }
    
    public static void caseThree() {
        System.out.println("       Sad");
        System.out.println("     -------");
        System.out.println("    |       |");
        System.out.println("    | O   O |");
        System.out.println("    |   <   |");
        System.out.println("    |  ...  |");
        System.out.println("    | '   ' |");
        System.out.println("     -------");
    }
    
    public static void caseFour() {
        System.out.println("...yikes, in a really bad mood!!! Cheer up!");
    }
}
