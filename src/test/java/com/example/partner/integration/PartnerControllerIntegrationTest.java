package com.example.partner.integration;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.partner.common.BasePartnerServiceTest;
import com.example.partner.controller.common.RequestMappings;
import com.example.partner.domain.partner.Address;
import com.example.partner.domain.partner.Partner;
import com.example.partner.domain.partner.PartnerType;
import com.example.partner.infrastructure.repository.PartnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Integration test for Partner service. This returns a mock response and make
 * sure business logic works as expected.
 * 
 * @author ravindu.s
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PartnerControllerIntegrationTest extends BasePartnerServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerRepository partnerRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {

        List<Partner> partnerList = new ArrayList<Partner>();

        Address mockAddress_1 = buildMockAddress(ADDRESS_LANE_1_1, ADDRESS_LANE_2_1, CITY_1, STATE_1);
        Partner mockPartner_1 = buildMockPartner(PARTNER_ID_1, LEGAL_FIRST_NAME_1, LEGAL_LAST_NAME_1, PREFERED_NAME_1,
                PartnerType.CONTRACT, mockAddress_1);

        Address mockAddress_2 = buildMockAddress(ADDRESS_LANE_1_2, ADDRESS_LANE_2_2, CITY_2, STATE_2);
        Partner mockPartner_2 = buildMockPartner(PARTNER_ID_2, LEGAL_FIRST_NAME_2, LEGAL_LAST_NAME_2, PREFERED_NAME_2,
                PartnerType.PERMANENT, mockAddress_2);

        partnerList.add(mockPartner_1);
        partnerList.add(mockPartner_2);

        given(partnerRepository.findAll()).willReturn(partnerList);
    }

    @Test
    public void retrieveAllPartnerTest() throws Exception {

        mockMvc.perform(get(RequestMappings.RETRIEVE_PARTNERS))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].partnerUri", is(PARTNER_ID_1)))
                .andExpect(jsonPath("$[0].legalFirstName", is(LEGAL_FIRST_NAME_1)))
                .andExpect(jsonPath("$[0].legalLastName", is(LEGAL_LAST_NAME_1)))
                .andExpect(jsonPath("$[0].preferedName", is(PREFERED_NAME_1)))
                .andExpect(jsonPath("$[0].address1", is(ADDRESS_LANE_1_1)))
                .andExpect(jsonPath("$[0].address2", is(ADDRESS_LANE_2_1))).andExpect(jsonPath("$[0].city", is(CITY_1)))
                .andExpect(jsonPath("$[0].state", is(STATE_1)))
                .andExpect(jsonPath("$[0].partnerType", is(PartnerType.CONTRACT.name())))

                .andExpect(jsonPath("$[1].partnerUri", is(PARTNER_ID_2)))
                .andExpect(jsonPath("$[1].legalFirstName", is(LEGAL_FIRST_NAME_2)))
                .andExpect(jsonPath("$[1].legalLastName", is(LEGAL_LAST_NAME_2)))
                .andExpect(jsonPath("$[1].preferedName", is(PREFERED_NAME_2)))
                .andExpect(jsonPath("$[1].address1", is(ADDRESS_LANE_1_2)))
                .andExpect(jsonPath("$[1].address2", is(ADDRESS_LANE_2_2))).andExpect(jsonPath("$[1].city", is(CITY_2)))
                .andExpect(jsonPath("$[1].state", is(STATE_2)))
                .andExpect(jsonPath("$[1].partnerType", is(PartnerType.PERMANENT.name())));

        verify(partnerRepository, times(1)).findAll();

    }

    @Test
    public void retrievePartnerByPartnerTypeTest() throws Exception {

        List<Partner> ContractPartnerList = new ArrayList<Partner>();

        Address mockAddress_1 = buildMockAddress(ADDRESS_LANE_1_1, ADDRESS_LANE_2_1, CITY_1, STATE_1);
        Partner mockPartner_1 = buildMockPartner(PARTNER_ID_1, LEGAL_FIRST_NAME_1, LEGAL_LAST_NAME_1, PREFERED_NAME_1,
                PartnerType.CONTRACT, mockAddress_1);
        
        ContractPartnerList.add(mockPartner_1);
        given(partnerRepository.findByPartnerType(PartnerType.CONTRACT)).willReturn(ContractPartnerList);

        mockMvc.perform(
                get(RequestMappings.RETRIEVE_PARTNERS).param(RequestMappings.PARTNER_TYPE, PartnerType.CONTRACT.name()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].partnerUri", is(PARTNER_ID_1)))
                .andExpect(jsonPath("$[0].legalFirstName", is(LEGAL_FIRST_NAME_1)))
                .andExpect(jsonPath("$[0].legalLastName", is(LEGAL_LAST_NAME_1)))
                .andExpect(jsonPath("$[0].preferedName", is(PREFERED_NAME_1)))
                .andExpect(jsonPath("$[0].address1", is(ADDRESS_LANE_1_1)))
                .andExpect(jsonPath("$[0].address2", is(ADDRESS_LANE_2_1))).andExpect(jsonPath("$[0].city", is(CITY_1)))
                .andExpect(jsonPath("$[0].state", is(STATE_1)))
                .andExpect(jsonPath("$[0].partnerType", is(PartnerType.CONTRACT.name())));

        verify(partnerRepository, times(1)).findByPartnerType(PartnerType.CONTRACT);

    }

    @Test
    public void createPartnerTest() throws Exception {

        Address mockAddress = buildMockAddress(ADDRESS_LANE_1, ADDRESS_LANE_2, CITY, STATE);
        Partner mockPartner = buildMockPartner(PARTNER_ID, LEGAL_FIRST_NAME, LEGAL_LAST_NAME, PREFERED_NAME,
                PartnerType.PERMANENT, mockAddress);

        when(partnerRepository.save(any(Partner.class))).thenReturn(mockPartner);
        
        mockMvc.perform(
                post(RequestMappings.CREATE_PARTNER).content(objectMapper.writeValueAsString(buildPartnerRequest()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.partnerUri", is(PARTNER_ID)))
                .andExpect(jsonPath("$.legalFirstName", is(LEGAL_FIRST_NAME)))
                .andExpect(jsonPath("$.legalLastName", is(LEGAL_LAST_NAME)))
                .andExpect(jsonPath("$.preferedName", is(PREFERED_NAME)))
                .andExpect(jsonPath("$.address1", is(ADDRESS_LANE_1)))
                .andExpect(jsonPath("$.address2", is(ADDRESS_LANE_2))).andExpect(jsonPath("$.city", is(CITY)))
                .andExpect(jsonPath("$.state", is(STATE)))
                .andExpect(jsonPath("$.partnerType", is(PartnerType.PERMANENT.name())));

        verify(partnerRepository, times(1)).save(any(Partner.class));
    }

    
}
