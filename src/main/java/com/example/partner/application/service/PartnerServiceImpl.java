package com.example.partner.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.partner.application.transformer.PartnerTransformer;
import com.example.partner.application.validation.PartnerRequestValidator;
import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.controller.dto.response.PartnerResponse;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.strategy.PartnerPersistanceAndRetrievalStrategy;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRequestValidator partnerValidator;

    @Autowired
    private PartnerTransformer partnerTransformer;

    @Autowired
    private PartnerPersistanceAndRetrievalStrategy partnerStrategy;

    @Override
    @Transactional
    public PartnerResponse createPartner(PartnerRequest partnerRequest) {

        partnerValidator.validatePartnerRequest(partnerRequest);

        Partner partner = partnerTransformer.transformToPartner(partnerRequest);

        partner = partnerStrategy.createPartner(partner);

        return partnerTransformer.transformToPartnerResponse(partner);
    }

    @Override
    @Transactional
    public List<PartnerResponse> retrievePartners(String partnerType) {

        List<Partner> partnerList = partnerStrategy.retrievePartners(partnerType);

        return partnerTransformer.transformToPartnerResponseList(partnerList);
    }

}
