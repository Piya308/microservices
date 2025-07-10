package com.priyanka.accounts.repository;

import com.priyanka.accounts.entity.Accounts;
import com.priyanka.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(long customerId);

}
