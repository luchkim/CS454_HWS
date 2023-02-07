import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class Dine {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of philosophers:");

        int size = scanner.nextInt();
        ArrayList<Thread> philosophers = new ArrayList<>(size);
        Hashtable<Integer, Lock> chopsticks = new Hashtable<>(size);

        // instantiate Chopsticks
        for(int i = 0; i < size; i++) {
            chopsticks.put(i, new Chopstick(i));
        }

        for(int i = 0; i < size; i++) {
            // instantiate Philosopher with left and right Chopsticks.
            philosophers.add( new Philosopher(i, chopsticks.get(i), chopsticks.get((i+1) % size)) );
        }

        for(Thread t: philosophers) {
            t.start();
        }

        for(Thread t: philosophers) {
            t.join();
        }
    }
}
