package tk.bankofapisgroup6.banks.analytics;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MonthlyReport {
	List<DailyTransfer> daily = new ArrayList<>();
	TransactionType type;
	String month;
	int countDays;
	
	public void setDaily(double []daily, List<Integer> dates) {
		for(int i=1;i<daily.length;i++) {
			this.daily.add(new DailyTransfer(dates.get(i-1),daily[i]));
		}
	}
}
@NoArgsConstructor
@Getter
@Setter
class DailyTransfer{
	int date;
	double amount;
	
	public DailyTransfer(int i, double d) {
		date=i;
		amount=d;
	}
}
