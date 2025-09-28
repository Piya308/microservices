package com.priyanka.accounts.service;

import com.priyanka.accounts.dto.CustomerDto;

public interface IAccountService {


    /**
     * @param customerDto
     */
    public void createAccount(CustomerDto customerDto);

    public CustomerDto fetchAccount( String mobileNumber);

    public boolean updateAccount(CustomerDto customerDto);

    public boolean deleteAccount(String mobileNumber);

}
