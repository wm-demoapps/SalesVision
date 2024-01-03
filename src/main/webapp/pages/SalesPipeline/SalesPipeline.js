/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Page.onReady = function() {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
    console.log("######### " + moment().format('YYYY-MM-DD'));
};

Page.getTodaysTaskonSuccess = function(variable, data) {
    Page.Widgets.calendar.selecteddata = data;
};

Page.CreateSalesLiveFormBeforeservicecall = function($event, $operation, $data, options) {
    $data.quotes = Page.Widgets.salesPipelineTable.selecteditem;
    $data.productId = Page.Widgets.salesPipelineTable.selecteditem.productId;
};

Page.iconForQuoteStatus = function(status) {
    return (status == 1) ? 'wi wi-record-voice-over' :
        (status == 2) ? 'wi wi-assignment-ind' :
        (status == 3) ? 'wi wi-assignment' :
        (status == 4) ? 'wi wi-assignment-turned-in' :
        (status == 5) ? 'wi wi-assignment-late' : 'wi wi-shop';
}
