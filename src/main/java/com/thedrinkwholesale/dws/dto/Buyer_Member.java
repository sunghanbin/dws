package com.thedrinkwholesale.dws.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Buyer_Member {
//    구매자

    @Id
    private String product_cd;

    private String product_nm;

    private String owner_nm;

    private String phon_nb;

    private String st_code;

    private String event_cd;

    private String address;

    private int city_cd;

    private int sigu_cd;


}
