package com.thedrinkwholesale.dws.JwtToken;

import com.thedrinkwholesale.dws.dto.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        return (UserDetails) userRepository.findByuserid(userid).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));


    }
}
