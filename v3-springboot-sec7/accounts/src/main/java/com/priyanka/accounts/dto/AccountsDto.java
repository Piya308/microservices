package com.priyanka.accounts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @Pattern(regexp = "(^$|[0-9]{12})", message = "please enter valid account number")
    @NotNull(message = "account number can not be empty")
    private Long accountNumber;

    @NotNull(message = "account type can not be empty")
    private String accountType;

    @NotNull(message = "branch address can not be empty")
    private String branchAddress;
}
