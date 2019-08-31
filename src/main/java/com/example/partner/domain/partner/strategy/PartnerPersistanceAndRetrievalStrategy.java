package com.example.partner.domain.partner.strategy;

import java.util.List;

import com.example.partner.domain.partner.Partner;

public interface PartnerPersistanceAndRetrievalStrategy {

    /**
     * Create Partner
     * 
     * @param partner
     *            the partner object
     * @return partner partner response
     */
    Partner createPartner(Partner partner);

    /**
     * Retrieve Partners by PartnerType or All partners
     * 
     * @param partnerType
     *            the partner type
     * @return List<Partner> List of partners
     */
    List<Partner> retrievePartners(String partnerType);
}
