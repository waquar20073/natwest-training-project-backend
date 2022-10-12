package tk.bankofapisgroup6.banks.accounts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByUsername(String username);
	Optional<Account> findByAccountId(long accountId);

	@Transactional
	@Modifying
	@Query("update Account a set a.balance = a.balance + ?2 where a.accountId = ?1")
	int creditAmount(long accountId, Double creditAmount);

	@Transactional
	@Modifying
	@Query("update Account a set a.balance = a.balance - ?2 where a.accountId = ?1")
	int debitAmount(long accountId, Double debitAmount);
}
