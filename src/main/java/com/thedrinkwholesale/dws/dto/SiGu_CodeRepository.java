package com.thedrinkwholesale.dws.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiGu_CodeRepository extends JpaRepository<Sigu_Code,String> {

    List<Sigu_Code> findAll();
}
