package com.example.partner.domain.partner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.partner.infrastructure.repository.PartnerRepository;

@Service
public class PartnerManagementServiceImpl implements PartnerManagementService {

    @Autowired
    private PartnerRepository partnerRepository;
    
    @Override
    public Partner createPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    @Override
    public List<Partner> retrievePartnersByPartnerType(PartnerType partnerType) {
        return partnerRepository.findByPartnerType(partnerType);
    }

    @Override
    public List<Partner> retrievePartners() {
        return partnerRepository.findAll();
    }

}
