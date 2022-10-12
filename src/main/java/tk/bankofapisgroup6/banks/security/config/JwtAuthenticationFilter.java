package tk.bankofapisgroup6.banks.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.accounts.AccountService;
import tk.bankofapisgroup6.banks.accounts.CustomUserDetailsService;
import tk.bankofapisgroup6.banks.util.JwtUtil;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private AccountService accountService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get header
		String requestTokenHeader = request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		//check format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken=requestTokenHeader.substring(7);
			try {
				username = jwtutil.getUsernameFromToken(jwtToken);
			}catch(Exception e) {
				e.printStackTrace();
			}
			UserDetails userDetails = accountService.loadUserByUsername(username);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				throw new IllegalStateException("Token is not validated");
			}
		}
		filterChain.doFilter(request, response);
	}

}
