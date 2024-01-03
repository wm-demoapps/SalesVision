package com.salesvision.getLeadsData.service;


import com.salesvision.getLeadsData.model.*;
import com.salesvision.getLeadsData.model.RootResponse;

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
    * @param Content_Type Content-Type (optional)
    * @param Authorization Authorization (optional)
    * @param q q (optional)
   * @return RootResponse
   */
  @RequestLine("GET /data/v39.0/query/?q={q}")
  @Headers({
    "Accept: application/json",
    "Content-Type: {Content_Type}",

    "Authorization: {Authorization}"  })
  RootResponse restServiceVirtualControllerInvoke(@Param("Content_Type") String Content_Type, @Param("Authorization") String Authorization, @Param("q") String q);


    /**
     * 
     * 
     * Note, this is equivalent to the other <code>restServiceVirtualControllerInvoke</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link RestServiceVirtualControllerInvokeQueryParams} class that allows for
     * building up this map in a fluent style.
     * @param Content_Type Content-Type (optional)
     * @param Authorization Authorization (optional)
     * @param queryParams Map of query parameters as name-value pairs
     *   <p>The following elements may be specified in the query map:</p>
     *   <ul>
     *   <li>q - q (optional)</li>
     *   </ul>
     * @return RootResponse
     */
    @RequestLine("GET /data/v39.0/query/?q={q}")
    @Headers({
    "Accept: application/json",
        "Content-Type: {Content_Type}",

        "Authorization: {Authorization}"    })
    RootResponse restServiceVirtualControllerInvoke
    (@Param("Content_Type") String Content_Type, @Param("Authorization") String Authorization, @QueryMap(encoded=true)
    MultiValueMap<String, String> queryParams);

}
