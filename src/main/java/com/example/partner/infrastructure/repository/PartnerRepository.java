package com.example.partner.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerType;

/**
 * partner repository interface. Connect to MySQL database. Use Spring data to
 * save and retrieve data.
 * 
 * @author ravindu.s
 *
 */
public interface PartnerRepository extends JpaRepository<Partner, String> {

    List<Partner> findByPartnerType(PartnerType partnerType);
}
