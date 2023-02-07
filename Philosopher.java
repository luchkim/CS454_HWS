import java.util.concurrent.locks.Lock;

public class Philosopher extends  Thread{
    private int id;
    private Lock leftChopstick;
    private Lock rightChopstick;
    public Philosopher(){ }

    public Philosopher(int id, Lock lc, Lock rc){
        this.id=id;


        this.leftChopstick = lc;
        this.rightChopstick = rc;
    }

    public void run() {
        while (true) {
            try{
                think();
                take();
                eat();
                down();
            }catch (InterruptedException e) {}

        }
    }

    public void eat() throws InterruptedException {
        Thread.sleep(50);
        System.out.println("Philosopher " + id + " is eating.");
    }


    private void think() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Philosopher " + id + " is thinking.");
    }


    public void take(){
        while(true){
            boolean isL = false;
            boolean isR = false;
            try{
                // both chopsticks are not Unique, so if it is locked, other cannot access it.
                isL = leftChopstick.tryLock();
                isR = rightChopstick.tryLock();
            }finally{
                if(isL && isR){
                    System.out.println("Philosopher " + id + " Pick Up both Chopsticks.");
                    return;
                }
                if(isL){
                    System.out.println("Philosopher " + id + " Left  is free, Right is busy.");
                    // avoid deadlock
                    leftChopstick.unlock();
                }
                if(isR){
                    System.out.println("Philosopher " + id + " Right is free, Left is busy.");
                    // avoid deadlock
                    rightChopstick.unlock();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void down(){
        leftChopstick.unlock();
        rightChopstick.unlock();
        System.out.println("Philosopher " + id + " frees Both ChopSticks");
    }



}
