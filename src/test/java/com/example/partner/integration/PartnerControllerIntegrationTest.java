package com.example.partner.integration;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.partner.controller.common.RequestMappings;
import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.domain.partner.Address;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerType;
import com.example.partner.infrastructure.repository.PartnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PartnerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerRepository partnerRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {

        List<Partner> partnerList = new ArrayList<Partner>();

        Address mockAddress_1 = buildMockAddress("Address Line 1-1", "Address Line 2-1", "City 1", "State 01");
        Partner mockPartner_1 = buildMockPartner("d8dd9d81-2bc1-42f3-b297-dd0e6d510d89", "Legal FirstName_1",
                "Legal LastName_1", "Prefered Name 1", PartnerType.CONTRACT, mockAddress_1);

        Address mockAddress_2 = buildMockAddress("Address Line 1-2", "Address Line 2-2", "City 2", "State 02");
        Partner mockPartner_2 = buildMockPartner("32cwd4vsv-8l4g-5sd4-n57n-dd0e634ds3ff", "Legal FirstName_2",
                "Legal LastName_2", "Prefered Name 2", PartnerType.PERMANENT, mockAddress_2);

        partnerList.add(mockPartner_1);
        partnerList.add(mockPartner_2);

        given(partnerRepository.findAll()).willReturn(partnerList);
    }

    @Test
    public void retrieveAllPartnerTest() throws Exception {

        mockMvc.perform(get(RequestMappings.RETRIEVE_PARTNERS))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].partnerUri", is("d8dd9d81-2bc1-42f3-b297-dd0e6d510d89")))
                .andExpect(jsonPath("$[0].legalFirstName", is("Legal FirstName_1")))
                .andExpect(jsonPath("$[0].legalLastName", is("Legal LastName_1")))
                .andExpect(jsonPath("$[0].preferedName", is("Prefered Name 1")))
                .andExpect(jsonPath("$[0].address1", is("Address Line 1-1")))
                .andExpect(jsonPath("$[0].address2", is("Address Line 2-1")))
                .andExpect(jsonPath("$[0].city", is("City 1"))).andExpect(jsonPath("$[0].state", is("State 01")))
                .andExpect(jsonPath("$[0].partnerType", is("CONTRACT")))

                .andExpect(jsonPath("$[1].partnerUri", is("32cwd4vsv-8l4g-5sd4-n57n-dd0e634ds3ff")))
                .andExpect(jsonPath("$[1].legalFirstName", is("Legal FirstName_2")))
                .andExpect(jsonPath("$[1].legalLastName", is("Legal LastName_2")))
                .andExpect(jsonPath("$[1].preferedName", is("Prefered Name 2")))
                .andExpect(jsonPath("$[1].address1", is("Address Line 1-2")))
                .andExpect(jsonPath("$[1].address2", is("Address Line 2-2")))
                .andExpect(jsonPath("$[1].city", is("City 2"))).andExpect(jsonPath("$[1].state", is("State 02")))
                .andExpect(jsonPath("$[1].partnerType", is("PERMANENT")));

        verify(partnerRepository, times(1)).findAll();

    }

    @Test
    public void retrievePartnerByPartnerTypeTest() throws Exception {

        List<Partner> ContractPartnerList = new ArrayList<Partner>();

        Address mockAddress_1 = buildMockAddress("Address Line 1-1", "Address Line 2-1", "City 1", "State 01");
        Partner mockPartner_1 = buildMockPartner("d8dd9d81-2bc1-42f3-b297-dd0e6d510d89", "Legal FirstName_1",
                "Legal LastName_1", "Prefered Name 1", PartnerType.CONTRACT, mockAddress_1);

        ContractPartnerList.add(mockPartner_1);
        given(partnerRepository.findByPartnerType(PartnerType.CONTRACT)).willReturn(ContractPartnerList);

        mockMvc.perform(
                get(RequestMappings.RETRIEVE_PARTNERS).param(RequestMappings.PARTNER_TYPE, PartnerType.CONTRACT.name()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].partnerUri", is("d8dd9d81-2bc1-42f3-b297-dd0e6d510d89")))
                .andExpect(jsonPath("$[0].legalFirstName", is("Legal FirstName_1")))
                .andExpect(jsonPath("$[0].legalLastName", is("Legal LastName_1")))
                .andExpect(jsonPath("$[0].preferedName", is("Prefered Name 1")))
                .andExpect(jsonPath("$[0].address1", is("Address Line 1-1")))
                .andExpect(jsonPath("$[0].address2", is("Address Line 2-1")))
                .andExpect(jsonPath("$[0].city", is("City 1"))).andExpect(jsonPath("$[0].state", is("State 01")))
                .andExpect(jsonPath("$[0].partnerType", is("CONTRACT")));

        verify(partnerRepository, times(1)).findByPartnerType(PartnerType.CONTRACT);

    }

    @Test
    public void createPartnerTest() throws Exception {

        Address mockAddress = buildMockAddress("Address Lane 01", "Address Lane 02", "City", "State");
        Partner mockPartner = buildMockPartner("fgddfgfd-2bc1-42f3-b297-ghr45b5w3455", "Legal FirstName",
                "Legal LastName", "Prefered Name", PartnerType.PERMANENT, mockAddress);

        when(partnerRepository.save(any(Partner.class))).thenReturn(mockPartner);
        
        mockMvc.perform(post(RequestMappings.CREATE_PARTNER).content(objectMapper.writeValueAsString(buildPartnerRequest()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.partnerUri", is("fgddfgfd-2bc1-42f3-b297-ghr45b5w3455")))
                .andExpect(jsonPath("$.legalFirstName", is("Legal FirstName")))
                .andExpect(jsonPath("$.legalLastName", is("Legal LastName")))
                .andExpect(jsonPath("$.preferedName", is("Prefered Name")))
                .andExpect(jsonPath("$.address1", is("Address Lane 01")))
                .andExpect(jsonPath("$.address2", is("Address Lane 02")))
                .andExpect(jsonPath("$.city", is("City"))).andExpect(jsonPath("$.state", is("State")))
                .andExpect(jsonPath("$.partnerType", is("PERMANENT")));

        verify(partnerRepository, times(1)).save(any(Partner.class));
    }

    private Address buildMockAddress(String Address1, String Address2, String City, String state) {

        Address mockAddress = new Address();
        mockAddress.setAddress1(Address1);
        mockAddress.setAddress2(Address2);
        mockAddress.setCity(City);
        mockAddress.setState(state);
        return mockAddress;
    }

    private Partner buildMockPartner(String partnerId, String firstName, String lastName, String preferedName,
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
    
    private PartnerRequest buildPartnerRequest() {
        PartnerRequest partnerRequest= new PartnerRequest();
        partnerRequest.setAddress1("Address Lane 01");
        partnerRequest.setAddress2("Address Lane 02");
        partnerRequest.setCity("City");
        partnerRequest.setState("State");
        partnerRequest.setLegalFirstName("Legal FirstName");
        partnerRequest.setLegalLastName("Legal LastName");
        partnerRequest.setPreferedName("Prefered Name");
        partnerRequest.setPartnerType("PERMANENT");
        
        return partnerRequest;
        
    }
}
