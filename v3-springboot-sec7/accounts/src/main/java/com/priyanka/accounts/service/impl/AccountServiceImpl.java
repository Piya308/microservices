package com.priyanka.accounts.service.impl;

import com.priyanka.accounts.constants.AccountConstants;
import com.priyanka.accounts.dto.AccountsDto;
import com.priyanka.accounts.dto.CustomerDto;
import com.priyanka.accounts.entity.Accounts;
import com.priyanka.accounts.entity.Customer;
import com.priyanka.accounts.exceptions.CustomerAlreadyExists;
import com.priyanka.accounts.exceptions.ResourceNotFoundException;
import com.priyanka.accounts.mapper.AccountsMapper;
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
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "CustomerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        return accounts;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto!=null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "AccountNumber", accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", customerId.toString()));

            CustomerMapper.mapToCutomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        if(customer!=null) {
            accountsRepository. deleteBycustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());
        }
        return true;
    }


}
