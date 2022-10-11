package tk.bankofapisgroup6.banks.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping(path = "api/v1/accounts")
@AllArgsConstructor
@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping(path="balance")
	public ResponseEntity<Double> getBalance(){
		ResponseEntity<Double> response = null;
		String username="johny",token=null;
		response = ResponseEntity.status(HttpStatus.OK).body(Double.parseDouble(accountService.getBalance(username,token)));
		return response;
	}
	@GetMapping(path="token")
	public ResponseEntity<Double> getToken(){
		ResponseEntity<Double> response = null;
		String username="johny",password="123";
		response = ResponseEntity.status(HttpStatus.OK).body(Double.parseDouble(accountService.getApiToken(username,password)));
		return response;
	}
}
