function doRequest(method, url, data, successFunc, errorFunc) {
    $.ajax({
        type: method,
        contentType: "application/json",
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        success: successFunc,
        error: errorFunc
    });
}

function postFormData(method, url, formData, successFunc, errorFunc) {
    const data = Object.fromEntries(formData.entries());
    doRequest(method, url, data, successFunc, errorFunc);
}

function gotoUrl(url) {
    document.location.href = url;
}