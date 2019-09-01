package com.example.partner.application.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.controller.dto.response.PartnerResponse;
import com.example.partner.domain.partner.Address;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerType;

/**
 * partner request, response transformer
 * 
 * @author ravindu.s
 *
 */
@Component
public class PartnerTransformer {

    public Partner transformToPartner(PartnerRequest partnerRequest) {
        Partner partner = new Partner();
        partner.setLegalFirstName(partnerRequest.getLegalFirstName());
        partner.setLegalLastName(partnerRequest.getLegalLastName());
        partner.setPreferedName(partnerRequest.getPreferedName());
        partner.setPartnerType(PartnerType.valueOf(partnerRequest.getPartnerType()));

        Address address = new Address();
        address.setAddress1(partnerRequest.getAddress1());
        address.setAddress2(partnerRequest.getAddress2());
        address.setCity(partnerRequest.getCity());
        address.setState(partnerRequest.getState());

        partner.setAddress(address);
        return partner;
    }

    public PartnerResponse transformToPartnerResponse(Partner partner) {
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setPartnerUri(partner.getId());
        partnerResponse.setLegalFirstName(partner.getLegalFirstName());
        partnerResponse.setLegalLastName(partner.getLegalLastName());
        partnerResponse.setPreferedName(partner.getPreferedName());
        partnerResponse.setPartnerType(partner.getPartnerType().name());
        partnerResponse.setAddress1(partner.getAddress().getAddress1());
        partnerResponse.setAddress2(partner.getAddress().getAddress2());
        partnerResponse.setCity(partner.getAddress().getCity());
        partnerResponse.setState(partner.getAddress().getState());
        return partnerResponse;
    }

    public List<PartnerResponse> transformToPartnerResponseList(List<Partner> partnerList) {
        return partnerList.stream().map(p -> {
            return transformToPartnerResponse(p);
        }).collect(Collectors.toList());
    }
}
