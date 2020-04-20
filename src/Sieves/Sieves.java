package Sieves;

import java.util.ArrayList;
import java.util.BitSet;

public class Sieves {
    public Sieves() {
        long startTime = System.nanoTime();
        int length = primeSieve(1_000_000_000).length;
        long endTime = System.nanoTime() - startTime;
        System.out.println(length + " in " + endTime / 1_000_000);
    }

    private long[] primeSieveBitSetBetter(int n) {
        BitSet sieve = new BitSet(n / 2);
        double root = Math.sqrt(n);

        for (int i = 3; i <= root + 1; i += 2) {
            for (int j = i * i / 2; j <= n / 2; j += i) {
                sieve.set(j);
            }
        }

        long[] primes = new long[sieve.length() - sieve.cardinality()];
        primes[0] = 2;
        for (int j = 1, i = sieve.nextClearBit(1); j < primes.length; j++, i = sieve.nextClearBit(i + 1)) {
            primes[j] = i * 2 + 1;
        }
        return primes;
    }

    private long[] primeSieveBitSet(int n) {
        BitSet sieve = new BitSet(n + 1);
        sieve.set(0);
        sieve.set(1);
        double root = Math.sqrt(n);

        for (int i = 4; i <= n; i += 2) sieve.set(i);

        for (int i = 3; i <= root; i += 2) {
            for (int j = i * i; j <= n; j += i << 1) {
                sieve.set(j);
            }
        }

        long[] primes = new long[sieve.length() - sieve.cardinality()];
        for (int j = 0, i = sieve.nextClearBit(0); j < primes.length; j++, i = sieve.nextClearBit(i + 1)) {
            primes[j] = i;
        }
        return primes;
    }

    private Integer[] primeSieve(int n) {
        boolean[] sieve = new boolean[n + 1];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        int root = (int) Math.sqrt(n);
        for (int i = 3; i <= root; i += 2) {
            if (!sieve[i]) {
                primes.add(i);
                for (int j = i * i; j < sieve.length; j += i << 1) {
                    sieve[j] = true;
                }
            }
        }
        for (int i = (root & 1) == 0 ? root + 1 : root + 2; i < n; i += 2) {
            if (!sieve[i]) primes.add(i);
        }
        return primes.toArray(new Integer[0]);
    }

    private Integer[] primeSieveBetterMemory(int n) {
        boolean[] sieve = new boolean[n / 2 + 1];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        int root = (int) Math.sqrt(n);
        for (int i = 3; i <= root + 1; i += 2) {
            if (!sieve[i]) {
                primes.add(i);
                for (int j = i * i; j < sieve.length; j += i + i) {
                    sieve[j] = true;
                }
            }
        }
        for (int i = (root & 1) == 0 ? root + 1 : root + 2; i < n; i += 2) {
            if (!sieve[i]) primes.add(i);
        }
        return primes.toArray(new Integer[0]);
    }
}
