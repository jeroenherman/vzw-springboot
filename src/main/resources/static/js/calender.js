$(document).ready(function() {

    callCalendar();

    $( "#myCalendars" ).change(function() {
        $(this).parents("form").submit();
        callCalendar();
    });


});

function callCalendar() {
    $('#calendar').fullCalendar({
        header : {
            left : 'prev,next today',
            center : 'title',
            right : 'month,agendaWeek,agendaDay,listWeek'
        },
        defaultDate : '2018-11-16',
        navLinks : true,
        eventLimit : true,
        events : '/api/events/'
    });
}