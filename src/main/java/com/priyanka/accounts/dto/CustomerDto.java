package com.priyanka.accounts.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String email;
    private String name;
    private String mobileNumber;
    private AccountsDto accountsDto;
}
