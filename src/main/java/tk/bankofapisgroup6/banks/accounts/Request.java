package tk.bankofapisgroup6.banks.accounts;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Request {
	long accountId;

	public Request() {
		super();
	}

	public Request(long accountId) {
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
