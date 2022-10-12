package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.Date;
import java.util.List;

public interface TransactionHistoryServiceInterface {
	List<Transaction> getTransactions(long accountId);
	Transaction addTransaction(TransactionDTO transactiondto);
	List<Transaction> findByQuery(String query);
}	
