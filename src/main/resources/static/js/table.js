/* Custom filtering function which will search data in column four between two values */
$.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {
        var min = $("#min").val();
        var max = $('#max').val();
        var createdAt = data[5] || 0; // Our date column in the table
        if ((createdAt >= min && createdAt <= max) || (createdAt >= min && max == '') || (createdAt <= max && min == '')) {
            return true;
        }
        return false;
    }
);

$(document).ready(function () {
    var table = $('#custom-table').DataTable();
    // Event listener to the two range filtering inputs to redraw on input
    $('#min, #max').keyup(function () {
        table.draw();
    });
});

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
    "bPaginate": false,
    "scrollY": "450px",
});

