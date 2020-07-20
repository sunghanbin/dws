package com.thedrinkwholesale.dws.JwtToken;

import com.thedrinkwholesale.dws.service.SellerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider  {

    @Autowired
    private CtUserDetailsService ctUserDetailsService;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    private String secretKey = "DrinkWholsale";


//    토큰 유효시간.. 30분
    private long tokenValidTime = 30 * 60 * 1000L;


//    객체 초기화 secretKey 를 Base64로 인코딩한다.
    @PostConstruct
    protected void init()  {

        String EcSecretKey = passwordEncoder.encode(secretKey);

        secretKey  = Base64.getEncoder().encodeToString(EcSecretKey.getBytes());


    }

//    JWT 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk); //jwt payload 에 저장되는 정보단위
        claims.put("roles",roles); // 정보 key/value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보저장
                .setIssuedAt(now) // 발행시간
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 만료시간 설정
                .signWith(SignatureAlgorithm.HS256,secretKey)  // 사용할 암호화 알고리즘
                .compact();
    }


//    jwt 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){

        UserDetails userDetails = ctUserDetailsService.loadUserByUsername(this.getUserPk(token));

        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

//    토큰에서 회원 정보 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

//    요청에서 해더에서 토큰 값 가져오기   "X-AUTH-TOKEN" : 토큰 값
    public  String resolveToken(HttpServletRequest request) {

        return request.getHeader("X-AUTH-TOKEN");
    }

//    토큰 유효성 만료일 확인
    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }



}
