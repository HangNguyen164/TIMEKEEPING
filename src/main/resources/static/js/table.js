$(document).ready(function () {
    $(document).ready(function () {
        $('#false-input').click(function () {
            $("#file-input").click();
        });
    });
    $('#file-input').change(function () {
        $('#selected_filename').text($('#file-input')[0].files[0].name);
    });
    getModalCenter();
    getMonthForCombobox();
    getYearForCombobox();
    a();
})

$('#custom-table').dataTable({
    "scrollY": "350",
    "scrollX": true
});

/* Centering the modal vertically */
function getModalCenter() {
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
}

/**
 * Get Month for combobox select
 */
function getMonthForCombobox() {
    const url = new URL(document.location);
    const params = url.searchParams;
    const month = params.get("month");
    const currentMonth = new Date().getMonth() + 1;
    if (month !== null) {
        $("#month").val(month);
    } else {
        $("#month").val(currentMonth);
    }
}

/**
 * Get Year for combobox select
 */
function getYearForCombobox() {
    const url = new URL(document.location);
    const params = url.searchParams;
    const year = params.get("year");
    const currentYear = new Date().getFullYear();
    if (year !== null) {
        $("#year").val(year);
    } else {
        $("#year").val(currentYear);
    }
}

function a() {
    const table = $('#custom-table').DataTable();
    const dataOfTable = table
        .rows()
        .data();
    console.log(JSON.stringify(dataOfTable))

    $('#form-user').change(function () {
        console.log("1")
        $.ajax({
            type: "POST",
            url: "home-user",
            dataType: 'json',
            data: JSON.stringify(dataOfTable),
            success: function (data) {
                alert(data);
                console.log(dataOfTable)
            }
        });
    });
}