package com.priyanka.accounts.service;

import com.priyanka.accounts.dto.CustomerDto;

public interface IAccountService {


    /**
     * @param customerDto
     */
    public void createAccount(CustomerDto customerDto);
}
