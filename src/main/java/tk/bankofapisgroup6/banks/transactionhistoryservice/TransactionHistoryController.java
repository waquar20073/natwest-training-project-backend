package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping(path="api/v1/transactions")
@RestController
public class TransactionHistoryController {
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getTransactions(@RequestBody RequestTransaction requestTransaction){
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
	public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transactiondto) {
		try {
			Transaction trans = transactionHistoryService.addTransaction(transactiondto);
			return ResponseEntity.status(HttpStatus.OK).body("Transaction Added");
		}catch(IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Account not Found");
		}
	}
	
	
	@GetMapping("expenses")
	public ResponseEntity<Double> getExpenses(@RequestBody RequestTransaction requestTransaction){
		ResponseEntity<Double> response = null;
		try {
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(transactionHistoryService.getExpenses(requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@GetMapping("incomes")
	public ResponseEntity<Double> getIncomes(@RequestBody RequestTransaction requestTransaction){
		ResponseEntity<Double> response = null;
		try {
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(transactionHistoryService.getIncomes(requestTransaction.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@GetMapping("fromdate")
	public ResponseEntity<List<Transaction>> getFromDate(@RequestBody RequestTransaction requestTransaction){
//		ResponseEntity<List<Transaction>> response = null;
//		try {
//		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
//				.(requestTransaction.getAccountId()));
//		}catch(Exception e) {
//			e.getLocalizedMessage();
//			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
//		}
//		return response;
		return null;
	}
	
	@GetMapping("todate")
	public ResponseEntity<List<Transaction>> getToDate(@RequestBody RequestTransaction requestTransaction){
//		ResponseEntity<List<Transaction>> response = null;
//		try {
//		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
//				.(requestTransaction.getAccountId()));
//		}catch(Exception e) {
//			e.getLocalizedMessage();
//			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
//		}
//		return response;
		return null;
	}
	
	@GetMapping("betweendate")
	public ResponseEntity<List<Transaction>> getBetweenDate(@RequestBody RequestTransaction requestTransaction){
//		ResponseEntity<List<Transaction>> response = null;
//		try {
//		response = ResponseEntity.status(HttpStatus.OK).body(transactionHistoryService
//				.(requestTransaction.getAccountId()));
//		}catch(Exception e) {
//			e.getLocalizedMessage();
//			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
//		}
//		return response;
		return null;
	}
}
