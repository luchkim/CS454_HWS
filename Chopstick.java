import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock {
    private int id;
    public Chopstick(int id){
        this.id = id;
    }
    public Chopstick(){}
}
