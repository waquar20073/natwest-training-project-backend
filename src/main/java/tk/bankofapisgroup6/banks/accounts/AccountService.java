package tk.bankofapisgroup6.banks.accounts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.bankofapisgroup6.banks.accounts.AccountRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	// get balance
	public String getBalance(String username){
		Optional<Account> account = accountRepository.findByUsername(username);
		String response = null;
		if(!account.isPresent()) {
			response = "Account Not Present";
		return response;
		
	}
	// get account details
	// login
	public String getApiToken(String username, String password) {
		/** return api_token on successful login */
		Optional<Account> account = accountRepository.findByUsername(username);
		String response = null;
		if(!account.isPresent()) {
			response = "Account Not Present";
		}else if(account.get().getPassword().equals(password)) {
			response = account.get().getApiKey();
		}else {
			response = "Invalid Password";
		}
		return response;
	}
	
	public Account getAccount(long account_id) {
		Optional<Account> account = accountRepository.findById(account_id);
		Account response = null;
		if(account.isPresent()) {
			response = account.get();
		}else {
			throw new IllegalStateException("Account not found");
		}
		return response;
	}
}
