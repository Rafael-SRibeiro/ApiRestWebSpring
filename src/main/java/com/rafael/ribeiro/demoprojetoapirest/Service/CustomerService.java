package com.rafael.ribeiro.demoprojetoapirest.Service;

import com.rafael.ribeiro.demoprojetoapirest.Service.exception.UsernameUniqueViolationException;
import com.rafael.ribeiro.demoprojetoapirest.entity.Customer;
import com.rafael.ribeiro.demoprojetoapirest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    // Criar cliente
    @Transactional
    public Customer save(Customer customer) {
        try{

            return customerRepository.save(customer);
        }catch (org.springframework.dao.DataIntegrityViolationException exception){

            throw new UsernameUniqueViolationException(String.format ("Username %s já cadastrado", customer.getUsername()));

        }

    }

    // Buscar cliente por ID
    @Transactional(readOnly = true)
    public Customer searchByID(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not exists")
        );
    }

    // Editar senha de cliente pelo ID
    @Transactional
    public Customer editPassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        if(!newPassword.equals(confirmPassword)){
            throw new RuntimeException("invalid password as the passwords are different");

        }

        Customer customer1 = searchByID(id);

        if (customer1.getPassword().equals(currentPassword)){
            throw new RuntimeException("Sua senha nao confere");

        }
        customer1.setPassword(newPassword);
        return customer1;
    }
    @Transactional(readOnly = true)

    // Buscar todos os clientes
    public List<Customer> searchAll() {
        return customerRepository.findAll();
    }












}






