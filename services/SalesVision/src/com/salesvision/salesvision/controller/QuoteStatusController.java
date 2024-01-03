/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.salesvision.salesvision.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.commons.file.manager.ExportedFileManager;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.salesvision.salesvision.QuoteStatus;
import com.salesvision.salesvision.Quotes;
import com.salesvision.salesvision.service.QuoteStatusService;


/**
 * Controller object for domain model class QuoteStatus.
 * @see QuoteStatus
 */
@RestController("SalesVision.QuoteStatusController")
@Api(value = "QuoteStatusController", description = "Exposes APIs to work with QuoteStatus resource.")
@RequestMapping("/SalesVision/QuoteStatus")
public class QuoteStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteStatusController.class);

    @Autowired
	@Qualifier("SalesVision.QuoteStatusService")
	private QuoteStatusService quoteStatusService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new QuoteStatus instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public QuoteStatus createQuoteStatus(@RequestBody QuoteStatus quoteStatusInstance) {
		LOGGER.debug("Create QuoteStatus with information: {}" , quoteStatusInstance);

		quoteStatusInstance = quoteStatusService.create(quoteStatusInstance);
		LOGGER.debug("Created QuoteStatus with information: {}" , quoteStatusInstance);

	    return quoteStatusInstance;
	}

    @ApiOperation(value = "Returns the QuoteStatus instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public QuoteStatus getQuoteStatus(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting QuoteStatus with id: {}" , id);

        QuoteStatus foundQuoteStatus = quoteStatusService.getById(id);
        LOGGER.debug("QuoteStatus details with id: {}" , foundQuoteStatus);

        return foundQuoteStatus;
    }

    @ApiOperation(value = "Updates the QuoteStatus instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public QuoteStatus editQuoteStatus(@PathVariable("id") Integer id, @RequestBody QuoteStatus quoteStatusInstance) {
        LOGGER.debug("Editing QuoteStatus with id: {}" , quoteStatusInstance.getId());

        quoteStatusInstance.setId(id);
        quoteStatusInstance = quoteStatusService.update(quoteStatusInstance);
        LOGGER.debug("QuoteStatus details with id: {}" , quoteStatusInstance);

        return quoteStatusInstance;
    }
    
    @ApiOperation(value = "Partially updates the QuoteStatus instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public QuoteStatus patchQuoteStatus(@PathVariable("id") Integer id, @RequestBody @MapTo(QuoteStatus.class) Map<String, Object> quoteStatusInstancePatch) {
        LOGGER.debug("Partially updating QuoteStatus with id: {}" , id);

        QuoteStatus quoteStatusInstance = quoteStatusService.partialUpdate(id, quoteStatusInstancePatch);
        LOGGER.debug("QuoteStatus details after partial update: {}" , quoteStatusInstance);

        return quoteStatusInstance;
    }

    @ApiOperation(value = "Deletes the QuoteStatus instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteQuoteStatus(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting QuoteStatus with id: {}" , id);

        QuoteStatus deletedQuoteStatus = quoteStatusService.delete(id);

        return deletedQuoteStatus != null;
    }

    /**
     * @deprecated Use {@link #findQuoteStatuses(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of QuoteStatus instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<QuoteStatus> searchQuoteStatusesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering QuoteStatuses list by query filter:{}", (Object) queryFilters);
        return quoteStatusService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of QuoteStatus instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<QuoteStatus> findQuoteStatuses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering QuoteStatuses list by filter:", query);
        return quoteStatusService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of QuoteStatus instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<QuoteStatus> filterQuoteStatuses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering QuoteStatuses list by filter", query);
        return quoteStatusService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportQuoteStatuses(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return quoteStatusService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportQuoteStatusesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = QuoteStatus.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> quoteStatusService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of QuoteStatus instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countQuoteStatuses( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting QuoteStatuses");
		return quoteStatusService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getQuoteStatusAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return quoteStatusService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/quoteses")
    @ApiOperation(value = "Gets the quoteses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Quotes> findAssociatedQuoteses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated quoteses");
        return quoteStatusService.findAssociatedQuoteses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service QuoteStatusService instance
	 */
	protected void setQuoteStatusService(QuoteStatusService service) {
		this.quoteStatusService = service;
	}

}
