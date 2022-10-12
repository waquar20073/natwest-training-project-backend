package tk.bankofapisgroup6.banks.accounts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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

	
	public UserDetails loadUserById(long accountId) {
		Optional<Account> account = accountRepository.findByAccountId(accountId);
		if(account.isPresent()) {
			return account.get();
		}else {
			throw new IllegalStateException("Account Id not Found");
		}
	}

	public double updateBalance(long accountId, double amount) {
		Optional<Account> account = accountRepository.findByAccountId(accountId);
		if(account.isPresent()) {
			double balance = account.get().getBalance();
			balance = balance+amount;
			return accountRepository.updateBalance(accountId, balance);
		}else {
			throw new IllegalStateException("Account Id not Found");
		}
	}

	public int creditAmount(long accountId, Double creditAmount){
		return accountRepository.creditAmount(accountId, creditAmount);
	}

	public int debitAmount(long accountId, Double debitAmount){
		return accountRepository.debitAmount(accountId, debitAmount);
	}

}
