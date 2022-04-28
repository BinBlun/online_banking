function success(res) {
    alert(res.data.status);
}
function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    alert(error);
}
function submitForm(e) {
    e.preventDefault();
    const form = new FormData(e.target);
    postFormData("POST", "/customer/rest/doTransferMoney", form, success, error)
}