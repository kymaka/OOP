package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.Callable;

public class App {

    long n;

    App(long n) {
        this.n = n;
    }

    public static Boolean call(Integer n) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isNonPrime(Integer n) {
        return !call(n);
    }
}
