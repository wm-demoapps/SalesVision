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

import com.salesvision.salesvision.Badges;
import com.salesvision.salesvision.Quotes;
import com.salesvision.salesvision.Reps;
import com.salesvision.salesvision.Rewards;
import com.salesvision.salesvision.Tasks;
import com.salesvision.salesvision.service.RepsService;


/**
 * Controller object for domain model class Reps.
 * @see Reps
 */
@RestController("SalesVision.RepsController")
@Api(value = "RepsController", description = "Exposes APIs to work with Reps resource.")
@RequestMapping("/SalesVision/Reps")
public class RepsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepsController.class);

    @Autowired
	@Qualifier("SalesVision.RepsService")
	private RepsService repsService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Reps instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Reps createReps(@RequestBody Reps reps) {
		LOGGER.debug("Create Reps with information: {}" , reps);

		reps = repsService.create(reps);
		LOGGER.debug("Created Reps with information: {}" , reps);

	    return reps;
	}

    @ApiOperation(value = "Returns the Reps instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Reps getReps(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Reps with id: {}" , id);

        Reps foundReps = repsService.getById(id);
        LOGGER.debug("Reps details with id: {}" , foundReps);

        return foundReps;
    }

    @ApiOperation(value = "Updates the Reps instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Reps editReps(@PathVariable("id") Integer id, @RequestBody Reps reps) {
        LOGGER.debug("Editing Reps with id: {}" , reps.getId());

        reps.setId(id);
        reps = repsService.update(reps);
        LOGGER.debug("Reps details with id: {}" , reps);

        return reps;
    }
    
    @ApiOperation(value = "Partially updates the Reps instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Reps patchReps(@PathVariable("id") Integer id, @RequestBody @MapTo(Reps.class) Map<String, Object> repsPatch) {
        LOGGER.debug("Partially updating Reps with id: {}" , id);

        Reps reps = repsService.partialUpdate(id, repsPatch);
        LOGGER.debug("Reps details after partial update: {}" , reps);

        return reps;
    }

    @ApiOperation(value = "Deletes the Reps instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteReps(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Reps with id: {}" , id);

        Reps deletedReps = repsService.delete(id);

        return deletedReps != null;
    }

    /**
     * @deprecated Use {@link #findReps(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Reps instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Reps> searchRepsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Reps list by query filter:{}", (Object) queryFilters);
        return repsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Reps instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Reps> findReps(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Reps list by filter:", query);
        return repsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Reps instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Reps> filterReps(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Reps list by filter", query);
        return repsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportReps(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return repsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportRepsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Reps.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> repsService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Reps instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countReps( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Reps");
		return repsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getRepsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return repsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/badgeses")
    @ApiOperation(value = "Gets the badgeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Badges> findAssociatedBadgeses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated badgeses");
        return repsService.findAssociatedBadgeses(id, pageable);
    }

    @GetMapping(value="/{id:.+}/taskses")
    @ApiOperation(value = "Gets the taskses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Tasks> findAssociatedTaskses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated taskses");
        return repsService.findAssociatedTaskses(id, pageable);
    }

    @GetMapping(value="/{id:.+}/quoteses")
    @ApiOperation(value = "Gets the quoteses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Quotes> findAssociatedQuoteses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated quoteses");
        return repsService.findAssociatedQuoteses(id, pageable);
    }

    @GetMapping(value="/{id:.+}/repsesForReportingHead")
    @ApiOperation(value = "Gets the repsesForReportingHead instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Reps> findAssociatedRepsesForReportingHead(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated repsesForReportingHead");
        return repsService.findAssociatedRepsesForReportingHead(id, pageable);
    }

    @GetMapping(value="/{id:.+}/rewardses")
    @ApiOperation(value = "Gets the rewardses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rewards> findAssociatedRewardses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated rewardses");
        return repsService.findAssociatedRewardses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RepsService instance
	 */
	protected void setRepsService(RepsService service) {
		this.repsService = service;
	}

}
