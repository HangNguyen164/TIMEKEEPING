$("#form-user").change(function (event) {

    event.preventDefault();

    fire_ajax_submit();

    event.preventDefault();
});

function fire_ajax_submit() {

    let search = {
        month:$("#month").val().trim(),
        year:$("#year").val().trim()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "user/list",
        data: JSON.stringify(search).trim(),
        dataType: 'json',
        cache: false,

        success: function (result) {
            const {accountDetailVoListByUser, listDayWorkNotFull, totalNotWorkInOffice, totalWorkInMonth} = result;
            let html = '';
            for (let i = 0; i < accountDetailVoListByUser.length; i++) {
                let index = i + 1;
                html += `<tr><td>${index}</td>
                        <td>${accountDetailVoListByUser[i].username}</td>
                        <td>${accountDetailVoListByUser[i].name}</td>
                        <td>${accountDetailVoListByUser[i].department}</td>
                        <td>${accountDetailVoListByUser[i].position}</td>
                        <td>${accountDetailVoListByUser[i].workDate}</td>
                        <td>${accountDetailVoListByUser[i].day}</td>
                        <td>${accountDetailVoListByUser[i].startTime}</td>
                        <td>${accountDetailVoListByUser[i].endTime}</td>
                        <td>${accountDetailVoListByUser[i].hour}</td>
                        <td>${accountDetailVoListByUser[i].note}</td>
                        <td>${accountDetailVoListByUser[i].sendEmail}</td></tr>`
            }
            $('#tbody').html(html);
            $('#total-not-work-in-office').text(listDayWorkNotFull);
            $('#total-work-in-month').text(totalNotWorkInOffice);
            $('#list-day-work-not-full').text(totalWorkInMonth);
        },
        error: function (e) {
            alert("fail"),
                console.log(e.responseText)
        }
    });
}