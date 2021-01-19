$(document).ready(function () {

    $("#form-user").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    const search = {}
    search["month"] = $("#month").val();
    search["year"] = $("#year").val();
    console.log(search)

    $("#btn-search1").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/home-user/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            let json = JSON.stringify(data, null, 4);
            $('#custom-table1').html(json);
            $("#btn-search1").prop("disabled", false);

        },
        error: function (e) {
            let json = e.responseText;
            console.log("ERROR : ", json);
            $('#custom-table').html(json);
            $("#btn-search1").prop("disabled", false);

        }
    });

}