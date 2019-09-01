package com.example.partner.transformer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.partner.application.transformer.PartnerTransformer;
import com.example.partner.common.BasePartnerServiceTest;
import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.controller.dto.response.PartnerResponse;
import com.example.partner.domain.partner.Address;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerType;

/**
 * Transformer test case to check dto to domain and domain to dto
 * transformation.
 * 
 * @author ravindu.s
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PartnerTransformerTest extends BasePartnerServiceTest {

    @Autowired
    private PartnerTransformer partnerTransformer;

    @Test
    public void transformToPartnerDomainTest() {

        PartnerRequest partnerRequestDto = buildPartnerRequest();
        Partner partnerDomain = partnerTransformer.transformToPartner(partnerRequestDto);
        Assert.assertNotNull("Partner domain cannot be null", partnerDomain);
        Assert.assertEquals("Partner domain address should exists Partner dto address 1",
                partnerRequestDto.getAddress1(), partnerDomain.getAddress().getAddress1());
        Assert.assertEquals("Partner domain address should exists Partner dto address 2",
                partnerRequestDto.getAddress2(), partnerDomain.getAddress().getAddress2());
        Assert.assertEquals("Partner domain address should exists Partner dto city", partnerRequestDto.getCity(),
                partnerDomain.getAddress().getCity());
        Assert.assertEquals("Partner domain address should exists Partner dto state", partnerRequestDto.getState(),
                partnerDomain.getAddress().getState());
        Assert.assertEquals("Partner domain first legal name should equal to Partner dto first legal name",
                partnerRequestDto.getLegalFirstName(), partnerDomain.getLegalFirstName());
        Assert.assertEquals("Partner domain last legal name should equal to Partner dto last legal name",
                partnerRequestDto.getLegalLastName(), partnerDomain.getLegalLastName());
        Assert.assertEquals("Partner domain prefered name should equal to Partner dto prefered name",
                partnerRequestDto.getPreferedName(), partnerDomain.getPreferedName());
        Assert.assertEquals("Partner domain partner type name should equal to Partner dto partner type",
                partnerRequestDto.getPartnerType(), partnerDomain.getPartnerType().name());

    }

    @Test
    public void transformToPartnerDtoResponseTest() {

        Address mockAddress = buildMockAddress(ADDRESS_LANE_1_1, ADDRESS_LANE_2_1, CITY_1, STATE_1);
        Partner mockPartner = buildMockPartner(PARTNER_ID_1, LEGAL_FIRST_NAME_1, LEGAL_LAST_NAME_1, PREFERED_NAME_1,
                PartnerType.CONTRACT, mockAddress);

        PartnerResponse partnerResponseDto = partnerTransformer.transformToPartnerResponse(mockPartner);
        Assert.assertNotNull("Partner response cannot be null", partnerResponseDto);
        Assert.assertEquals("Partner response address 1 should exists Partner domain address",
                mockPartner.getAddress().getAddress1(), partnerResponseDto.getAddress1());
        Assert.assertEquals("Partner response address 2 should exists Partner domain address",
                mockPartner.getAddress().getAddress2(), partnerResponseDto.getAddress2());
        Assert.assertEquals("Partner response city should exists Partner domain city",
                mockPartner.getAddress().getCity(), partnerResponseDto.getCity());
        Assert.assertEquals("Partner response state should exists Partner domain state",
                mockPartner.getAddress().getState(), partnerResponseDto.getState());
        Assert.assertEquals("Partner response first legal name should exists Partner domain first legal name",
                mockPartner.getLegalFirstName(), partnerResponseDto.getLegalFirstName());
        Assert.assertEquals("Partner response last legal name should exists Partner domain last legal name",
                mockPartner.getLegalFirstName(), partnerResponseDto.getLegalFirstName());
        Assert.assertEquals("Partner response prefered name should exists Partner domain prefered name",
                mockPartner.getPreferedName(), partnerResponseDto.getPreferedName());
        Assert.assertEquals("Partner response partner type should exists Partner domain partner type",
                mockPartner.getPartnerType().name(), partnerResponseDto.getPartnerType());

    }
}
