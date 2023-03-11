package fit.iuh.dulichgiare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fit.iuh.dulichgiare.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public Optional<Account> findByUsername(String username);

//	public Account loadUserById(Long userId);

}
