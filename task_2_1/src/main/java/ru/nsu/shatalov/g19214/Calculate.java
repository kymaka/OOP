package ru.nsu.shatalov.g19214;

import java.util.concurrent.Callable;

public class Calculate implements Callable {
    long n;

    public Calculate(long n) {
        this.n = n;
    }

    public Object call() {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (long i = 5; i * i <= n; i+= 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
