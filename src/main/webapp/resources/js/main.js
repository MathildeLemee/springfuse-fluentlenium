jQuery(function() {
	
    // HACK to make p:layout work with chromeshades (we just add the !important)
    $(PrimeFaces.escapeClientId('form:top')).css("display","block !important");
    $(PrimeFaces.escapeClientId('form:center')).css("display","block !important");
    $(PrimeFaces.escapeClientId('form:bottom')).css("display","block !important");

	// Add some ARIA role here
    $(PrimeFaces.escapeClientId('form:center')).attr("role","main");
    $(PrimeFaces.escapeClientId('form:bottom')).attr("role","contentinfo");
    $(".aria-role-presentation").attr("role", "presentation");
    $(".aria-search-button").attr("aria-controls", "searchResultsRegion");
    $(".aria-save-button").attr("aria-controls", "messagesRegion");
    
    // Propagate quit action, from previous flow to current flow
    if (document.URL.indexOf('_cascadeQuit', 0) > 0) {
		APP.menu.quit();
	}

    /* temporary fix for keyboard menu navigation */
    $('.ui-menuitem-link').focus(function() {
    	jQuery(this).parent().toggleClass('ui-state-focus');
    	}).blur(function() {
    	jQuery(this).parent().removeClass('ui-state-focus');
    });

    /* default submit button when user press enter */
    $("form input, form select").live("keypress", function (e) {
    	if ($(this).parents("form").find("button[type=submit].default").length <= 0) {
    		return true;
    	}
    	
    	if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
    		$(this).parents("form").find("button[type=submit].default").click();
    		return false;
    	} else {
    		return true;
    	}
    });
    
});

APP = {};

//-------------------------------------
// Gain focus on dialog
//-------------------------------------
APP.focusAskForDeleteDialog = function() {
	jQuery(PrimeFaces.escapeClientId('form:askForDeleteDialogNo')).focus();
};

APP.focusAskForSaveDialog = function() {
	  jQuery(PrimeFaces.escapeClientId('form:askForSaveDialogYes')).focus();
};

APP.focusTo = function(jsfId) {
	jQuery(PrimeFaces.escapeClientId(jsfId)).focus();
};	

//---------------------------------------
// Aria live region related
//---------------------------------------
/* todo: localization */
APP.updateSearchResultsRegion = function(xhr, status, args) {
  	jQuery("#searchResultsRegion").text('' + args.totalRecords + ' results');
};	

//-------------------------------------
//Menu shortcuts
//-------------------------------------

APP.menu = {};
APP.menu.call = function(idToClick) {
  jQuery(PrimeFaces.escapeClientId(idToClick)).click();	
  return false;
};
APP.menu.openLocation = function() {
  window.location.href=window.location.href.substring(0, window.location.href.indexOf('?')); /* go to 'open' page...*/
  return;
};
APP.menu.newLocation = function() {
  window.location.href=window.location.href.substring(0, window.location.href.indexOf('?'))+'?create=true'; /* go to 'new' page...*/
  return;
};
APP.menu.debugThrowException = function() {
  return APP.menu.call('form:debugThrowException');
};
APP.menu.cancel = function() {
  return APP.menu.call('form:cancel');
};
APP.menu.forceClose = function() {
  return APP.menu.call('form:forceClose');
};
APP.menu.quit = function() {
  return APP.menu.call('form:quit');
};
