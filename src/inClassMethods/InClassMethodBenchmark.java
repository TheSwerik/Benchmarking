package inClassMethods;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class InClassMethodBenchmark {

    private final int iterations = 10_000;
    private final int samples = 10;

    public InClassMethodBenchmark() {
        long[] inMethodTimes = new long[samples];
        long[] outMethodTimes = new long[samples];
        long[] outClassTimes = new long[samples];
        long[] outClassWVTimes = new long[samples];
        long[] outClassStaticTimes = new long[samples];

        //test outMethod
        for (int i = 0; i < samples / 2; i++) {
            long startTime = System.nanoTime();
            outMethodTimer();
            outMethodTimes[i] = System.nanoTime() - startTime;
        }

        //test inmethod
        for (int i = 0; i < samples / 2; i++) {
            inMethodTimes[i] = inMethodTimer();
        }

        //test outClass
        for (int i = 0; i < samples / 2; i++) {
            outClassTimes[i] = new OutClass().outClassTimes(iterations);
        }

        //test outClassWV
        OutClass OutClass = new OutClass();
        for (int i = 0; i < samples / 2; i++) {
            outClassWVTimes[i] = OutClass.outClassTimes(iterations);
        }

        //test outClassStatic
        for (int i = 0; i < samples / 2; i++) {
            outClassStaticTimes[i] = inClassMethods.OutClass.outClassStaticTimes(iterations);
        }

        //test outMethod 2
        for (int i = samples / 2; i < samples; i++) {
            long startTime = System.nanoTime();
            outMethodTimer();
            outMethodTimes[i] = System.nanoTime() - startTime;
        }

        //test inmethod 2
        for (int i = samples / 2; i < samples; i++) {
            inMethodTimes[i] = inMethodTimer();
        }

        //test outClass 2
        for (int i = samples / 2; i < samples; i++) {
            outClassTimes[i] = new OutClass().outClassTimes(iterations);
        }

        //test outClassWV 2
        OutClass OutClass2 = new OutClass();
        for (int i = samples / 2; i < samples; i++) {
            outClassWVTimes[i] = OutClass2.outClassTimes(iterations);
        }

        //test outClassStatic 2
        for (int i = samples / 2; i < samples; i++) {
            outClassStaticTimes[i] = inClassMethods.OutClass.outClassStaticTimes(iterations);
        }

        //return:
        System.out.println("Timer in Constructor:\t\t\t\t\t" + average(outMethodTimes) + "ns\t" + Arrays.toString(outMethodTimes));
        System.out.println("Timer in Method:\t\t\t\t\t\t" + average(inMethodTimes) + "ns\t" + Arrays.toString(inMethodTimes));
        System.out.println("Timer in Other Class' Method:\t\t\t" + average(outClassTimes) + "ns\t" + Arrays.toString(outClassTimes));
        System.out.println("Timer in Other Class' Method w/ var:\t" + average(outClassWVTimes) + "ns\t" + Arrays.toString(outClassWVTimes));
        System.out.println("Timer in Other Class' static Method:\t" + average(outClassStaticTimes) + "ns\t" + Arrays.toString(outClassStaticTimes));

    }

    private long inMethodTimer() {
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {

        }
        return System.nanoTime() - startTime;
    }

    private void outMethodTimer() {
        for (int i = 0; i < iterations; i++) {

        }
    }

    private long average(long[] arr) {
        long sum = 0;
        for (long l : arr) {
            sum += l;
        }
        return sum / arr.length;
    }
}
