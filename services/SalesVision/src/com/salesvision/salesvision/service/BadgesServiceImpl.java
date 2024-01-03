/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.annotations.EntityService;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;

import com.salesvision.salesvision.Badges;


/**
 * ServiceImpl object for domain model class Badges.
 *
 * @see Badges
 */
@Service("SalesVision.BadgesService")
@Validated
@EntityService(entityClass = Badges.class, serviceId = "SalesVision")
public class BadgesServiceImpl implements BadgesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BadgesServiceImpl.class);


    @Autowired
    @Qualifier("SalesVision.BadgesDao")
    private WMGenericDao<Badges, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Badges, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Badges create(Badges badges) {
        LOGGER.debug("Creating a new Badges with information: {}", badges);

        Badges badgesCreated = this.wmGenericDao.create(badges);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(badgesCreated);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Badges getById(Integer badgesId) {
        LOGGER.debug("Finding Badges by id: {}", badgesId);
        return this.wmGenericDao.findById(badgesId);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Badges findById(Integer badgesId) {
        LOGGER.debug("Finding Badges by id: {}", badgesId);
        try {
            return this.wmGenericDao.findById(badgesId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Badges found with id: {}", badgesId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public List<Badges> findByMultipleIds(List<Integer> badgesIds, boolean orderedReturn) {
        LOGGER.debug("Finding Badges by ids: {}", badgesIds);

        return this.wmGenericDao.findByMultipleIds(badgesIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SalesVisionTransactionManager")
    @Override
    public Badges update(Badges badges) {
        LOGGER.debug("Updating Badges with information: {}", badges);

        this.wmGenericDao.update(badges);
        this.wmGenericDao.refresh(badges);

        return badges;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Badges partialUpdate(Integer badgesId, Map<String, Object>badgesPatch) {
        LOGGER.debug("Partially Updating the Badges with id: {}", badgesId);

        Badges badges = getById(badgesId);

        try {
            ObjectReader badgesReader = this.objectMapper.reader().forType(Badges.class).withValueToUpdate(badges);
            badges = badgesReader.readValue(this.objectMapper.writeValueAsString(badgesPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", badgesPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        badges = update(badges);

        return badges;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Badges delete(Integer badgesId) {
        LOGGER.debug("Deleting Badges with id: {}", badgesId);
        Badges deleted = this.wmGenericDao.findById(badgesId);
        if (deleted == null) {
            LOGGER.debug("No Badges found with id: {}", badgesId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Badges.class.getSimpleName(), badgesId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public void delete(Badges badges) {
        LOGGER.debug("Deleting Badges with {}", badges);
        this.wmGenericDao.delete(badges);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Badges> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Badges");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Badges> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Badges");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SalesVision for table Badges to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SalesVision for table Badges to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}
