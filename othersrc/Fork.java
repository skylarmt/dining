// http://tutorials.jenkov.com/java-concurrency/semaphores.html

public class Fork {

    private boolean signal = false;
    private String name;

    public Fork(int forkNum){
        this.name = "Fork " + forkNum;  
    }

    public synchronized void take(){
        this.signal = true;
        this.notify();
    }

    public synchronized void release() throws InterruptedException{
        while(!this.signal) wait(1000);
        this.signal = false;
    }
}
