package com.example.partner.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.partner.application.transformer.PartnerTransformer;
import com.example.partner.application.validation.PartnerRequestValidator;
import com.example.partner.controller.common.CommonConstant;
import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.controller.dto.response.PartnerResponse;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.strategy.PartnerPersistanceAndRetrievalStrategy;
import com.example.partner.platform.LoggingEvent;
import com.example.partner.platform.LoggingUtil;

/**
 * Partner service implementation
 * 
 * @author ravindu.s
 *
 */
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRequestValidator partnerValidator;

    @Autowired
    private PartnerTransformer partnerTransformer;

    @Autowired
    private PartnerPersistanceAndRetrievalStrategy partnerStrategy;
    
    final Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public PartnerResponse createPartner(PartnerRequest partnerRequest) {

        logger.info("Started create partner process.", LoggingEvent.CREATE_PARTNER);
        
        partnerValidator.validatePartnerRequest(partnerRequest);

        Partner partner = partnerTransformer.transformToPartner(partnerRequest);

        partner = partnerStrategy.createPartner(partner);
        
        Map<String, String> logMap = new HashMap<String, String>();
        logMap.put(CommonConstant.PARTNER_ID, partner.getId());
        logMap.put(CommonConstant.PARTNER_TYPE, partner.getPartnerType().name());

        LoggingUtil.logInfo(logger, "Create partner success.", logMap, LoggingEvent.CREATE_PARTNER);

        return partnerTransformer.transformToPartnerResponse(partner);
    }

    @Override
    @Transactional
    public List<PartnerResponse> retrievePartners(String partnerType) {
        
        logger.info("Started retrieve partner process.", LoggingEvent.RETRIEVE_PARTNER);

        List<Partner> partnerList = partnerStrategy.retrievePartners(partnerType);
        
        LoggingUtil.logInfo(logger, "Retrieve partners success.", LoggingEvent.RETRIEVE_PARTNER);

        return partnerTransformer.transformToPartnerResponseList(partnerList);
    }

}
