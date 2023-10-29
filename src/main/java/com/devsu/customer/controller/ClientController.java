package com.devsu.customer.controller;

import com.devsu.customer.model.dto.ClientDto;
import com.devsu.customer.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping
    public ClientDto save(@RequestBody @Valid ClientDto client) {
        log.info("To save: {}",client);
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    public ClientDto update(@RequestBody @Valid ClientDto client, @PathVariable(value = "id")Long id) {
        log.info("To update: {}, id: {}",client, id);
        return clientService.update(client, id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id")Long id) {
        clientService.delete(id);
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable(value = "id")Long id) {
        log.info("FindById: {}",id);
        return clientService.getById(id);
    }

    @GetMapping
    public List<ClientDto> getAll() {
        log.info("getAll");
        return clientService.getAll();
    }


}
