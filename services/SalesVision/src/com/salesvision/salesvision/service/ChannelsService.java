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

import com.salesvision.salesvision.Channels;
import com.salesvision.salesvision.Reps;

/**
 * Service object for domain model class {@link Channels}.
 */
public interface ChannelsService {

    /**
     * Creates a new Channels. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Channels if any.
     *
     * @param channels Details of the Channels to be created; value cannot be null.
     * @return The newly created Channels.
     */
    Channels create(@Valid Channels channels);


	/**
     * Returns Channels by given id if exists.
     *
     * @param channelsId The id of the Channels to get; value cannot be null.
     * @return Channels associated with the given channelsId.
	 * @throws EntityNotFoundException If no Channels is found.
     */
    Channels getById(Integer channelsId);

    /**
     * Find and return the Channels by given id if exists, returns null otherwise.
     *
     * @param channelsId The id of the Channels to get; value cannot be null.
     * @return Channels associated with the given channelsId.
     */
    Channels findById(Integer channelsId);

	/**
     * Find and return the list of Channels by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param channelsIds The id's of the Channels to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Channels associated with the given channelsIds.
     */
    List<Channels> findByMultipleIds(List<Integer> channelsIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Channels. It replaces all fields of the existing Channels with the given channels.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Channels if any.
     *
     * @param channels The details of the Channels to be updated; value cannot be null.
     * @return The updated Channels.
     * @throws EntityNotFoundException if no Channels is found with given input.
     */
    Channels update(@Valid Channels channels);


    /**
     * Partially updates the details of an existing Channels. It updates only the
     * fields of the existing Channels which are passed in the channelsPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Channels if any.
     *
     * @param channelsId The id of the Channels to be deleted; value cannot be null.
     * @param channelsPatch The partial data of Channels which is supposed to be updated; value cannot be null.
     * @return The updated Channels.
     * @throws EntityNotFoundException if no Channels is found with given input.
     */
    Channels partialUpdate(Integer channelsId, Map<String, Object> channelsPatch);

    /**
     * Deletes an existing Channels with the given id.
     *
     * @param channelsId The id of the Channels to be deleted; value cannot be null.
     * @return The deleted Channels.
     * @throws EntityNotFoundException if no Channels found with the given id.
     */
    Channels delete(Integer channelsId);

    /**
     * Deletes an existing Channels with the given object.
     *
     * @param channels The instance of the Channels to be deleted; value cannot be null.
     */
    void delete(Channels channels);

    /**
     * Find all Channels matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Channels.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Channels> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Channels matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Channels.
     *
     * @see Pageable
     * @see Page
     */
    Page<Channels> findAll(String query, Pageable pageable);

    /**
     * Exports all Channels matching the given input query to the given exportType format.
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
     * Exports all Channels matching the given input query to the given exportType format.
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
     * Retrieve the count of the Channels in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Channels.
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
     * Returns the associated repses for given Channels id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Reps instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Reps> findAssociatedRepses(Integer id, Pageable pageable);

}
