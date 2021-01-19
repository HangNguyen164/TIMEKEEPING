$("#form-user").change(function (event) {

    event.preventDefault();

    fire_ajax_submit();

});

function fire_ajax_submit() {

    let search = {
        month: $("#month").val(),
        year: $("#year").val(),
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "user/list",
        data: JSON.stringify(search),
        dataType: 'json',

        success: function (result) {
            let columnDefs = [];
            for(let k in result[0]){
                if(columnDefs.indexOf(k) === -1){
                    columnDefs.push({title: result[0][k]});
                }
            }
            let data = [];
            for(let j of result){
                let arr = [];
                for(let k in j){
                    if(columnDefs.indexOf(k) === -1){
                        arr.push(j[k]);
                    }
                }
                data.push(arr);
            }

            $('#custom-table').dataTable({
                data: data,
                columns: columnDefs
            });
        },
        error: function (e) {
            console.log(e.responseText)
        }
    });

    function populateDataTable(data) {

        $("#custom-table").DataTable({
            "data": data,
            columns: [
                {data: "username"},
                {data: "name"},
                {data: "department"},
                {data: "position"},
                {data: "workDate"},
                {data: "day"},
                {data: "startTime"},
                {data: "endTime"},
                {data: "hour"},
                {data: "note"},
                {data: "sendEmail"},
            ]
        })
    }
}