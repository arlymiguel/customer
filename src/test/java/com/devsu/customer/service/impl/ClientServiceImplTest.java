package com.devsu.customer.service.impl;

import com.devsu.customer.model.dto.ClientDto;
import com.devsu.customer.model.dto.PersonDto;
import com.devsu.customer.model.entity.Client;
import com.devsu.customer.model.entity.Person;
import com.devsu.customer.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    private ClientDto clientDto;
    private Client client;

    @BeforeEach
    void setUp() {
        PersonDto personDto = PersonDto.builder()
                .name("Arly")
                .gender("M")
                .address("Comas")
                .age(30)
                .phone("994320740")
                .identification("73386321")
                .build();
        clientDto = ClientDto.builder()
                .password("123")
                .state(true)
                .person(personDto)
                .build();

        Person person = Person.builder()
                .name("Arly")
                .gender("M")
                .address("Comas")
                .age(30)
                .phone("994320740")
                .identification("73386321")
                .build();
        client = Client.builder()
                .password("123")
                .state(true)
                .person(person)
                .build();
    }

    @Test
    @DisplayName("When client is sent to create it is saved into the table")
    void create_test() {

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto clienteCreated = clientServiceImpl.create(clientDto);

        Assertions.assertNotNull(clienteCreated);
    }

    @Test
    @DisplayName("When client is sent to update by the id, it is updated in the table")
    void update_test() {

        Long id = 1L;
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findById(id)).thenReturn(clientOptional);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto clienteUpdated = clientServiceImpl.update(clientDto, id);

        Assertions.assertNotNull(clienteUpdated);
    }

    @Test
    @DisplayName("When client is deleted by the id, it is removed in the table")
    void delete_test() {

        Long id = 1L;

        doNothing().when(clientRepository).deleteById(id);

        clientServiceImpl.delete(id);

        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(id);


    }

    @Test
    @DisplayName("When client wants to find a client by the id, it is returned")
    void getById_test() {

        Long id = 1L;
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findById(id)).thenReturn(clientOptional);

        ClientDto clientFound = clientServiceImpl.getById(id);

        Assertions.assertNotNull(clientFound);
    }


}
