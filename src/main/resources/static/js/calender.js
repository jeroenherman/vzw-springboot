$(document).ready(function() {

    callCalendar();

    $( "#myCalendars" ).change(function() {
        $(this).parents("form").submit();
        callCalendar();
    });


});

function callCalendar() {
    $('#calendar').fullCalendar({
        eventClick: function(calEvent, jsEvent, view) {

            location.replace('/drive/show/' + calEvent.id);
        },
        header : {
            left : 'prev,next today',
            center : 'title',
            right : 'month,agendaWeek,agendaDay,listWeek'
        },
        locale: 'nl',
        height: 500,
        navLinks : true,
        eventLimit : true,
        events : '/schedule/events/'
    });

}

