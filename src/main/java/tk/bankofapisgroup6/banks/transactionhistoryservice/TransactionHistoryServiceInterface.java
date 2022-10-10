package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.Date;
import java.util.List;

public interface TransactionHistoryServiceInterface {
	List<Transaction> findAll();
	Transaction addTransaction();
	List<Transaction> findBetween(Date fromDate, Date toDate);
	List<Transaction> findByQuery(String query);
}	
