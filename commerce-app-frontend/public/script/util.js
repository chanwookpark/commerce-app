var Commerce = {};
Commerce.util = {};

/**
 * 참조: https://www.sitepoint.com/url-parameters-jquery/
 * @param name
 * @returns {*}
 */
Commerce.util.getParameterByName = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null) {
        return null;
    }
    else {
        return results[1] || 0;
    }
}