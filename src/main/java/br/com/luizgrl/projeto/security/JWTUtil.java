package br.com.luizgrl.projeto.security;

        import java.nio.charset.Charset;
        import java.security.Key;
        import java.time.Clock;
        import java.util.Base64;
        import java.util.Date;

        import io.jsonwebtoken.ExpiredJwtException;
        import io.jsonwebtoken.security.Keys;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Component;

        import io.jsonwebtoken.Claims;
        import io.jsonwebtoken.Jwts;
        import io.jsonwebtoken.SignatureAlgorithm;

        import javax.crypto.SecretKey;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512,secret)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .compact();
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            System.out.println(now);
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {

            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {
        Claims body = null;
        try{
            //Isso tava dando bug n sei pq mas consegui arrumar essa desgraça acredito que seja pq estava usando secret.getBytes
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
