package inClassMethods;

public class OutClass {
    public long outClassTimes(int iterations){
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {

        }
        return System.nanoTime() - startTime;
    }

    public static long outClassStaticTimes(int iterations){
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {

        }
        return System.nanoTime() - startTime;
    }
}
