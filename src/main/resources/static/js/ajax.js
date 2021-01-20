$("#form-user").change(function (event) {

    event.preventDefault();

    fire_ajax_submit_user();

    event.preventDefault();
});

function fire_ajax_submit_user() {

    $.ajax({
        url: "/home-user/search?month=" + $("#month").val().trim() + "&year=" + $("#year").val().trim(),
        cache: false,

        success: function (result) {
            $("#table-user").html(result);
        },
        error: function (e) {
            alert("fail"),
                console.log(e.responseText)
        }
    });
}

$("#form-admin").change(function (event) {

    event.preventDefault();

    fire_ajax_submit_admin();

    event.preventDefault();
});

function fire_ajax_submit_admin() {

    $.ajax({
        url: "/home-admin/search?month=" + $("#month").val().trim() + "&year=" + $("#year").val().trim(),
        cache: false,

        success: function (result) {
            $("#table-admin").html(result);
        },
        error: function (e) {
            alert("fail"),
                console.log(e.responseText)
        }
    });
}