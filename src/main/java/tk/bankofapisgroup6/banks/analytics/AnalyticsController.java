package tk.bankofapisgroup6.banks.analytics;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.transactionhistoryservice.TransactionHistoryService;
import tk.bankofapisgroup6.banks.util.JwtUtil;

@RequestMapping(path="api/v1/analytics")
@RestController
@AllArgsConstructor
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyticsService;
	@Autowired
	private JwtUtil jwutil;
	private static Logger logger = LoggerFactory.getLogger(TransactionHistoryService.class);
	
	private boolean validateToken(Map<String, String> headers, long requestAccountId) {
		String accessToken = headers.get("authorization").substring(7);
		long accountId = jwutil.getIdFromToken(accessToken);
		return accountId==requestAccountId;
	}
	
	
	@GetMapping(path="expenses")
	public ResponseEntity<MonthlyReport> getExpenseReport(@RequestHeader Map<String, String> headers, @RequestBody Request account){
		if(!validateToken(headers,account.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
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
	public ResponseEntity<MonthlyReport> getIncomeReport(@RequestHeader Map<String, String> headers, @RequestBody Request account){
		if(!validateToken(headers,account.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
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
	public ResponseEntity<TradingPartnerReport> getExpensePartner(@RequestHeader Map<String, String> headers, @RequestBody Request account){
		if(!validateToken(headers,account.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
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
	public ResponseEntity<TradingPartnerReport> getIncomePartner(@RequestHeader Map<String, String> headers, @RequestBody Request account){
		if(!validateToken(headers,account.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
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
