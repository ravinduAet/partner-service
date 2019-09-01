package com.example.partner.common;

import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.domain.partner.Address;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerType;

public abstract class BasePartnerServiceTest implements TestConstants {

    public Address buildMockAddress(String Address1, String Address2, String City, String state) {

        Address mockAddress = new Address();
        mockAddress.setAddress1(Address1);
        mockAddress.setAddress2(Address2);
        mockAddress.setCity(City);
        mockAddress.setState(state);
        return mockAddress;
    }

    public Partner buildMockPartner(String partnerId, String firstName, String lastName, String preferedName,
            PartnerType partnerType, Address address) {
        Partner mockPartner = new Partner();
        mockPartner.setId(partnerId);
        mockPartner.setLegalFirstName(firstName);
        mockPartner.setLegalLastName(lastName);
        mockPartner.setPreferedName(preferedName);
        mockPartner.setPartnerType(partnerType);
        mockPartner.setAddress(address);
        return mockPartner;

    }

    public PartnerRequest buildPartnerRequest() {
        PartnerRequest partnerRequest = new PartnerRequest();
        partnerRequest.setAddress1(ADDRESS_LANE_1);
        partnerRequest.setAddress2(ADDRESS_LANE_2);
        partnerRequest.setCity(CITY);
        partnerRequest.setState(STATE);
        partnerRequest.setLegalFirstName(LEGAL_FIRST_NAME);
        partnerRequest.setLegalLastName(LEGAL_LAST_NAME);
        partnerRequest.setPreferedName(PREFERED_NAME);
        partnerRequest.setPartnerType(PartnerType.PERMANENT.name());

        return partnerRequest;

    }
}
