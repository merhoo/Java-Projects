import java.util.Scanner;

public class Sound {
    private int samples[];
    private static Scanner input = new Scanner(System.in);
    
    public Sound(int[] samples){
        this.samples = new int[samples.length];
        for (int i = 0; i < samples.length; i++) {
            this.samples[i] = samples[i];
        }
    }
    
    public void displaySamples(){
        System.out.print("[");
        for (int i = 0; i<samples.length; i++) {
            if (i == 0) {
                System.out.print(samples[i]);
            } else {
                System.out.print(", " + samples[i]);
            }
        }
        System.out.println("]");
    }
    
    public int limitAmplitude(int limit){
        
        int count = 0;

        for (int i = 0; i < samples.length; i++) {
          if (samples[i] > limit) {
            samples[i] = limit;
            count++;
          } else if (samples[i] < -limit) {
            samples[i] = -limit;
            count++;
          }
          
        }
        return count;
    }
    
    public void trimSilenceFromBeginning(){
        int n = 0;
        while (samples[n] == 0){
            n++;
        }

        int[] newSamples = new int[samples.length - n];

        for (int i = 0; i < newSamples.length; i++) {
            newSamples[i] = samples[i+n];
        }

        samples = newSamples;
    }
    
    public static int[] readIntArrayFromKeyboard(String[] prompt){
        for (int i = 0; i<prompt.length; i++) {
            try {
                Integer.parseInt(prompt[i]);
            } catch(NumberFormatException nfe) {
                System.err.println("Error: Contains non-integer value.");
                System.exit(1);
            }
        }
        int[] array = new int[prompt.length];
        for (int i = 0; i<prompt.length; i++) {
            array[i] = Integer.parseInt(prompt[i]);
        }
        return array;
    }
    
    public static int readIntFromKeyboard(String prompt){
        int limit = 0; 
        try {
            limit = Integer.parseInt(prompt);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: Contains non-integer value.");
            System.exit(1);
        }
        return limit;
    }
    
    public static void main(String[] args){
        System.out.println("2011 AP Computer Science A Exam, Free Response Question 1");
        System.out.println();
        System.out.print("Enter sample values: ");
        String[] userInput = (input.nextLine()).split(" ");
        int[] intUserInput = readIntArrayFromKeyboard(userInput);
        System.out.print("Enter limit: ");
        System.out.println();
        Sound s = new Sound(intUserInput);
        System.out.print("Initial samples: ");
        s.displaySamples();
        
        
    }
}
