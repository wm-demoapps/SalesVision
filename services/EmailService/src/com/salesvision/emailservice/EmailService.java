/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.emailservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import org.springframework.mail.SimpleMailMessage;
import com.wavemaker.connector.email.EmailConnector;
import java.util.HashMap;
import java.util.Map;
import com.wavemaker.connector.exception.EmailTemplateNotFoundException;
//import com.salesvision.emailservice.model.*;

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
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private EmailConnector emailConnector;
    
    @Value("${email.server.username}")
    private String fromEmailAddress;

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
        public void sendEmailWithTemplate(String toEmailAddress, String emailSubject, String repName, String clientName) {
        logger.info("Sending the email to {} with subject {} ", toEmailAddress, emailSubject);
        
        String[] recipientList = toEmailAddress.split(",");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailSubject);
        message.setTo(recipientList);
        message.setFrom(fromEmailAddress);
        Map<String, String> props = new HashMap<>();
        props.put("rep", repName);
        props.put("client", clientName);
        try {
            emailConnector.sendSimpleMailMessageWithTemplate(message, "templates/mail_template.txt", props);
        } catch (EmailTemplateNotFoundException e) {
            throw new RuntimeException("Email template not found", e);
        }
    }
    
     public void sendMailWithSimpleMessage(String toEmailAddress, String emailSubject, String emailMessage) {
        logger.info("Sending the email to {} with subject {} and message {}", toEmailAddress, emailSubject, emailMessage);

        String[] recipientList = toEmailAddress.split(",");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailAddress);
        simpleMailMessage.setTo(recipientList);
        simpleMailMessage.setSubject(emailSubject);
        simpleMailMessage.setText(emailMessage);
        emailConnector.sendSimpleMailMessage(simpleMailMessage);
    }

}
