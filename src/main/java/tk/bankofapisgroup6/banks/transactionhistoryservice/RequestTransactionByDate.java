package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestTransactionByDate {
	long accountId;
	String from;
	String to;
	
}
