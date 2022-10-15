package tk.bankofapisgroup6.banks.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tk.bankofapisgroup6.banks.accounts.Account;

@Service
public class JwtUtil {
	
	private static final long serialVersionUID = 1L;
	public static final long JWT_TOKEN_VALIDITY = 1000*60*60*24*365*100; // 1 hour
    private String SECRET_KEY = "R$08nga2421@";
    
    public long getIdFromToken(String token) {
    	return Long.parseLong(extractClaim(token, Claims::getSubject));
    }
    
    public Date getExpirationDateFromToken(String token) {
    	return extractClaim(token, Claims::getExpiration); 
    }
    
    public Boolean isTokenExpired(String token) {
    	final Date expiration = getExpirationDateFromToken(token);
    	return expiration.before(new Date());
    }
    
    public String generateToken(Account account) {
    	Map<String, Object> claims = new HashMap<>();
    	return doGenerateToken(claims,String.valueOf(account.getAccountId()));
    }
    

    private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
    
    public boolean validateToken(String token, Account userDetails) {
    	final long accountId = getIdFromToken(token);
    	return (accountId==userDetails.getAccountId() && !isTokenExpired(token));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

}
