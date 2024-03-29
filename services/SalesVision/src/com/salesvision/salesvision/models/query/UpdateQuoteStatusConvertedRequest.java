/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateQuoteStatusConvertedRequest implements Serializable {


    @JsonProperty("quoteId")
    @NotNull
    private Integer quoteId;

    public Integer getQuoteId() {
        return this.quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateQuoteStatusConvertedRequest)) return false;
        final UpdateQuoteStatusConvertedRequest updateQuoteStatusConvertedRequest = (UpdateQuoteStatusConvertedRequest) o;
        return Objects.equals(getQuoteId(), updateQuoteStatusConvertedRequest.getQuoteId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuoteId());
    }
}
