package fit.iuh.dulichgiare.config;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import fit.iuh.dulichgiare.entity.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtTokenService {

    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(20);
    @Value("${jwt.secret}")
    private String secretKey;
    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails) {
        final Instant now = Instant.now();
        System.out.println(this.hmac512);
        System.out.println(secretKey.getBytes());
        return JWT.create().withSubject(userDetails.getUsername()).withIssuer("app").withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis())).sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            log.warn("token invalid: {}", verificationEx.getMessage());
            return null;
        }
    }

    public Long getUserIdFromToken(String token) throws NoSuchAlgorithmException, InvalidKeyException {

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(secretKeySpec);
//        
//        String data = "Hello, world!";
        byte[] encodedData = mac.doFinal(secretKey.getBytes());
        String encodedString = Base64.getEncoder().encodeToString(encodedData);

        JwtParser parser = Jwts.parserBuilder().setSigningKey(encodedString).build();
        Claims claims = parser.parseClaimsJws(token).getBody();
        System.out.println(claims);
        UserToken userToken = new UserToken();
        userToken.setUserId(claims.get("id", String.class));

        return Long.parseLong(userToken.getUserId());
    }

}
