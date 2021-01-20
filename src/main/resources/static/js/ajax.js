$("#form-user").change(function (event) {

    event.preventDefault();

    fire_ajax_submit();

    event.preventDefault();
});

function fire_ajax_submit() {

    $.ajax({
        url: "/home-user?month="+ $("#month").val().trim()+"&year="+ $("#year").val().trim(),
        cache: false,

        success: function (result) {
            $("#exam").html(result);
        },
        error: function (e) {
            alert("fail"),
                console.log(e.responseText)
        }
    });
}

$("#form-admin").change(function (event) {

    event.preventDefault();

    fire_ajax_submit();

    event.preventDefault();
});

function fire_ajax_submit() {

    $.ajax({
        url: "/home-admin/search?month="+ $("#month").val().trim()+"&year="+ $("#year").val().trim(),
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