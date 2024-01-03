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
import org.springframework.context.annotation.Lazy;
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
import com.salesvision.salesvision.Quotes;
import com.salesvision.salesvision.Reps;
import com.salesvision.salesvision.Rewards;
import com.salesvision.salesvision.Tasks;


/**
 * ServiceImpl object for domain model class Reps.
 *
 * @see Reps
 */
@Service("SalesVision.RepsService")
@Validated
@EntityService(entityClass = Reps.class, serviceId = "SalesVision")
public class RepsServiceImpl implements RepsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepsServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("SalesVision.TasksService")
    private TasksService tasksService;

    @Lazy
    @Autowired
    @Qualifier("SalesVision.RewardsService")
    private RewardsService rewardsService;

    @Lazy
    @Autowired
    @Qualifier("SalesVision.BadgesService")
    private BadgesService badgesService;

    @Lazy
    @Autowired
    @Qualifier("SalesVision.QuotesService")
    private QuotesService quotesService;

    @Autowired
    @Qualifier("SalesVision.RepsDao")
    private WMGenericDao<Reps, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Reps, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Reps create(Reps reps) {
        LOGGER.debug("Creating a new Reps with information: {}", reps);

        Reps repsCreated = this.wmGenericDao.create(reps);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(repsCreated);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Reps getById(Integer repsId) {
        LOGGER.debug("Finding Reps by id: {}", repsId);
        return this.wmGenericDao.findById(repsId);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Reps findById(Integer repsId) {
        LOGGER.debug("Finding Reps by id: {}", repsId);
        try {
            return this.wmGenericDao.findById(repsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Reps found with id: {}", repsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public List<Reps> findByMultipleIds(List<Integer> repsIds, boolean orderedReturn) {
        LOGGER.debug("Finding Reps by ids: {}", repsIds);

        return this.wmGenericDao.findByMultipleIds(repsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SalesVisionTransactionManager")
    @Override
    public Reps update(Reps reps) {
        LOGGER.debug("Updating Reps with information: {}", reps);

        this.wmGenericDao.update(reps);
        this.wmGenericDao.refresh(reps);

        return reps;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Reps partialUpdate(Integer repsId, Map<String, Object>repsPatch) {
        LOGGER.debug("Partially Updating the Reps with id: {}", repsId);

        Reps reps = getById(repsId);

        try {
            ObjectReader repsReader = this.objectMapper.reader().forType(Reps.class).withValueToUpdate(reps);
            reps = repsReader.readValue(this.objectMapper.writeValueAsString(repsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", repsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        reps = update(reps);

        return reps;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public Reps delete(Integer repsId) {
        LOGGER.debug("Deleting Reps with id: {}", repsId);
        Reps deleted = this.wmGenericDao.findById(repsId);
        if (deleted == null) {
            LOGGER.debug("No Reps found with id: {}", repsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Reps.class.getSimpleName(), repsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SalesVisionTransactionManager")
    @Override
    public void delete(Reps reps) {
        LOGGER.debug("Deleting Reps with {}", reps);
        this.wmGenericDao.delete(reps);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Reps> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Reps");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Reps> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Reps");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SalesVision for table Reps to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SalesVision for table Reps to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Badges> findAssociatedBadgeses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated badgeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("reps.id = '" + id + "'");

        return badgesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Tasks> findAssociatedTaskses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated taskses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("reps.id = '" + id + "'");

        return tasksService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Quotes> findAssociatedQuoteses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated quoteses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("reps.id = '" + id + "'");

        return quotesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Reps> findAssociatedRepsesForReportingHead(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated repsesForReportingHead");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("repsByReportingHead.id = '" + id + "'");

        return findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "SalesVisionTransactionManager")
    @Override
    public Page<Rewards> findAssociatedRewardses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated rewardses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("reps.id = '" + id + "'");

        return rewardsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service TasksService instance
     */
    protected void setTasksService(TasksService service) {
        this.tasksService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service RewardsService instance
     */
    protected void setRewardsService(RewardsService service) {
        this.rewardsService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service BadgesService instance
     */
    protected void setBadgesService(BadgesService service) {
        this.badgesService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service QuotesService instance
     */
    protected void setQuotesService(QuotesService service) {
        this.quotesService = service;
    }

}