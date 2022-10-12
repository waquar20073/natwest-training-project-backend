package tk.bankofapisgroup6.banks.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.accounts.AccountService;
import tk.bankofapisgroup6.banks.accounts.Account;
import tk.bankofapisgroup6.banks.accounts.CustomUserDetailsService;

@RequestMapping(path="api/v1/jwt")
@AllArgsConstructor
@RestController
public class JwtController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtUtil jwtUtil;
	private static Logger logger = LoggerFactory.getLogger(JwtController.class);
	
	@PostMapping(path="token")
	public ResponseEntity<?> generateToken(@RequestBody JwtREquest jwtrequest){
		Account account = null;
		try {
			account = accountService.login(jwtrequest.getUsername(), jwtrequest.getPassword());
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new IllegalStateException("Invalid Credentials");
		}
		
		String token = jwtUtil.generateToken(account);
		logger.info("Token Generated");
		return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
	}
}
