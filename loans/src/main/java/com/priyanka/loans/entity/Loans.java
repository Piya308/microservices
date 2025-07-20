package com.priyanka.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Loans extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

}