package com.devsu.customer.controller;

import com.devsu.customer.model.dto.ClientDto;
import com.devsu.customer.model.dto.PersonDto;
import com.devsu.customer.service.ClientService;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientService clientService;

    private ClientDto testClient;

    Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        // Initialize a test ClientDto object
        PersonDto personDto = PersonDto.builder()
                .name("Miguel")
                .gender("M")
                .address("Comas")
                .age(30)
                .phone("994320740")
                .identification("73386321")
                .build();
        testClient = ClientDto.builder()
                .password("123")
                .state(true)
                .person(personDto)
                .build();
    }


    @Test
    void shouldSaveClient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType("application/json")
                        .content(gson.toJson(testClient)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    void shouldUpdateClient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                .contentType("application/json")
                .content(gson.toJson(testClient)));

        List<ClientDto> data = clientService.getAll();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/clients/{id}", data.get(0).getId())
                        .contentType("application/json")
                        .content(gson.toJson(testClient)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldGetClientById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                .contentType("application/json")
                .content(gson.toJson(testClient)));

        List<ClientDto> data = clientService.getAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/clients/{id}", data.get(0).getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldGetAllClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clients/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
