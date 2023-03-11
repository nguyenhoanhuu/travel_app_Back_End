package fit.iuh.dulichgiare.service.impl;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.entity.Account;
import fit.iuh.dulichgiare.repository.AccountRepository;
import fit.iuh.dulichgiare.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccountById(long id) throws InterruptedException, ExecutionException {

		return accountRepository.findById(id).get();
	}

	@Override
	public Optional<Account> getAccountByUserName(String userName) throws InterruptedException, ExecutionException {

		return accountRepository.findByUsername(userName);
	}



}
