$(document).ready(function () {
    $(document).ready(function () {
        $('#falseinput').click(function () {
            $("#fileinput").click();
        });
    });
    $('#fileinput').change(function () {
        $('#selected_filename').text($('#fileinput')[0].files[0].name);
    });
})

$('#custom-table').dataTable({
    "scrollY": "450px",
});

