package fit.iuh.dulichgiare.service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.entity.Account;

/**
 * 
 * @author HOAN HUU
 *
 */
@Service

public interface AccountService {

	public Account getAccountById(long id) throws InterruptedException, ExecutionException;

	public Optional<Account> getAccountByUserName(String userName) throws InterruptedException, ExecutionException;

}
