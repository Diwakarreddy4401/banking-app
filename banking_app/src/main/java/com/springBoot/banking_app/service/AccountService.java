package com.springBoot.banking_app.service;

import java.util.List;

import com.springBoot.banking_app.dto.AccountDto;
import com.springBoot.banking_app.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDyo);
	List<AccountDto> getAllAccounts();
	AccountDto getAccountById(long id);
	void deleteAccount(long id);
	AccountDto withdrawAmount(long id, double amount);
	AccountDto depositAmount(long id, double amount);
//	AccountDto 

}
