package com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.mapper;

import com.rafael.ribeiro.demoprojetoapirest.entity.Customer;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerCreateDto;
import com.rafael.ribeiro.demoprojetoapirest.web.Controller.dto.CustomerResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.*;

public class CustomerMapper {


    public static Customer tocustomer(CustomerCreateDto customerCreateDto){

        return new ModelMapper().map(customerCreateDto, Customer.class);
    }

    public static CustomerResponseDto toDto(Customer customer){
        String role = customer.getRole().name().substring("ROLE_".length());
        PropertyMap<Customer,CustomerResponseDto> props = new PropertyMap<Customer, CustomerResponseDto>() {
            @Override
            protected void configure() {

                map().setRole(role);
            }
        };
        ModelMapper  mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(customer, CustomerResponseDto.class);


    }
    public static List<CustomerResponseDto> toListDto(List<Customer> customers){
        return customers.stream()
                .map(customer -> toDto(customer))
                .collect(Collectors.toList());


    }





}
