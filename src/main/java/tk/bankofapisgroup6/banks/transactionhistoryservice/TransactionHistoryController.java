package tk.bankofapisgroup6.banks.transactionhistoryservice;

import java.util.List;

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
	private TransactionHistoryService transactionHistoryService;
	
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getTransactions(){
		
		return null;
	}
	
	@PostMapping("newTransaction")
	public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transactiondto) {
		
		return null;
	}
}
