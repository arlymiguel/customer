package com.devsu.customer.mapper;

import com.devsu.customer.model.dto.ClientDto;
import com.devsu.customer.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto toDto(Client client);
    Client toEntity(ClientDto dto);

}
