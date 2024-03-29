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

import com.salesvision.salesvision.Leads;


/**
 * ServiceImpl object for domain model class Leads.
 *
 * @see Leads
 */
@Service("SalesVision.LeadsService")
@Validated
@EntityService(entityClass = Leads.class, serviceId = "SalesVision")
public class LeadsServiceImpl implements LeadsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeadsServiceImpl.class);


    @Autowired
    @Qualifier("SalesVision.LeadsDao")
    private WMGenericDao<Leads, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Leads, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Leads create(Leads leads) {
        LOGGER.debug("Creating a new Leads with information: {}", leads);

        Leads leadsCreated = this.wmGenericDao.create(leads);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(leadsCreated);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Leads getById(Integer leadsId) {
        LOGGER.debug("Finding Leads by id: {}", leadsId);
        return this.wmGenericDao.findById(leadsId);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Leads findById(Integer leadsId) {
        LOGGER.debug("Finding Leads by id: {}", leadsId);
        try {
            return this.wmGenericDao.findById(leadsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Leads found with id: {}", leadsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public List<Leads> findByMultipleIds(List<Integer> leadsIds, boolean orderedReturn) {
        LOGGER.debug("Finding Leads by ids: {}", leadsIds);

        return this.wmGenericDao.findByMultipleIds(leadsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SalesVisionTransactionManager")
    @Override
    public Leads update(Leads leads) {
        LOGGER.debug("Updating Leads with information: {}", leads);

        this.wmGenericDao.update(leads);
        this.wmGenericDao.refresh(leads);

        return leads;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Leads partialUpdate(Integer leadsId, Map<String, Object>leadsPatch) {
        LOGGER.debug("Partially Updating the Leads with id: {}", leadsId);

        Leads leads = getById(leadsId);

        try {
            ObjectReader leadsReader = this.objectMapper.reader().forType(Leads.class).withValueToUpdate(leads);
            leads = leadsReader.readValue(this.objectMapper.writeValueAsString(leadsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", leadsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        leads = update(leads);

        return leads;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Leads delete(Integer leadsId) {
        LOGGER.debug("Deleting Leads with id: {}", leadsId);
        Leads deleted = this.wmGenericDao.findById(leadsId);
        if (deleted == null) {
            LOGGER.debug("No Leads found with id: {}", leadsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Leads.class.getSimpleName(), leadsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public void delete(Leads leads) {
        LOGGER.debug("Deleting Leads with {}", leads);
        this.wmGenericDao.delete(leads);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Leads> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Leads");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Leads> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Leads");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SalesVision for table Leads to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SalesVision for table Leads to {} format", options.getExportType());
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
