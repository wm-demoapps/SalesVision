/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetTopPerformersResponse implements Serializable {


    @ColumnAlias("NAME")
    private String name;

    @ColumnAlias("percent")
    private BigDecimal percent;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPercent() {
        return this.percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetTopPerformersResponse)) return false;
        final GetTopPerformersResponse getTopPerformersResponse = (GetTopPerformersResponse) o;
        return Objects.equals(getName(), getTopPerformersResponse.getName()) &&
                Objects.equals(getPercent(), getTopPerformersResponse.getPercent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getPercent());
    }
}
