package com.jachin.blog.utils;

import com.jachin.blog.pojo.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:18
 */
public class JwtUtils {
    public static final String TOKEN_HEADER = "authorization";
    public static final String TOKEN_HEAD = "Bearer ";
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;
    private static final long REFRESH_EXPIRE = 1000 * 60 * 60 * 24 * 9;
    private static final String SECRET = "cyRQ1NPNrP";

    public static String getJwtToken(UserEntity user) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("username", user.getUsername())
                .claim("id", user.getId())
                .claim("role", "user")
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String getRefreshToken(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("refresh")
                .setIssuedAt(new Date())
                .claim("username", username)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static boolean checkToken(String jwtToken) {
        if (ObjectUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("UserToken");
            if (ObjectUtils.isEmpty(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getUsernameByJwtToken(String token) {
        String username = null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            username = (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

}
