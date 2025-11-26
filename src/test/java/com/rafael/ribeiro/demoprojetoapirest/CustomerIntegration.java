package com.rafael.ribeiro.demoprojetoapirest;


import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerCreateDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerPasswordDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerResponseDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql (scripts = "/sql/customer/customer-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql (scripts = "/sql/customer/customer-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class CustomerIntegration {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createCustomer_ComUsernameEPasswordValidos_RetornarCustomerCriadoComStatus201() {
        CustomerResponseDto responseBody = testClient
                .post()
                .uri("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new CustomerCreateDto("coca@email.com", "1234$2"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("coca@email.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CUSTOMER");
    }


    @Test
    public void listarUsuarios_SemQualquerParametro_RetornarListaDeUsuariosComStatus200() {
        List<CustomerResponseDto> responseBody = testClient
                .get()
                .uri("/api/customer")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CustomerResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(3);
    }

}
