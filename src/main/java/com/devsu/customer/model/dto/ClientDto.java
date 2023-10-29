package com.devsu.customer.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private Long id;
    @NotBlank
    private String password;
    private boolean state;
    private PersonDto person;

}
