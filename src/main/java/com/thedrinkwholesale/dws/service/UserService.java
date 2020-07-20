package com.thedrinkwholesale.dws.service;


import com.thedrinkwholesale.dws.JwtToken.JwtTokenProvider;
import com.thedrinkwholesale.dws.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private Buyer_MemberRepository buyer_memberRepository;



//    아이디 중복체크
    public boolean sameIdsh(String userid){
        System.out.println(userid);
        return userRepository.findByuserid(userid).isEmpty();
    }



    //    회원가입
    public String signUpUser( Map<String,String> user) {
        User newUser = new User();
        newUser.setUserid(user.get("userid"));
        newUser.setName(user.get("name"));
        newUser.setEmail(user.get("email"));
        newUser.setPhonNo(user.get("phonno"));
        newUser.setPassword(passwordEncoder.encode(user.get("password")));
        newUser.setRole("USER");
        String userid = userRepository.save(newUser).getUserid();

        if(userid == null){
            return "다시";
        }
        return userid;
    }

    //    로그인 & 토큰생성
    public Map<String, Object> loginUser(Map<String,String> user, HttpServletResponse res) {
        LoginFlag loginFlag = new LoginFlag();


        User shUser = userRepository.findByuserid(user.get("userid"))
                .orElse(null);
        if(shUser == null){

            loginFlag.idError();
            return loginFlag.result();
        }else{

            if (!passwordEncoder.matches(user.get("password"), shUser.getPassword())) {
                loginFlag.passwordError();
                    return loginFlag.result();
            }
        }
        Cookie cookie = new Cookie("X-AUTH-TOKEN",jwtTokenProvider.createToken(shUser.getUserid(), Collections.singletonList(shUser.getRole())));
        cookie.setPath("/");
        res.addCookie(cookie);
        loginFlag.successLogin();

        return loginFlag.result();
    }

    //    유저 정보조회
    public User userInfo(String id) {

        User iUser = userRepository.findByuserid(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원 입니다."));
        iUser.builder().password("").role("");


        return iUser;
    }

    public void AuthUser(HttpServletRequest req) {


        String token = jwtTokenProvider.resolveToken(req);

        jwtTokenProvider.getAuthentication(token);
    }


    public Page<Buyer_Member> buyerlist(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return buyer_memberRepository.findAll(pageRequest);

    }



























//    public Long singupUser(UserEntity userEntity) {
//
//
////        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//        System.out.println(userEntity.getPassword());
//
//
//
//        return userRepository.save(userEntity).getId();
//    }
//
//    public Boolean logIn(UserEntity userEntity) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        List<Object> Pck = userRepository.findByUseridAndPassword(userEntity.getUserid());
////        패스워드 매치
//        Iterator itr = Pck.iterator();
//        Object[] obj = (Object[]) itr.next();
//
//
//
//        return passwordEncoder.matches(userEntity.getPassword(),obj[1].toString());
//    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
////        Optional<UserEntity> userWrapper = userRepository.findByuserid(s);
//        List<UserEntity> userWrapper = userRepository.findByuserid(s);
//        System.out.println("userWrapper==================" + userWrapper.get(0));
//        UserEntity authUserEntity = userWrapper.get(0);
////        System.out.println(authUserEntity);
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority("GOD"));
//        User user = new User(authUserEntity.getUserid(),authUserEntity.getPassword(), authorities);
//        System.out.println(user.toString());
//        return user;
//    }
}



