package ten.give.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    // Token 에서 UserId 꺼내기
    public static Long getUserId(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId",Long.class);
    }

    /*
    // Token 에서 UserName 꺼내기
    public static String getUsername(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userName",String.class);
    }*/

    // Token 만료 여부
    public static boolean isExpired(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    // 토큰 만들기
    public static String createJwt(Long userId, String secretKey, Long expiredMs){
        Claims claims = Jwts.claims(); // 원하는 정보를 담아 둘 수 있는 공간
        claims.put("userId",userId); // Map 형식
        return Jwts.builder() // builder ptn 지원
                .setClaims(claims) //claims 등록
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발생 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료 시간 등록
                .signWith(SignatureAlgorithm.HS256, secretKey) //sign 방식 HS256 암호화 알고리즘 사용, key -> secretKey
                .compact();
    }

}
