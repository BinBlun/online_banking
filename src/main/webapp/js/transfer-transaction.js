function success(res) {
    if (res.status === 'ERROR') {
        alert(res.errorDesc);
    } else {
        gotoUrl('/customer/transactionSuccess');
    }
}
function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    alert(error);
}
function submitForm(e) {
    e.preventDefault();
    const form = new FormData(e.target);
    postFormData("POST", "/customer/doTransferMoney", form, success, error)
}