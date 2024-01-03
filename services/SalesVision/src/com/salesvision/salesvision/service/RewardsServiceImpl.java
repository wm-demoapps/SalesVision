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

import com.salesvision.salesvision.Rewards;


/**
 * ServiceImpl object for domain model class Rewards.
 *
 * @see Rewards
 */
@Service("SalesVision.RewardsService")
@Validated
@EntityService(entityClass = Rewards.class, serviceId = "SalesVision")
public class RewardsServiceImpl implements RewardsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardsServiceImpl.class);


    @Autowired
    @Qualifier("SalesVision.RewardsDao")
    private WMGenericDao<Rewards, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Rewards, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Rewards create(Rewards rewards) {
        LOGGER.debug("Creating a new Rewards with information: {}", rewards);

        Rewards rewardsCreated = this.wmGenericDao.create(rewards);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(rewardsCreated);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Rewards getById(Integer rewardsId) {
        LOGGER.debug("Finding Rewards by id: {}", rewardsId);
        return this.wmGenericDao.findById(rewardsId);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Rewards findById(Integer rewardsId) {
        LOGGER.debug("Finding Rewards by id: {}", rewardsId);
        try {
            return this.wmGenericDao.findById(rewardsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Rewards found with id: {}", rewardsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public List<Rewards> findByMultipleIds(List<Integer> rewardsIds, boolean orderedReturn) {
        LOGGER.debug("Finding Rewards by ids: {}", rewardsIds);

        return this.wmGenericDao.findByMultipleIds(rewardsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SalesVisionTransactionManager")
    @Override
    public Rewards update(Rewards rewards) {
        LOGGER.debug("Updating Rewards with information: {}", rewards);

        this.wmGenericDao.update(rewards);
        this.wmGenericDao.refresh(rewards);

        return rewards;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Rewards partialUpdate(Integer rewardsId, Map<String, Object>rewardsPatch) {
        LOGGER.debug("Partially Updating the Rewards with id: {}", rewardsId);

        Rewards rewards = getById(rewardsId);

        try {
            ObjectReader rewardsReader = this.objectMapper.reader().forType(Rewards.class).withValueToUpdate(rewards);
            rewards = rewardsReader.readValue(this.objectMapper.writeValueAsString(rewardsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", rewardsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        rewards = update(rewards);

        return rewards;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Rewards delete(Integer rewardsId) {
        LOGGER.debug("Deleting Rewards with id: {}", rewardsId);
        Rewards deleted = this.wmGenericDao.findById(rewardsId);
        if (deleted == null) {
            LOGGER.debug("No Rewards found with id: {}", rewardsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Rewards.class.getSimpleName(), rewardsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public void delete(Rewards rewards) {
        LOGGER.debug("Deleting Rewards with {}", rewards);
        this.wmGenericDao.delete(rewards);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Rewards> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Rewards");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Rewards> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Rewards");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SalesVision for table Rewards to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SalesVision for table Rewards to {} format", options.getExportType());
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
