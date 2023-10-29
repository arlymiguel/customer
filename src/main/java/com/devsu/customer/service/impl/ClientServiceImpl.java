package com.devsu.customer.service.impl;

import com.devsu.customer.exception.ClientNotFoundException;
import com.devsu.customer.mapper.ClientMapper;
import com.devsu.customer.model.dto.ClientDto;
import com.devsu.customer.model.entity.Client;
import com.devsu.customer.repository.ClientRepository;
import com.devsu.customer.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDto create(ClientDto client) {

        Client clientToSave = ClientMapper.INSTANCE.toEntity(client);
        Client clientSaved = clientRepository.save(clientToSave);

        log.info("clientSaved : {}",clientSaved);
        ClientDto dataToReturn = ClientMapper.INSTANCE.toDto(clientSaved);
        log.info("dataToReturn : {}",dataToReturn);

        return dataToReturn;
    }

    @Override
    public ClientDto update(ClientDto client, Long id) {

        Optional<Client> clientFound = clientRepository.findById(id);
        if (clientFound.isPresent()) {
            Client clientToUpdate = ClientMapper.INSTANCE.toEntity(client);
            clientToUpdate.setId(id);
            clientToUpdate.getPerson().setId(clientFound.get().getPerson().getId());

            Client clientUpdated = clientRepository.save(clientToUpdate);
            return ClientMapper.INSTANCE.toDto(clientUpdated);
        }
        throw new ClientNotFoundException("Client not found");
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto getById(Long id) {
        Optional<Client> clientFound = clientRepository.findById(id);
        if (clientFound.isPresent()) {
            return ClientMapper.INSTANCE.toDto(clientFound.get());
        }
        throw new ClientNotFoundException("Client not found");
    }

    @Override
    public List<ClientDto> getAll() {

        List<ClientDto> clientList = new ArrayList<>();

        clientRepository.findAll().stream().forEach(client -> clientList.add(ClientMapper.INSTANCE.toDto(client)) );

        return clientList;
    }
}
