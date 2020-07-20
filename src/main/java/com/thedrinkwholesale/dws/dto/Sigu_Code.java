package com.thedrinkwholesale.dws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity()
public class Sigu_Code {

    @Id
    private int mix_cd;

    private int city_cd;

    private int sigu_cd;

    private String sigu_nm;



}
