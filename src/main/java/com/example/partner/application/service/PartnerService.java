package com.example.partner.application.service;

import java.util.List;

import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.controller.dto.response.PartnerResponse;

public interface PartnerService {

    /**
     * Create Partner service
     * 
     * @param partnerRequest
     *            the partner dto request
     * @return PartnerResponse the partner dto response
     */
    PartnerResponse createPartner(PartnerRequest partnerRequest);

    /**
     * Retrieve All partners
     * 
     * @param partnerType
     *            the partner type
     * @return List<PartnerResponse> the list of Partners
     */
    List<PartnerResponse> retrievePartners(String partnerType);
}
