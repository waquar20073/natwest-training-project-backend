package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.accounts.Account;
import tk.bankofapisgroup6.banks.accounts.AccountService;

@AllArgsConstructor
@Service
public class TransactionHistoryService implements TransactionHistoryServiceInterface{
	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;
	@Autowired
	AccountService accountService;
	
	@Override
	public Transaction addTransaction(TransactionDTO transactiondto) {
		Account account;
		try {
			account = accountService.getAccount(transactiondto.getAccountId());
		}catch(IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getLocalizedMessage());
			//"AccountID Not Found during add Transaction";
		}
		Transaction transaction = new Transaction(
				transactiondto.getTransactionWith(),
				account, transactiondto.getType(),
				transactiondto.getAmount());
		return transactionHistoryRepository.save(transaction);
	}

	@Override
	public List<Transaction> findBetween(Date fromDate, Date toDate) {

		return null;
	}

	@Override
	public List<Transaction> findByQuery(String query) {
		List<Transaction> transactions = transactionHistoryRepository.findAll();
		// TODO: use java streams and regex to filter transactions on field from/to
		return null;
	}

	public List<Transaction> getTransactions(String username, String token) {
		return transactionHistoryRepository.findAll();
	}

}
