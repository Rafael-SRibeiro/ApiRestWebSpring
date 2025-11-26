package com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponseDto {

    private Long id;

    private String username;

    private String role;



}
