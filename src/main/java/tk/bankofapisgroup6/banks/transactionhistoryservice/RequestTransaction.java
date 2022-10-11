package tk.bankofapisgroup6.banks.transactionhistoryservice;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestTransaction {
	String username;
	String token;
}
