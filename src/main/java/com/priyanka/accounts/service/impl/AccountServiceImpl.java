package com.priyanka.accounts.service.impl;

import com.priyanka.accounts.constants.AccountConstants;
import com.priyanka.accounts.dto.CustomerDto;
import com.priyanka.accounts.entity.Accounts;
import com.priyanka.accounts.entity.Customer;
import com.priyanka.accounts.exceptions.CustomerAlreadyExists;
import com.priyanka.accounts.mapper.CustomerMapper;
import com.priyanka.accounts.repository.AccountsRepository;
import com.priyanka.accounts.repository.CustomerRepository;
import com.priyanka.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCutomer(customerDto, new Customer());
        Optional<Customer>customerAlreadyExists = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(customerAlreadyExists.isPresent()){
            throw new CustomerAlreadyExists("Customer Already registered with given number");
        }
        customer.setCreatedBy("anonymous");
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        accounts.setCreatedBy("anonymous");
        accounts.setCreatedAt(LocalDateTime.now());
        return accounts;
    }
}
