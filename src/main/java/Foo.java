/**
 * Created by amura on 12/25/16.
 */
public class Foo {
    private boolean isFirstRead = false;
    private boolean isSecondRead = false;

    /**
     * This method can only be performed when `firstRead` flag is false
     * It will wait indefinitely after acquire lock if pre-requisite is not met
     *
     * Upon success it will notify all threads on the `wait-set` so other thread may continue the progress.
     */
    public synchronized void first(){
//        System.out.println("TRY FIRST");
        while(isFirstRead){
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        isFirstRead = true;
        System.out.printf("%s: FIRST\n", Thread.currentThread().getName());
        notifyAll();
    }

    /**
     * This method can only be performed when `firstRead` flag is true and `secondRead` flag is false
     * It will wait indefinitely after acquire lock if pre-requisite is not met
     *
     * Upon success it will notify all threads on the `wait-set` so other thread may continue the progress.
     */
    public synchronized void second(){
//        System.out.println("TRY SECOND");
        while(!isFirstRead || isSecondRead){
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        isSecondRead = true;
        System.out.printf("%s: SECOND\n", Thread.currentThread().getName());
        notifyAll();
    }

    /**
     * This method can only be performed when `secondRead` flag is true
     * It will wait indefinitely after acquire lock if pre-requisite is not met
     *
     * Upon success it will notify all threads on the `wait-set` so other thread may continue the progress.
     */
    public synchronized void third(){
//        System.out.println("TRY THIRD");
        while(!isSecondRead){
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        // reset state
        isFirstRead = false;
        isSecondRead = false;
        System.out.printf("%s: THIRD\n", Thread.currentThread().getName());
        notifyAll();
    }
}