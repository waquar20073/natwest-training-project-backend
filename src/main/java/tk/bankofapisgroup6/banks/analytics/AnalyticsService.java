package tk.bankofapisgroup6.banks.analytics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.bankofapisgroup6.banks.transactionhistoryservice.Transaction;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnalyticsService {
	@Autowired
	AnalyticsRepository analyticsRepository;
	
	public MonthlyReport getExpenseReport(long accountId) {
		// get current month's daily expense histogram
		LocalDate currentDate = LocalDate.now();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean isLeapYear = currentDate.isLeapYear();
		
		Month month = currentDate.getMonth();
		Integer numDays = month.length(isLeapYear);
		
		double []expenses = new double[numDays+1];
		
		List<Transaction> transactions = analyticsRepository.findByMonth(accountId);
		for(Transaction t: transactions) {
			if(t.getType().ordinal()==TransactionType.debit.ordinal()) {
				expenses[t.getTimestamp().getDate()]+=t.getAmount();	
			}
		}
		MonthlyReport report = new MonthlyReport();
		report.setDaily(expenses);
		report.setType(TransactionType.debit);
		report.setMonth(currentDate.getMonth().toString());
		return report;
	}
	
	public MonthlyReport getIncomeReport(long accountId) {
		// get current month's daily income histogram
		LocalDate currentDate = LocalDate.now();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean isLeapYear = currentDate.isLeapYear();
		
		Month month = currentDate.getMonth();
		Integer numDays = month.length(isLeapYear);
		
		double []expenses = new double[numDays+1];
		
		List<Transaction> transactions = analyticsRepository.findByMonth(accountId);
		for(Transaction t: transactions) {
			if(t.getType().ordinal()==TransactionType.credit.ordinal()) {
				expenses[t.getTimestamp().getDate()]+=t.getAmount();	
			}
		}
		MonthlyReport report = new MonthlyReport();
		report.setDaily(expenses);
		report.setType(TransactionType.credit);
		report.setMonth(currentDate.getMonth().toString());
		return report;
	}
	
	public TradingPartnerReport getPartnerExpense(long accountId) {
		// get current month's daily income histogram
		LocalDate currentDate = LocalDate.now();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean isLeapYear = currentDate.isLeapYear();
		
		Month month = currentDate.getMonth();
		Integer numDays = month.length(isLeapYear);
		
		List<Transaction> transactions = analyticsRepository.findByMonth(accountId);
		Map<String,Integer> frequency = new HashMap<String,Integer>();
		
		for(Transaction t: transactions) {
			if(t.getType().ordinal()==TransactionType.debit.ordinal()) {
				Integer f = frequency.get(t.getTransactionWith());
				 //checking null
			    if(f==null) f=0;
			    frequency.put(t.getTransactionWith(),f+1);
			}
		}
		TradingPartnerReport report = new TradingPartnerReport();
		report.setFrequency(frequency);
		report.setType(TransactionType.debit);
		report.setMonth(currentDate.getMonth().toString());
		return report;
	}
	
	public TradingPartnerReport getPartnerIncome(long accountId) {
		// get current month's daily income histogram
		LocalDate currentDate = LocalDate.now();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean isLeapYear = currentDate.isLeapYear();
		
		Month month = currentDate.getMonth();
		Integer numDays = month.length(isLeapYear);

		List<Transaction> transactions = analyticsRepository.findByMonth(accountId);
		Map<String,Integer> frequency = new HashMap<String,Integer>();
		
		for(Transaction t: transactions) {
			if(t.getType().ordinal()==TransactionType.credit.ordinal()) {
				Integer f = frequency.get(t.getTransactionWith());
				 //checking null
			    if(f==null) f=0;
			    frequency.put(t.getTransactionWith(),f+1);
			}
		}
		TradingPartnerReport report = new TradingPartnerReport();
		report.setFrequency(frequency);
		report.setType(TransactionType.credit);
		report.setMonth(currentDate.getMonth().toString());
		return report;
	}
}
