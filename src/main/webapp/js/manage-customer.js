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
                console.log(d)
                return JSON.stringify(d);
            },
        },
        "columns": [
            {"data": "fullName", "width": "20%"},
            {"data": "email","width": "20%"},
            {"data": "phoneNumber", "width": "20%"},
            {"data": "ssn", "width": "20%"},
        ]
    });
}

loadTable();
