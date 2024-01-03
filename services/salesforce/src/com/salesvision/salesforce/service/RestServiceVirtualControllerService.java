package com.salesvision.salesforce.service;


import com.salesvision.salesforce.model.*;
import com.salesvision.salesforce.model.RootResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Object;
import org.springframework.util.MultiValueMap;
import feign.*;

public interface RestServiceVirtualControllerService {

  /**
   * 
   * 
    * @param username  (optional)
    * @param password  (optional)
    * @param client_secret  (optional)
    * @param client_id  (optional)
    * @param grant_type  (optional)
   * @return RootResponse
   */
  @RequestLine("POST /oauth2/token")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",  })
  RootResponse restServiceVirtualControllerInvoke(@Param("username") String username, @Param("password") String password, @Param("client_secret") String client_secret, @Param("client_id") String client_id, @Param("grant_type") String grant_type);

}
