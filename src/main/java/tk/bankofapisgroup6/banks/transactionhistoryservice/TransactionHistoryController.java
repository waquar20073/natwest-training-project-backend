package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.util.JwtUtil;

@AllArgsConstructor
@RequestMapping(path="api/v1/transactions")
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
public class TransactionHistoryController {
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	@Autowired
	private JwtUtil jwutil;
	private static Logger logger = LoggerFactory.getLogger(TransactionHistoryController.class);
	
	private boolean validateToken(Map<String, String> headers, long requestAccountId) {
		String accessToken = headers.get("authorization").substring(7);
		long accountId = jwutil.getIdFromToken(accessToken);
		return accountId==requestAccountId;
	}
	
	@PostMapping("transactionfilter") 
	ResponseEntity<List<Transaction>> filterTransactions(@RequestHeader Map<String, String> headers, @RequestBody TransactionFilter requestTransaction){
		ResponseEntity<List<Transaction>> response = null;
		try {
			logger.info("Request: "+requestTransaction.toString());
			String fromDate=null;
			if(requestTransaction.getFrom()==null || requestTransaction.getFrom().length()<10) {
				fromDate="1900-01-01"; // default date
			}else {
				fromDate=requestTransaction.getFrom().substring(0,10);
			}
			String toDate=null;
			if(requestTransaction.getTo()==null || requestTransaction.getTo().length()<10) {
				toDate=LocalDate.now().toString(); // default date
			}else {
				toDate=requestTransaction.getTo().substring(0,10);
			}
			if(!validateToken(headers,requestTransaction.getAccountId())) {
				throw new IllegalStateException("Token not valid");
			}
			String search=null;
			if(requestTransaction.getSearch()==null) {
				search=""; // default date
			}else {
				search=requestTransaction.getSearch();
			}
			if(!validateToken(headers,requestTransaction.getAccountId())) {
				throw new IllegalStateException("Token not valid");
			}
			response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
				.filterTransactions(requestTransaction.getAccountId(),
						fromDate,
						toDate,
						search,
						requestTransaction.getSortBy()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	
	@PostMapping
	public ResponseEntity<List<Transaction>> getTransactions(@RequestHeader Map<String, String> headers, @RequestBody RequestTransaction requestTransaction){
		
		if(!validateToken(headers,requestTransaction.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<List<Transaction>> response = null;
		try {
		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
				.getTransactions(requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@PostMapping("newtransaction")
	public ResponseEntity<String> addTransaction(@RequestHeader Map<String, String> headers, @RequestBody TransactionDTO transactiondto) {
		if(!validateToken(headers,transactiondto.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		try {
			Transaction trans = transactionHistoryService.addTransaction(transactiondto);
			return ResponseEntity.status(HttpStatus.OK).body("Transaction Added");
		}catch(IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Account not Found");
		}
	}
	
	
	@PostMapping("expenses")
	public ResponseEntity<Double> getExpenses(@RequestHeader Map<String, String> headers, @RequestBody RequestTransaction requestTransaction){
		if(!validateToken(headers,requestTransaction.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<Double> response = null;
		try {
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(transactionHistoryService.getExpenses(requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@PostMapping("incomes")
	public ResponseEntity<Double> getIncomes(@RequestHeader Map<String, String> headers, @RequestBody RequestTransaction requestTransaction){
		if(!validateToken(headers,requestTransaction.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<Double> response = null;
		try {
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(transactionHistoryService.getIncomes(requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@PostMapping("fromdate")
	public ResponseEntity<List<Transaction>> getFromDate(@RequestHeader Map<String, String> headers, @RequestBody RequestTransactionByDate requestTransaction){
		if(!validateToken(headers,requestTransaction.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<List<Transaction>> response = null;
		try {
		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
				.findBetween(LocalDate.parse(requestTransaction.getFrom().substring(0,10),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						null,requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@PostMapping("todate")
	public ResponseEntity<List<Transaction>> getToDate(@RequestHeader Map<String, String> headers, @RequestBody RequestTransactionByDate requestTransaction){
		if(!validateToken(headers,requestTransaction.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<List<Transaction>> response = null;
		try {
		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
				.findBetween(null,
						LocalDate.parse(requestTransaction.getTo().substring(0,10),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@PostMapping("betweendate")
	public ResponseEntity<List<Transaction>> getBetweenDate(@RequestHeader Map<String, String> headers, @RequestBody RequestTransactionByDate requestTransaction){
		if(!validateToken(headers,requestTransaction.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<List<Transaction>> response = null;
		try {
		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
				.findBetween(LocalDate.parse(requestTransaction.getFrom().substring(0,10),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						LocalDate.parse(requestTransaction.getTo().substring(0,10),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
}
