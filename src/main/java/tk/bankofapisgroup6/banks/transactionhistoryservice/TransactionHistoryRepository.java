package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import tk.bankofapisgroup6.banks.accounts.Account;

public interface TransactionHistoryRepository extends JpaRepository<Transaction, Long>{
	
	Optional<Transaction> findByUsername(String username);
	
//    @Query("SELECT Transaction t " +
//            "WHERE a.email = ?1")
//    List<Transaction> findByDate(Date from, Date to);
	
	@Query("SELECT t FROM Transaction t WHERE t.account.accountId=?1")
	List<Transaction> findAccountById(long accountId);
	
	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE MONTH(t.timestamp) = MONTH(CURRENT_DATE())"
	+"AND YEAR(t.timestamp) = YEAR(CURRENT_DATE()) AND t.account.accountId=?1 AND t.type=1")
	Double findExpense(long accountId);

	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE MONTH(t.timestamp) = MONTH(CURRENT_DATE())"
			+"AND YEAR(t.timestamp) = YEAR(CURRENT_DATE()) AND t.account.accountId=?1 AND t.type=0")
	Double findIncome(long accountId);

	
//	void findTransactionsFromTo(long accountId, Date From, Date to);
//
//	void findTransactionsTo(long accountId, Date to);
//
//	void findTransactionsFrom(long accountId, Date from);
}
