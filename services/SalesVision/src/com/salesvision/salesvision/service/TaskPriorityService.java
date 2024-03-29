/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;

import com.salesvision.salesvision.TaskPriority;
import com.salesvision.salesvision.Tasks;

/**
 * Service object for domain model class {@link TaskPriority}.
 */
public interface TaskPriorityService {

    /**
     * Creates a new TaskPriority. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TaskPriority if any.
     *
     * @param taskPriorityInstance Details of the TaskPriority to be created; value cannot be null.
     * @return The newly created TaskPriority.
     */
    TaskPriority create(@Valid TaskPriority taskPriorityInstance);


	/**
     * Returns TaskPriority by given id if exists.
     *
     * @param taskpriorityId The id of the TaskPriority to get; value cannot be null.
     * @return TaskPriority associated with the given taskpriorityId.
	 * @throws EntityNotFoundException If no TaskPriority is found.
     */
    TaskPriority getById(Integer taskpriorityId);

    /**
     * Find and return the TaskPriority by given id if exists, returns null otherwise.
     *
     * @param taskpriorityId The id of the TaskPriority to get; value cannot be null.
     * @return TaskPriority associated with the given taskpriorityId.
     */
    TaskPriority findById(Integer taskpriorityId);

	/**
     * Find and return the list of TaskPriorities by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param taskpriorityIds The id's of the TaskPriority to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return TaskPriorities associated with the given taskpriorityIds.
     */
    List<TaskPriority> findByMultipleIds(List<Integer> taskpriorityIds, boolean orderedReturn);


    /**
     * Updates the details of an existing TaskPriority. It replaces all fields of the existing TaskPriority with the given taskPriorityInstance.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TaskPriority if any.
     *
     * @param taskPriorityInstance The details of the TaskPriority to be updated; value cannot be null.
     * @return The updated TaskPriority.
     * @throws EntityNotFoundException if no TaskPriority is found with given input.
     */
    TaskPriority update(@Valid TaskPriority taskPriorityInstance);


    /**
     * Partially updates the details of an existing TaskPriority. It updates only the
     * fields of the existing TaskPriority which are passed in the taskPriorityInstancePatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TaskPriority if any.
     *
     * @param taskpriorityId The id of the TaskPriority to be deleted; value cannot be null.
     * @param taskPriorityInstancePatch The partial data of TaskPriority which is supposed to be updated; value cannot be null.
     * @return The updated TaskPriority.
     * @throws EntityNotFoundException if no TaskPriority is found with given input.
     */
    TaskPriority partialUpdate(Integer taskpriorityId, Map<String, Object> taskPriorityInstancePatch);

    /**
     * Deletes an existing TaskPriority with the given id.
     *
     * @param taskpriorityId The id of the TaskPriority to be deleted; value cannot be null.
     * @return The deleted TaskPriority.
     * @throws EntityNotFoundException if no TaskPriority found with the given id.
     */
    TaskPriority delete(Integer taskpriorityId);

    /**
     * Deletes an existing TaskPriority with the given object.
     *
     * @param taskPriorityInstance The instance of the TaskPriority to be deleted; value cannot be null.
     */
    void delete(TaskPriority taskPriorityInstance);

    /**
     * Find all TaskPriorities matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching TaskPriorities.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<TaskPriority> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all TaskPriorities matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching TaskPriorities.
     *
     * @see Pageable
     * @see Page
     */
    Page<TaskPriority> findAll(String query, Pageable pageable);

    /**
     * Exports all TaskPriorities matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all TaskPriorities matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the TaskPriorities in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the TaskPriority.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated taskses for given TaskPriority id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Tasks instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Tasks> findAssociatedTaskses(Integer id, Pageable pageable);

}
