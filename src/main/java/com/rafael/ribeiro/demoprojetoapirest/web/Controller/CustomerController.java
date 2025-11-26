package com.rafael.ribeiro.demoprojetoapirest.web.Controller;

import com.rafael.ribeiro.demoprojetoapirest.Service.CustomerService;
import com.rafael.ribeiro.demoprojetoapirest.entity.Customer;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerCreateDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerPasswordDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerResponseDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.mapper.CustomerMapper;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "customer", description = "Aborda as informações referente aos endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Create new customer",
            description = "Cria no sistema um novo cliente",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Cliente criado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Customer já existe no sistema",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Erro de validação - dados inválidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@Valid @RequestBody CustomerCreateDto customerCreateDto) {
        Customer customer1 = customerService.save(CustomerMapper.tocustomer(customerCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toDto(customer1));
    }


    @Operation(
            summary = " Recuperar customer by id",
            description = " Recuperar customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Cliente criado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recuso não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long id) {
        Customer customer1 = customerService.searchByID(id);
        return ResponseEntity.ok(CustomerMapper.toDto(customer1));
    }


    @Operation(
            summary = "Atualiza Senha",
            description ="Atualiza Senha",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Senha atualizada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recuso não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Senha invalida",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody CustomerPasswordDto dto) {
        customerService.editPassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAll() {
        List<Customer> customers = customerService.searchAll();
        return ResponseEntity.ok(CustomerMapper.toListDto(customers));
    }


}
