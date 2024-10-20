package com.example.primenumbers.dto;

import java.util.List;

public class PrimeResponseDTO {

    private List<Integer> primes;

    // Constructor
    public PrimeResponseDTO(List<Integer> primes) {
        this.primes = primes;
    }

    // Getter
    public List<Integer> getPrimes() {
        return primes;
    }

    // Setter
    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }
}
