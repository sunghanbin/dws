package com.thedrinkwholesale.dws.service;

import com.thedrinkwholesale.dws.dto.City_Code;
import com.thedrinkwholesale.dws.dto.City_CodeRepository;
import com.thedrinkwholesale.dws.dto.Sigu_Code;
import com.thedrinkwholesale.dws.dto.SiGu_CodeRepository;
import com.thedrinkwholesale.dws.dto.Sigu_Code;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SellerService {

    @Autowired
    private City_CodeRepository city_codeRepository;

    @Autowired
    private SiGu_CodeRepository siGu_codeRepository;


//    특별시,광역시,도단위
    public List<City_Code> sidocity() {
        return city_codeRepository.findAll();
    }

//    시,구 단위
    public List<Sigu_Code> sigucity() {
    return siGu_codeRepository.findAll();
}



}
