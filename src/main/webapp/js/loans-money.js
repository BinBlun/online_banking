doSomething = (id) => {
    document.getElementById('loansPackageId').innerText = 'Loans Package ID: ';
    console.log(document.getElementById('id'))
    document.getElementById('id').value = id;
}

function success(res) {
    // gotoUrl('/customer/transferSuccess');
    alert("Thành Công")
}
function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    displayMessageError(error);
}
function submitForm(e) {
    e.preventDefault();
    const form = new FormData(e.target);
    postFormData("POST", "/customer/rest/doMoneyLoans", form, success, error)
}


