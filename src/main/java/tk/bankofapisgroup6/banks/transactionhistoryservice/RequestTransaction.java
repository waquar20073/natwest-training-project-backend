package tk.bankofapisgroup6.banks.transactionhistoryservice;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RequestTransaction {
	long accountId;

	public RequestTransaction() {
		super();
	}

	public RequestTransaction(long accountId) {
		super();
		this.accountId = accountId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
}
