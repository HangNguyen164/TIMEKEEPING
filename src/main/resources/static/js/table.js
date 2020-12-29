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
    "scrollY": "310",
    "scrollX": true
});

function doSubmit() {
    var opt = document.getElementsByName("month")[0];
    var v = opt.options[opt.selectedIndex].value;
    document.forms[0].submit();
}
