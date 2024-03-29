/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * QuoteStatus generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`QUOTE_STATUS`")
public class QuoteStatus implements Serializable {

    private Integer id;
    private String quoteStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`QUOTE_STATUS`", nullable = true, length = 255)
    public String getQuoteStatus() {
        return this.quoteStatus;
    }

    public void setQuoteStatus(String quoteStatus) {
        this.quoteStatus = quoteStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuoteStatus)) return false;
        final QuoteStatus quoteStatusInstance = (QuoteStatus) o;
        return Objects.equals(getId(), quoteStatusInstance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
