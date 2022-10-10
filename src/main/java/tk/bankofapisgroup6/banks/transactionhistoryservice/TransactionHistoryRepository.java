package tk.bankofapisgroup6.banks.transactionhistoryservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<Transaction, Long>{
	
}
