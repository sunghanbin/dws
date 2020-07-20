package com.thedrinkwholesale.dws.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface City_CodeRepository extends JpaRepository<City_Code,String> {

    List<City_Code> findAll();
}
