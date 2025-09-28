package com.priyanka.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
@Entity
public class Accounts extends AuditEntity{
    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;


}
