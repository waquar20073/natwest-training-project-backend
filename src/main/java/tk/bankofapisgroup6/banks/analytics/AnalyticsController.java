package tk.bankofapisgroup6.banks.analytics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.accounts.Account;
import tk.bankofapisgroup6.banks.transactionhistoryservice.Transaction;

@RequestMapping(path="api/v1/analytics")
@RestController
@AllArgsConstructor
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyticsService;
	
	@GetMapping(path="expenses")
	public ResponseEntity<MonthlyReport> getExpenseReport(@RequestBody Request account){
		ResponseEntity<MonthlyReport> response=null;
		try {
			response = ResponseEntity.status(HttpStatus.OK).body(analyticsService.getExpenseReport(account.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(analyticsService.getExpenseReport(account.getAccountId()));
		}
		return response;
	}
	
	@GetMapping(path="incomes")
	public ResponseEntity<MonthlyReport> getIncomeReport(@RequestBody Request account){
		ResponseEntity<MonthlyReport> response=null;
		try {
			response = ResponseEntity.status(HttpStatus.OK).body(analyticsService.getIncomeReport(account.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(analyticsService.getIncomeReport(account.getAccountId()));
		}
		return response;
	}
	
	@GetMapping(path="expensepartners")
	public ResponseEntity<TradingPartnerReport> getExpensePartner(@RequestBody Request account){
		ResponseEntity<TradingPartnerReport> response=null;
		try {
			response = ResponseEntity.status(HttpStatus.OK).body(analyticsService.getPartnerExpense(account.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(analyticsService.getPartnerExpense(account.getAccountId()));
		}
		return response;
	}
	
	@GetMapping(path="incomepartners")
	public ResponseEntity<TradingPartnerReport> getIncomePartner(@RequestBody Request account){
		ResponseEntity<TradingPartnerReport> response=null;
		try {
			response = ResponseEntity.status(HttpStatus.OK).body(analyticsService.getPartnerIncome(account.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(analyticsService.getPartnerIncome(account.getAccountId()));
		}
		return response;
	}
}
