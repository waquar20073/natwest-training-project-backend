package tk.bankofapisgroup6.banks.transactionhistoryservice;

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
}
