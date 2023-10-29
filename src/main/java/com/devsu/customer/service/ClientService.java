package com.devsu.customer.service;

import com.devsu.customer.model.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    ClientDto create(ClientDto client);
    ClientDto update(ClientDto client, Long id);
    void delete(Long id);
    ClientDto getById(Long id);
    List<ClientDto> getAll();

}
