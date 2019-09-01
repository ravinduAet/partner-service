package com.example.partner.domain.partner.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.partner.application.validation.PartnerRequestValidator;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerManagementService;
import com.example.partner.domain.partner.PartnerType;

/**
 * Partner persistance and retrieval service implementation. If there are
 * different retrieveal processes, then we can have seperate retrieval
 * startegies implementation.
 * 
 * @author ravindu.s
 *
 */
@Service
public class PartnerPersistanceAndRetrievalStrategyImpl implements PartnerPersistanceAndRetrievalStrategy {

    @Autowired
    private PartnerManagementService partnerManagementService;

    @Autowired
    private PartnerRequestValidator partnerValidator;

    @Override
    public Partner createPartner(Partner partner) {
        return partnerManagementService.createPartner(partner);
    }

    @Override
    public List<Partner> retrievePartners(String partnerType) {
        if (partnerType != null) {
            partnerValidator.validatePartnerType(partnerType);
            return partnerManagementService.retrievePartnersByPartnerType(PartnerType.valueOf(partnerType));
        }
        return partnerManagementService.retrievePartners();
    }

}
