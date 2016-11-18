public class Main 
{

    public static Fork[] forks;
    public static Philosopher[] philosophers;
    public static final int NUM_PHILOSOPHERS = 5;
    public static final int MAX_NUM_ROUNDS = 20;
    
    public static void main(String[] args) {
        forks = new Fork[5];
        philosophers = new Philosopher[5];

        //initializing all the forks and philosophers
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
            philosophers[i] = new Philosopher(i);
        }

        int rounds = 0;
        while(MAX_NUM_ROUNDS>rounds){
            for(int i = 0; i < NUM_PHILOSOPHERS; i++){
                try{
                    philosophers[i].think();
                }
                catch(Exception InterruptedException){}

                forks[Math.min(i, (i+1)%5)].take();
                forks[Math.max(i, (i+1)%5)].take();
                
                try{
                    philosophers[i].eat();
                
                    forks[Math.min(i, (i+1)%5)].release();
                    forks[Math.max(i, (i+1)%5)].release();
                }
                catch(Exception InterruptedException){}
                
                rounds ++;
            }
        }
    }
}
