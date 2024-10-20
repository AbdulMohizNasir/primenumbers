package com.example.primenumbers.controller;

import com.example.primenumbers.dto.PrimeRequestDTO;
import com.example.primenumbers.dto.PrimeResponseDTO;
import com.example.primenumbers.service.PrimeNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrimeNumberControllerTest {

    @InjectMocks
    private PrimeNumberController primeNumberController;

    @Mock
    private PrimeNumberService primeNumberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrimeGetValidInput() {
        when(primeNumberService.getPrimesUpTo(10, 1)).thenReturn(List.of(2, 3, 5, 7));

        ResponseEntity<?> response = primeNumberController.PrimeGet(10, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(2, 3, 5, 7), ((PrimeResponseDTO) response.getBody()).getPrimes());
    }

    @Test
    void testPrimeGetInvalidNumber() {
        ResponseEntity<?> response = primeNumberController.PrimeGet(-1, 1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Number must be positive.", response.getBody());
    }

    @Test
    void testPrimesPostValidInput() {
        PrimeRequestDTO requestDTO = new PrimeRequestDTO(10, 1);
        when(primeNumberService.getPrimesUpTo(10, 1)).thenReturn(List.of(2, 3, 5, 7));

        ResponseEntity<?> response = primeNumberController.PrimesPost(requestDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(2, 3, 5, 7), ((PrimeResponseDTO) response.getBody()).getPrimes());
    }

    @Test
    void testPrimesPostInvalidAlgo() {
        PrimeRequestDTO requestDTO = new PrimeRequestDTO(10, 3);
        ResponseEntity<?> response = primeNumberController.PrimesPost(requestDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Algo must be either 1 or 2.", response.getBody());
    }

    @Test
    void testPrimesPostNegativeNumber() {
        PrimeRequestDTO requestDTO = new PrimeRequestDTO(-5, 1);
        ResponseEntity<?> response = primeNumberController.PrimesPost(requestDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Number must be positive.", response.getBody());
    }

    @Test
    void testPrimesPostNegativeNumberAndInvalidAlgo() {
        PrimeRequestDTO requestDTO = new PrimeRequestDTO(-5, 3);
        ResponseEntity<?> response = primeNumberController.PrimesPost(requestDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Number must be positive. Algo must be either 1 or 2.", response.getBody());
    }
}
