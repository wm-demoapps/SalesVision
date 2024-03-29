/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetDealStatusResponse implements Serializable {


    @ColumnAlias("QUOTE_STATUS")
    private String quoteStatus;

    @ColumnAlias("size")
    private BigInteger size;

    @ColumnAlias("volume")
    private Long volume;

    public String getQuoteStatus() {
        return this.quoteStatus;
    }

    public void setQuoteStatus(String quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public BigInteger getSize() {
        return this.size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public Long getVolume() {
        return this.volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetDealStatusResponse)) return false;
        final GetDealStatusResponse getDealStatusResponse = (GetDealStatusResponse) o;
        return Objects.equals(getQuoteStatus(), getDealStatusResponse.getQuoteStatus()) &&
                Objects.equals(getSize(), getDealStatusResponse.getSize()) &&
                Objects.equals(getVolume(), getDealStatusResponse.getVolume());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuoteStatus(),
                getSize(),
                getVolume());
    }
}
