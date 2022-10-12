package tk.bankofapisgroup6.banks.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tk.bankofapisgroup6.banks.transactionhistoryservice.Transaction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TradingPartnerReport {
	Map<String,Integer> frequency;
	TransactionType type;
	String month;
}
