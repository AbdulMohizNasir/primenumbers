package com.example.primenumbers.dto;

public class PrimeRequestDTO {
    private int number;
    private int algo= 1;

    // Default constructor
    public PrimeRequestDTO() {}

    // Constructor with parameter
    public PrimeRequestDTO(int number, int algo) {
        this.number = number;
        this.algo = algo;
    }

    // Getter
    public int getNumber() {
        return this.number;
    }

    // Setter
    public void setNumber(int number) {
        this.number = number;
    }
    //Getter
    public int getAlgo() {
        return algo;
    }
    //Setter
    public void setAlgo(int algo) {
        this.algo = algo;
    }
}
