package com.thedrinkwholesale.dws.controller;


import com.thedrinkwholesale.dws.dto.Buyer_Member;
import com.thedrinkwholesale.dws.dto.City_Code;
import com.thedrinkwholesale.dws.dto.Sigu_Code;
import com.thedrinkwholesale.dws.service.SellerService;
import com.thedrinkwholesale.dws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    SellerService sellerService;

//    거래처 모두 검색
    @GetMapping("/buyerall")
    public Page<Buyer_Member> home(@RequestParam Integer page, Integer size) {

        return userService.buyerlist(page,size);
    }

    @GetMapping("/citycode")
    public List<City_Code> cityCodeLu() {

        return sellerService.sidocity();
    }

    @GetMapping("/sigucode")
    public List<Sigu_Code> siguCodeLu() {

        return sellerService.sigucity();
    }


}
