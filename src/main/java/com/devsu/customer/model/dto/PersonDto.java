package com.devsu.customer.model.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;

}
