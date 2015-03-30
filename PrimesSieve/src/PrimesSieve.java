import java.util.Scanner;

public class PrimesSieve {
    private boolean[] isPrime;
    private int limit, maxPrime;
    
    public PrimesSieve(int limit) {
        this.limit = limit;  
        for (int k = 2; k <= isPrime.length + 1; k++) {
            isPrime[k] = true;
        }
        for (int i = 2; i <= Math.sqrt(input); i++) {
            if (isPrime[i] == true) {
                for (int j = 2*i; j <= limit; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    public int countNumberOfPrimes() {
        int k = 0;
        for (int j = 2; j <= isPrime.length; j++) {
            if (isPrime[j] == true) {
                k++;
            }
        } 
        return k;
    }
    public void displayPrimesss() {
        int maxPrimeWidth = String.valueOf(maxPrime).length();
        int primesPerRow = 80 / (maxPrimeWidth + 1);
        int placeholder = 0;
        String string = "";
        for (int i = 2; i <= maxPrime; i++ ) {
            if (isPrime[i] == true) {
                if (String.valueOf(i).length() < maxPrimeWidth) {
                    
                } else {
                    String j = String.valueOf(i);
                    string += j;
                    string += " ";
                }
            }
        }
    }
    public static void main(String[] args) {
        //System.out.println("**************************** Sieve of Eratosthenes ***************************");
        Scanner input = new Scanner(System.in);
        System.out.print("Search for primes up to: ");
        int primes = input.nextInt();
        boolean[] isPrime = new boolean[primes + 1];
        for (i = )
        }
        //call countNumberOfPrimes
        //System.out.println(Number of Primes found: " + primecount);
        System.out.println("Primes up to " + primes + ":");
        
    }
}
