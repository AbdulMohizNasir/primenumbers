package com.example.primenumbers.controller;

import com.example.primenumbers.dto.PrimeRequestDTO;
import com.example.primenumbers.dto.PrimeResponseDTO;
import com.example.primenumbers.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrimeNumberController {

    @Autowired
    private PrimeNumberService primeNumberService;

    // GET endpoint using a query parameter
    @GetMapping("/primes/get")
    public ResponseEntity<?> PrimeGet(@RequestParam("number") int number, @RequestParam(value = "algo", defaultValue = "1") int algo) {

        String errorMessage = "";


        try {
            if (number <= 0 && (algo != 1 && algo != 2) ) {
                errorMessage += "Number must be positive. Algo must be either 1 or 2. ";
            }

            else if (number <= 0  ) {
                errorMessage += "Number must be positive. ";
            }

            else if (algo != 1 && algo != 2 ) {
                errorMessage += " Algo must be either 1 or 2. ";
            }


            // If there are validation errors, throw an exception
            if (!errorMessage.isEmpty()) {
                throw new IllegalArgumentException(errorMessage.trim());
            }

            // If validations pass, get the prime numbers
            List<Integer> primes = primeNumberService.getPrimesUpTo(number, algo);
            return ResponseEntity.ok(new PrimeResponseDTO(primes));

        }  catch (IllegalArgumentException ex) {
            // Handle the exception by returning an error response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            // Handle any other exceptions that may arise
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }

    // POST endpoint using PrimeRequestDTO
    @PostMapping("/primes/post")
    public ResponseEntity<?> PrimesPost(@RequestBody PrimeRequestDTO input) {
        String errorMessage = "";

        try {
            int number = input.getNumber();
            int algo = input.getAlgo();

            if (number <= 0 && (algo != 1 && algo != 2) ) {
                errorMessage += "Number must be positive. Algo must be either 1 or 2. ";
            }

            else if (number <= 0  ) {
                errorMessage += "Number must be positive. ";
            }

            else if (algo != 1 && algo != 2 ) {
                errorMessage += " Algo must be either 1 or 2. ";
            }

            // If there are validation errors, throw an exception
            if (!errorMessage.isEmpty()) {
                throw new IllegalArgumentException(errorMessage.trim());
            }

            // If validations pass, get the prime numbers
            List<Integer> primes = primeNumberService.getPrimesUpTo(number, algo);
            return ResponseEntity.ok(new PrimeResponseDTO(primes));

        } catch (IllegalArgumentException ex) {
            // Handle the exception by returning an error response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            // Handle any other exceptions that may arise
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }

}
