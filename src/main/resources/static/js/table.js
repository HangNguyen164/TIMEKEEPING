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
    "scrollY": "310",
    "scrollX": true
});

$(document).ready(function () {
    const url = new URL(document.location);
    const params = url.searchParams;
    const month = params.get("month");
    $("#month").val(month);
});