package tk.bankofapisgroup6.banks.analytics;

import java.util.ArrayList;
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
	List<Partner> data = new ArrayList<>();
	TransactionType type;
	String month;
}
class Partner{
	String partnerName;
	int frequency;
	
	public Partner() {
		super();
	}
	public Partner(String partnerName, int frequency) {
		super();
		this.partnerName = partnerName;
		this.frequency = frequency;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
}
