package tk.bankofapisgroup6.banks.accounts;

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
import tk.bankofapisgroup6.banks.util.JwtUtil;

@RequestMapping(path = "api/v1/accounts")
@AllArgsConstructor
@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtUtil jwutil;
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	private boolean validateToken(Map<String, String> headers, long requestAccountId) {
		String accessToken = headers.get("authorization").substring(7);
		long accountId = jwutil.getIdFromToken(accessToken);
		return accountId==requestAccountId;
	}
	
	@GetMapping(path="balance")
	public ResponseEntity<Double> getBalance(@RequestHeader Map<String, String> headers, @RequestBody Request request){
		logger.info("Checking Balance");
		if(!validateToken(headers,request.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		ResponseEntity<Double> response = null;
		try {
		response = ResponseEntity.status(HttpStatus.OK).body(accountService
				.getBalance(request.getAccountId()));
		}catch(Exception e) {
			e.getLocalizedMessage();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
}
