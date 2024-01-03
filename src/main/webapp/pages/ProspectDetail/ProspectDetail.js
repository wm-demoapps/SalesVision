/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Partial.onReady = function() {
    /*
     * variables can be accessed through 'Partial.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Partial.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Partial.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Partial.Widgets.username.datavalue'
     */
};

Partial.iconForQuoteStatus = function(status) {
    console.log(status);
    return (status == 1) ? 'wi wi-record-voice-over fa-2x' :
        (status == 2) ? 'wi wi-assignment-ind fa-2x' :
        (status == 3) ? 'wi wi-assignment fa-2x' :
        (status == 4) ? 'wi wi-assignment-turned-in fa-2x' :
        (status == 5) ? 'wi wi-assignment-late fa-2x' : 'wi wi-shop fa-2x';
}
