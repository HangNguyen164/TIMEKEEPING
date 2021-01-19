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
           populateDataTable(result);
        },
        error: function (e) {
            console.log(e.responseText)
        }
    });

    function populateDataTable(data) {

        $("#custom-table").DataTable({
            "data":data,
            columns: [
                { title: "username" },
                { title: "name" },
                { title: "department" },
                { title: "position" },
                { title: "workDate" },
                { title: "day" },
                { title: "startTime" },
                { title: "endTime" },
                { title: "hour" },
                { title: "note" },
                { title: "sendEmail" },
            ]
        })
    }
}