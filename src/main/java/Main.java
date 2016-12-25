public class Main {

    public static void main(String[] args) {
	    final Foo foo = new Foo();

        // Try with 100 method calls for each thread
        Thread t1 = new Thread(() -> {for(int i=0; i<100; i++) foo.first();}, "A");
        Thread t2 = new Thread(() -> {for(int i=0; i<100; i++) foo.second();}, "B");
        Thread t3 = new Thread(() -> {for(int i=0; i<100; i++) foo.third();}, "C");

        t3.start();
        t2.start();
        t1.start();

        // Wait until all threads are done
        try {
            t1.join();
            t2.join();
            t3.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

        System.out.println("Program finished !");
    }
}
