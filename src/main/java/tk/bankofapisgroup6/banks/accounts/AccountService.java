package tk.bankofapisgroup6.banks.accounts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.bankofapisgroup6.banks.accounts.AccountRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService implements UserDetailsService{
	@Autowired
	AccountRepository accountRepository;
	// get balance
	public Double getBalance(long accountId){
		Optional<Account> account = accountRepository.findByAccountId(accountId);
		Double balance = null;
		if(account.isPresent()) {
			balance = account.get().getBalance();
		}else {
			throw new IllegalStateException("Account not found");
		}
		return balance;
		
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
	
	public Account getAccount(long id) {
		Optional<Account> account = accountRepository.findById(id);
		Account response = null;
		if(account.isPresent()) {
			response = account.get();
		}else {
			throw new IllegalStateException("Account not found");
		}
		return response;
	}
	
	public Account login(String username, String password) {
		Optional<Account> account = accountRepository.findByUsername(username);
		Account response = null;
		if(account.isPresent()) {
			if(account.get().getPassword().equals(password)) {
				response = account.get();
			}else {
				throw new IllegalStateException("Invalid Password");
			}
			
		}else {
			throw new UsernameNotFoundException("Username not found");
		}
		return response;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Account> account = accountRepository.findByUsername(username);
		UserDetails user = null;
		if(account.isPresent()) {
			user = account.get();
		}else {
			throw new UsernameNotFoundException("Username not found");
		}
		return user;
	}
	
	public Double getMonthExpense(String month) {
		return 0.0d;
	}
	
	public Double getMonthIncome(String month) {
		return 0.0d;
	}

	public int creditAmount(long accountId, Double creditAmount){
		return accountRepository.creditAmount(accountId, creditAmount);
	}

	public int debitAmount(long accountId, Double debitAmount){
		return accountRepository.debitAmount(accountId, debitAmount);
	}

}
