
var ymc = ymc || {};
ymc.ymcPath="";
(function () {
    ymc.ajax = function (options) {
        options = options || {};
        var options = $.extend({}, ymc.ajax.defaultOpts, options);
        var beforeSendCallback = options.beforeSend;
        options.beforeSend = function (xhr) {
            if (beforeSendCallback && typeof beforeSendCallback == 'function') {
                beforeSendCallback(xhr);
            }
        }
        var successCallback = options.success;
        options.success = function (data, status, xhr) {
            if (successCallback && typeof successCallback == 'function') {
                successCallback(data, status, xhr);
            }
        }
        
        if (!options.error) {
            options.error = function (e, type) {
                console.info(e, type);
            }
        }
        $.ajax(options);
    };


    ymc.getHtml = function (options) {
        options = options || {};
        var options = $.extend({}, ymc.getHtml.defaultOpts, options);
        var successCallback = options.success;
        options.success = function (data, status, xhr) {
            if (successCallback && typeof successCallback == 'function') {
                successCallback(data, status, xhr);
            } else {
                $('body').append(data)
            }
        }
        $.ajax(options);
    }

    ymc.getHtml.defaultOpts = {
        dataType: 'text',
        type: 'GET',
        async: false
    };

    ymc.ajax.defaultOpts = {
        dataType: 'json',
        type: 'POST',
        //contentType: 'application/json'
        	
    };

})();