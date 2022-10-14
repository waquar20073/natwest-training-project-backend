package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

	@Query("SELECT t FROM Transaction t WHERE date_format(t.timestamp, '%Y-%m-%d')>=?2 AND date_format(t.timestamp, '%Y-%m-%d')<=?3 AND t.account.accountId=?1")
	List<Transaction> findTransactionsFromTo(long accountId, String From, String to);

	@Query("SELECT t FROM Transaction t WHERE date_format(t.timestamp, '%Y-%m-%d')<=?2 AND t.account.accountId=?1" ) 
	List<Transaction> findTransactionsTo(long accountId, String to);

	@Query("SELECT t FROM Transaction t WHERE date_format(t.timestamp, '%Y-%m-%d')>=?2 AND t.account.accountId=?1" ) 
	List<Transaction> findTransactionsFrom(long accountId, String from);
	
	@Query("SELECT t FROM Transaction t WHERE "
			+ "t.transactionWith LIKE ?4 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')>=?2 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')<=?3 AND "
			+ "t.account.accountId=?1 "
			+ "order by timestamp")
	List<Transaction> filterAndOrderByTime(long accountId, String from, String to, String search);
	
	@Query("SELECT t FROM Transaction t WHERE "
			+ "t.transactionWith LIKE ?4 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')>=?2 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')<=?3 AND "
			+ "t.account.accountId=?1 "
			+ "order by timestamp DESC")
	List<Transaction> filterAndOrderByTimeDesc(long accountId, String from, String to, String search);
	
	@Query("SELECT t FROM Transaction t WHERE "
			+ "t.transactionWith LIKE ?4 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')>=?2 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')<=?3 AND "
			+ "t.account.accountId=?1 "
			+ "order by amount")
	List<Transaction> filterAndOrderByAmount(long accountId, String from, String to, String search);
	
	@Query("SELECT t FROM Transaction t WHERE "
			+ "t.transactionWith LIKE ?4 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')>=?2 AND "
			+ "date_format(t.timestamp, '%Y-%m-%d')<=?3 AND "
			+ "t.account.accountId=?1 "
			+ "order by amount DESC")
	List<Transaction> filterAndOrderByAmountDesc(long accountId, String from, String to, String search);
}
