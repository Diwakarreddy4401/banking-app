package com.springBoot.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.banking_app.dto.AccountDto;
import com.springBoot.banking_app.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	public AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PutMapping
	public ResponseEntity<AccountDto> createAccount (@RequestBody AccountDto accountDto){
		
		return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounter() {
		List<AccountDto> accountes = accountService.getAllAccounts();
		return ResponseEntity.ok(accountes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable long id) {
		AccountDto account = accountService.getAccountById(id);
		return ResponseEntity.ok(account);
	}
	
	@PostMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdrawAmountById(@PathVariable long id, @RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdrawAmount(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@PostMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmountById(@PathVariable long id, @RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		AccountDto accountDto = accountService.depositAmount(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted Succesfully");
	}
	
	

}
