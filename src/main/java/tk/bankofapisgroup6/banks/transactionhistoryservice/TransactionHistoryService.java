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
		double amount = 0.0d;
		if(transactiondto.getType()==TransactionType.debit &&transactiondto.getAmount()<0) {
			amount = transactiondto.getAmount();
		}else {
			amount = transactiondto.getAmount()*-1;
		}
		Transaction transaction = new Transaction(
				transactiondto.getTransactionWith(),
				account, transactiondto.getType(),
				amount);
		return transactionHistoryRepository.save(transaction);
	}

	public List<Transaction> findBetween(Date fromDate, Date toDate, long accountId) {
//		if(fromDate!=null && toDate!=null) {
//			transactionHistoryRepository.findTransactionsFromTo(accountId,null,null);
//		}
//		else if(fromDate==null && toDate==null) {
//			// get all transactions
//			return getTransactions(accountId);
//		}else if(fromDate!=null) {
//			// hence toDate is null
//			transactionHistoryRepository.findTransactionsFrom(accountId,null);
//		}else {
//			// fromDate is null
//			transactionHistoryRepository.findTransactionsTo(accountId,null);
//		}
//		
//		return null;
		return null;
	}

	@Override
	public List<Transaction> findByQuery(String query) {
		List<Transaction> transactions = transactionHistoryRepository.findAll();
		// TODO: use java streams and regex to filter transactions on field from/to
		return null;
	}

	public List<Transaction> getTransactions(long accountId) {
		return transactionHistoryRepository.findAccountById(accountId);
	}
	
	public Double getExpenses(long accountId) {
		return transactionHistoryRepository.findExpense(accountId);
	}

	public Double getIncomes(long accountId) {
		return transactionHistoryRepository.findIncome(accountId);
	}

}
