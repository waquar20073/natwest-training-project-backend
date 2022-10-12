package tk.bankofapisgroup6.banks.analytics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tk.bankofapisgroup6.banks.transactionhistoryservice.Transaction;


public interface AnalyticsRepository extends JpaRepository<Transaction, Long>{
	@Query("SELECT t FROM Transaction t WHERE MONTH(t.timestamp) = MONTH(CURRENT_DATE()) AND YEAR(t.timestamp) = YEAR(CURRENT_DATE()) AND t.account.accountId=?1")
	List<Transaction> findByMonth(long accountId);
	
	List<Transaction> findTransactionByAccountAccountId(long accountId);
}
