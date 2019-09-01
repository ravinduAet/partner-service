package com.example.partner.application.validation;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.domain.partner.PartnerType;
import com.example.partner.platform.AccountStatusCode;

/**
 * Partner request validator
 * 
 * @author ravindu.s
 *
 */
@Component
public class PartnerRequestValidator {

    public void validatePartnerRequest(PartnerRequest partnerRequest) {
        Assert.notNull(partnerRequest, AccountStatusCode.INVALID_PARTNER.name());
        Assert.isTrue(StringUtils.isNotBlank(partnerRequest.getLegalFirstName()),
                AccountStatusCode.LEGAL_FIRST_NAME_REQUIRED.name());
        Assert.isTrue(StringUtils.isNotBlank(partnerRequest.getLegalLastName()),
                AccountStatusCode.LEGAL_LAST_NAME_REQUIRED.name());
        Assert.isTrue(StringUtils.isNotBlank(partnerRequest.getAddress1()),
                AccountStatusCode.PARTNER_ADDRESS_REQUIRED.name());
        Assert.isTrue(StringUtils.isNotBlank(partnerRequest.getCity()), AccountStatusCode.PARTNER_CITY_REQUIRED.name());
        Assert.isTrue(StringUtils.isNotBlank(partnerRequest.getState()),
                AccountStatusCode.PARTNER_STATE_REQUIRED.name());
        validatePartnerType(partnerRequest.getPartnerType());

    }

    public void validatePartnerType(String partnerType) {
        Assert.isTrue(StringUtils.isNotBlank(partnerType), AccountStatusCode.PARTNER_TYPE_REQUIRED.name());
        Assert.isTrue(EnumUtils.isValidEnum(PartnerType.class, partnerType),
                AccountStatusCode.INVALID_PARTNER_TYPE.name());

    }
}
