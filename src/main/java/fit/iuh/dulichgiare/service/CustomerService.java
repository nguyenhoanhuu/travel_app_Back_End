package fit.iuh.dulichgiare.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.AccountCustomerDTO;
import fit.iuh.dulichgiare.entity.Customer;

/**
 * 
 * @author HOAN HUU
 *
 */
@Service
public interface CustomerService {

	public Customer registerAccountCustomer(AccountCustomerDTO accountCustomerDTO);

	public AccountCustomerDTO getCustomerById(long id) throws InterruptedException, ExecutionException;

}
