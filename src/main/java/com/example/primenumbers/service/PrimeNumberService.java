package com.example.primenumbers.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrimeNumberService {

    // Original algorithm (e.g., Sieve of Eratosthenes)
    private List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            prime[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) primes.add(i);
        }

        return primes;
    }

    // New algorithm (e.g., Trial Division)
    private List<Integer> trialDivision(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) primes.add(i);
        }
        return primes;
    }

    // Main method to choose the algorithm based on `algo`
    public List<Integer> getPrimesUpTo(int n, int algo) {
        if (algo == 2) {
            return trialDivision(n);
        } else {
            return sieveOfEratosthenes(n);
        }
    }
}
