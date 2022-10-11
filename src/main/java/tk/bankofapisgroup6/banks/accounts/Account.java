package tk.bankofapisgroup6.banks.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long accountId;
	@Column(nullable = false)
	String username;
	@Column(nullable = false)
	String password;
	@Column(nullable = false)
	Double balance;
	@Column(nullable = false)
	String apiKey;
}
