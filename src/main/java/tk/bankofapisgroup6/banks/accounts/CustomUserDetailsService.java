package tk.bankofapisgroup6.banks.accounts;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("abc")) {
			return new User("abc","pass",new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found");
		}
	}
	
}
