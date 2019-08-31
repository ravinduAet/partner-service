package com.example.partner.controller;

import static com.example.partner.controller.common.CommonConstant.HTTP_METHOD_GET;
import static com.example.partner.controller.common.CommonConstant.HTTP_METHOD_POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.partner.application.service.PartnerService;
import com.example.partner.controller.common.RequestMappings;
import com.example.partner.controller.dto.request.PartnerRequest;
import com.example.partner.controller.dto.response.PartnerResponse;
import com.example.partner.platform.HTTPResponseHandler;

import io.swagger.annotations.ApiOperation;

@RestController
public class PartnerController extends HTTPResponseHandler {

    @Autowired
    private PartnerService partnerService;

    @ApiOperation(httpMethod = HTTP_METHOD_POST, value = "Create Partner", nickname = "Create partner")
    @PostMapping(value = RequestMappings.CREATE_PARTNER)
    public @ResponseBody PartnerResponse createPartner(HttpServletRequest request, HttpServletResponse response,
            @RequestBody PartnerRequest partnerRequest) {

        PartnerResponse partnerResponse = partnerService.createPartner(partnerRequest);
        setStatusHeadersToSuccess(response);
        return partnerResponse;
    }

    @ApiOperation(httpMethod = HTTP_METHOD_GET, value = "Retrieve partners", nickname = "Retrieve partners")
    @GetMapping(value = RequestMappings.RETRIEVE_PARTNERS)
    public @ResponseBody List<PartnerResponse> retrieveAgencies(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = RequestMappings.PARTNER_TYPE, required = false) String partnerType) {

        List<PartnerResponse> partnerResponseList = partnerService.retrievePartners(partnerType);
        setStatusHeadersToSuccess(response);
        return partnerResponseList;
    }

}
