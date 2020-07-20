package com.thedrinkwholesale.dws.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Buyer_MemberRepository extends JpaRepository<Buyer_Member,String> {

    Page<Buyer_Member> findAll(Pageable pageable);
}
