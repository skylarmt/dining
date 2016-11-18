import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher extends Thread {

    private Fork leftFork;
    private Fork rightFork;
    private String name;

    public Philosopher(int philNumber) {
        this.name = "Philosopher " + philNumber;
        leftFork = null;
        rightFork = null;
    }

    @Override
    public void run() {
    }

    public void think() throws InterruptedException{
        System.out.println(this.getName() + " is thinking...");
        this.wait(10000);
    }

    public void eat() throws InterruptedException{
        System.out.println(this.getName() + " is eating...");
        this.wait(10000);
        
    }

    public String getPhilName(){
        return this.name;
    }
}

