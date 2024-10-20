package com.example.primenumbers;

import com.example.primenumbers.dto.PrimeRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumberIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPrimeGetEndpoint() throws Exception {
        mockMvc.perform(get("/primes/get")
                        .param("number", "10")
                        .param("algo", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primes").isArray())
                .andExpect(jsonPath("$.primes").value(containsInAnyOrder(2, 3, 5, 7)));
    }

    @Test
    void testPrimesPostEndpoint() throws Exception {
        PrimeRequestDTO request = new PrimeRequestDTO(10, 1);

        mockMvc.perform(post("/primes/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"number\": 10, \"algo\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primes").isArray())
                .andExpect(jsonPath("$.primes").value(containsInAnyOrder(2, 3, 5, 7)));
    }

    @Test
    void testPrimesPostInvalidNumber() throws Exception {
        mockMvc.perform(post("/primes/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"number\": -10, \"algo\": 1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Number must be positive."));
    }
}
