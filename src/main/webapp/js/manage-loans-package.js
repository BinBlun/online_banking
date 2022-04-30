// function loadTable() {
//     $('#manage-loans-package-table').DataTable({
//         "processing": true,
//         "serverSide": true,
//         "ajax": {
//             "url": "/admin/rest/get-loans-package-list",
//             "type": "POST",
//             "dataType": "json",
//             "contentType": "application/json",
//             "data": function (d) {
//                 console.log(d)
//                 return JSON.stringify(d);
//             },
//         },
//         "columns": [
//             {"data": "loan_package_id", "width": "20%"},
//             {"data": "duration","width": "20%"},
//             {"data": "interestRate", "width": "20%"},
//         ]
//     });
// }
//
// loadTable();
