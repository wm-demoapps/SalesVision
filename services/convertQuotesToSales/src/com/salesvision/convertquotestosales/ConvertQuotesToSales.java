/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.convertquotestosales;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import com.salesvision.salesvision.Quotes;
import com.salesvision.salesvision.service.QuotesService;
import com.salesvision.salesvision.Sales;
import com.salesvision.salesvision.service.SalesService;

//import com.salesvision.convertquotestosales.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 *
 * NOTE: We do not recommend using method overloading on client exposed methods.
 */
@ExposeToClient
public class ConvertQuotesToSales {

    private static final Logger logger = LoggerFactory.getLogger(ConvertQuotesToSales.class);

    @Autowired
    private SecurityService securityService;

    /**
     * Updates Quotes with the Quote_Status as 'Converted' or any other State and creates a Sales record
     * 
     */
    @Autowired
    private QuotesService quotesService;
    @Autowired
    private SalesService salesService;
    public void convert(Quotes quote,Sales sale){
        quotesService.update(quote);
        salesService.create(sale);
    }
    
    public String message(String str) {
        return "Hello "+str;
    }

}
