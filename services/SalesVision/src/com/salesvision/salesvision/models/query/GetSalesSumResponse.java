/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetSalesSumResponse implements Serializable {


    @ColumnAlias("salesSum")
    private BigInteger salesSum;

    @ColumnAlias("repCount")
    private Long repCount;

    public BigInteger getSalesSum() {
        return this.salesSum;
    }

    public void setSalesSum(BigInteger salesSum) {
        this.salesSum = salesSum;
    }

    public Long getRepCount() {
        return this.repCount;
    }

    public void setRepCount(Long repCount) {
        this.repCount = repCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetSalesSumResponse)) return false;
        final GetSalesSumResponse getSalesSumResponse = (GetSalesSumResponse) o;
        return Objects.equals(getSalesSum(), getSalesSumResponse.getSalesSum()) &&
                Objects.equals(getRepCount(), getSalesSumResponse.getRepCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSalesSum(),
                getRepCount());
    }
}
