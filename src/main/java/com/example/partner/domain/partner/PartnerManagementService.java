package com.example.partner.domain.partner;

import java.util.List;

public interface PartnerManagementService {

    /**
     * Create Partner service
     * 
     * @param partner
     *            the partner entity object
     * @return Partner the Partner response
     */
    Partner createPartner(Partner partner);

    /**
     * Retrieve Partners by Partner type
     * 
     * @param partnerType
     *            the partner type
     * @return List<Partner> List of Partners
     */
    List<Partner> retrievePartnersByPartnerType(PartnerType partnerType);

    List<Partner> retrievePartners();

}
