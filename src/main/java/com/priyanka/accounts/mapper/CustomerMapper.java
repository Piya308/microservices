package com.priyanka.accounts.mapper;

import com.priyanka.accounts.constants.AccountConstants;
import com.priyanka.accounts.dto.AccountsDto;
import com.priyanka.accounts.dto.CustomerDto;
import com.priyanka.accounts.entity.Accounts;
import com.priyanka.accounts.entity.Customer;
import com.priyanka.accounts.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerMapper {

    @Autowired
    AccountServiceImpl accountService;
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCutomer(CustomerDto customerDto, Customer customer){
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }

}
