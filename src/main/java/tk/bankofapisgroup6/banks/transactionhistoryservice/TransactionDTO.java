package tk.bankofapisgroup6.banks.transactionhistoryservice;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionDTO {
	private String transactionWith;
	private long accountId;
	private TransactionType type;
	private double amount;
	
	public long getAccountId() {
		return accountId;
	}

	public String getTransactionWith() {
		return transactionWith;
	}

	public TransactionType getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}
	
}
