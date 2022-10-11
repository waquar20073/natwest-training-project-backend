package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.bankofapisgroup6.banks.accounts.Account;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String transactionWith;
	@ManyToOne()
	@JoinColumn(
            nullable = false,
            name = "account_id"
    )
    private tk.bankofapisgroup6.banks.accounts.Account account;
	@Column(nullable = false)
	private long username;
	@Column(nullable = false)
	private tk.bankofapisgroup6.banks.transactionhistoryservice.TransactionType type;
	@Column(nullable = false)
	private double amount;
	@CreationTimestamp
	private Date timestamp;

	public Transaction(String transactionWith, Account account, TransactionType type,
			double amount) {
		super();
		this.transactionWith = transactionWith;
		this.account = account;
		this.type = type;
		this.amount = amount;
	}
	
}
