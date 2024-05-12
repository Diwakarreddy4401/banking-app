package com.springBoot.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.springBoot.banking_app.dto.AccountDto;
import com.springBoot.banking_app.entity.Account;
import com.springBoot.banking_app.mapper.AccountMapper;
import com.springBoot.banking_app.repository.AccountRepository;
import com.springBoot.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accountes = accountRepository.findAll();
		return accountes.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public AccountDto getAccountById(long id) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account does not exist."));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public void deleteAccount(long id) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account does not exist."));
		accountRepository.deleteById(id);
	}

	@Override
	public AccountDto withdrawAmount(long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account does not exist."));
		if(account.getBalance() < amount) throw new RuntimeException("Insufficient balance.");
		double withdrawAmount = account.getBalance() - amount;
		account.setBalance(withdrawAmount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto depositAmount(long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account does not exist."));
		double withdrawAmount = account.getBalance() + amount;
		account.setBalance(withdrawAmount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
