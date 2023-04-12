package com.company.web.controller;

import com.company.processors.AppPropertyGetProcessor;
import com.company.processors.AppPropertyUpdateProcessor;
import com.company.services.request.AppPropertyGetServiceRequest;
import com.company.services.request.AppPropertyUpdateServiceRequest;
import com.company.services.request.BaseRequest;
import com.company.services.rest.request.AppPropertyUpdateRequest;
import com.company.web.ApiMappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mukulbansal
 */
@RestController
public class AppPropertyController {

    @Autowired
    private AppPropertyUpdateProcessor appPropertyUpdateProcessor;

    @Autowired
    private AppPropertyGetProcessor appPropertyGetProcessor;

    @RequestMapping(value = ApiMappingConstants.PROPERTY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAppProperty(
            @RequestHeader(required = true) String authorization,
            @RequestBody(required = true) AppPropertyUpdateRequest appPropertyUpdateRequest,
            HttpServletRequest request, HttpServletResponse response) {
        return appPropertyUpdateProcessor.execute(appPropertyUpdateRequest, new AppPropertyUpdateServiceRequest());
    }

    @RequestMapping(value = ApiMappingConstants.PROPERTY, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAppProperties(
            @RequestHeader(required = true) String authorization,
            @RequestHeader(required = true) String secret,
            HttpServletRequest request, HttpServletResponse response) {
        AppPropertyGetServiceRequest appPropertyGetServiceRequest = new AppPropertyGetServiceRequest();
        appPropertyGetServiceRequest.setSecret(secret);
        return appPropertyGetProcessor.execute(new BaseRequest(), appPropertyGetServiceRequest);
    }
}
