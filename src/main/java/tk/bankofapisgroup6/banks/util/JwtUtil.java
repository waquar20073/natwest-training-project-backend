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
	public static final long JWT_TOKEN_VALIDITY = 1000*60*60; // 1 hour
    private String SECRET_KEY = "R$08nga2421@";
    
    public String getUsernameFromToken(String token) {
    	return extractClaim(token, Claims::getSubject);
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
    	return doGenerateToken(claims,account.getUsername());
    }
    

    private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
    
    public boolean validateToken(String token, UserDetails userDetails) {
    	final String username = getUsernameFromToken(token);
    	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

}
