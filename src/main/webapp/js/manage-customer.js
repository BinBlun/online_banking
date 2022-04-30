function loadTable() {
    $('#manage-customer-table').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/admin/rest/get-customer-list",
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function (d) {
                return JSON.stringify(d);
            },
        },
        "columns": [
            {"data": "fullName", "width": "20%"},
            {"data": "email","width": "20%"},
            {"data": "phoneNumber", "width": "20%"},
            {"data": "ssn", "width": "20%"},
            {"mRender": function ( data, type, row ) {
                    return '<div>' +
                        '<a onclick="edit(' + row.id + ')"> Edit </a>' +
                        '<a onclick="deleteUser(' + row.id + ')"> Delete </a>' +
                        '</div>';}
            }
        ]
    });
}

function success(res) {
    $('#manage-customer-table').DataTable().ajax.reload();
}
function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    displayMessageError(error);
}
function edit(id) {
    gotoUrl('/admin/edit-user?id=' + id);
}
function deleteUser(id) {
    if (confirm('Are you sure?')) {
        doRequest('POST', '/admin/rest/delete-user?id=' + id, null, success, error);
    }
}

loadTable();
