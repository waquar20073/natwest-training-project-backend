package tk.bankofapisgroup6.banks.transactionhistoryservice;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TransactionFilter {
	int accountId;
	String from;
	String to;
	String search;
	String sortBy;
	String type;
	
	public TransactionFilter() {
		super();
	}

	public TransactionFilter(int accountId, String from, String to, String search, String sortBy, String type) {
		super();
		this.accountId = accountId;
		this.from = from;
		this.to = to;
		this.search = search;
		this.sortBy = sortBy;
		this.type = type;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
