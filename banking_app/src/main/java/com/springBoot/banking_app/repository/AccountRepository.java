package com.springBoot.banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.banking_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
