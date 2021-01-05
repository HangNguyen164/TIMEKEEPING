$(document).ready(function () {
    $(document).ready(function () {
        $('#false-input').click(function () {
            $("#file-input").click();
        });
    });
    $('#file-input').change(function () {
        $('#selected_filename').text($('#file-input')[0].files[0].name);
    });
})

$('#custom-table').dataTable({
    "scrollY": "350",
    "scrollX": true
});

$(document).ready(function () {
    const url = new URL(document.location);
    const params = url.searchParams;
    const month = params.get("month");
    const currentMonth = new Date().getMonth() + 1;
    if (month !== null) {
        $("#month").val(month);
    } else {
        $("#month").val(currentMonth);
    }
});

$(document).ready(function () {

    /* Centering the modal vertically */
    function alignModal() {
        const modalDialog = $(this).find(".modal-dialog");
        modalDialog.css("margin-top", Math.max(0,
            ($(window).height() - modalDialog.height()) / 2));
    }

    $(".modal").on("shown.bs.modal", alignModal);

    /* Resizing the modal according the screen size */
    $(window).on("resize", function () {
        $(".modal:visible").each(alignModal);
    });
});
