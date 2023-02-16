package web.seminar.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import web.seminar.domain.entity.User;
import web.seminar.domain.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtService {
    private final UserRepository userRepository;

    @Value("${spring.jwt.secret-key}")
    private String SECRET_KEY;

    private Long accessTokenValidTime = 1000L * 60 * 60 * 24;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createToken(String value, Long tokenValidTime){
        Claims claims = Jwts.claims().setSubject(value);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createAccessToken(Long userId){
        return this.createToken(userId.toString(), accessTokenValidTime);
    }

    public String resolveAccessToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    public Long getMemberInfo(String token) {
        return Long.valueOf(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
    }

    public Authentication getAuthentication(String token){
        User user = userRepository.findById(getMemberInfo(token))
                .orElseThrow(() -> new IllegalArgumentException("토큰으로 유저 정보를 확인할 수 없습니다."));
        return new UsernamePasswordAuthenticationToken(user, "");
    }

    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().after(new Date());
        }
        catch (Exception e){
            return false;
        }
    }
}
