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
function getSelectedIndex() {
    var e = document.getElementById("select-month");
    var result = e.options[e.selectedIndex].text;

    document.getElementById("result").innerHTML = result;
}

