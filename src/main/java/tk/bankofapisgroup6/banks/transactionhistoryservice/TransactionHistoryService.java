package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.sql.Date;
import java.time.LocalDate;
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
			account = (Account)accountService.loadUserById(transactiondto.getAccountId());
		}catch(IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getLocalizedMessage());
			//"AccountID Not Found during add Transaction";
		}
		double amount = transactiondto.getAmount();
		if(transactiondto.getType()==TransactionType.debit &&transactiondto.getAmount()<0) {
			amount = transactiondto.getAmount();
		}else {
			amount = transactiondto.getAmount()*-1;
		}
		if(transactiondto.getType()==TransactionType.credit &&transactiondto.getAmount()>0) {
			amount = transactiondto.getAmount();
		}else {
			amount = transactiondto.getAmount()*-1;
		}
		Transaction transaction = new Transaction(
				transactiondto.getTransactionWith(),
				account, transactiondto.getType(),
				amount);
		Transaction trans = transactionHistoryRepository.save(transaction);
		accountService.updateBalance(transactiondto.getAccountId(), amount);
		return transactionHistoryRepository.save(transaction);
	}

	public List<Transaction> findBetween(LocalDate fromDate, LocalDate toDate, long accountId) {
		List<Transaction> trans = null;
		if(fromDate!=null && toDate!=null) {
			trans = transactionHistoryRepository.findTransactionsFromTo(accountId,fromDate.toString(),toDate.toString());
		}
		else if(fromDate==null && toDate==null) {
			// get all transactions
			trans =  getTransactions(accountId);
		}else if(fromDate!=null) {
			// hence toDate is null
			trans = transactionHistoryRepository.findTransactionsFrom(accountId,fromDate.toString());
		}else {
			// fromDate is null
			trans = transactionHistoryRepository.findTransactionsTo(accountId,toDate.toString());
		}
		return trans;
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
