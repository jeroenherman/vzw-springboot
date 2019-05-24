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
            if (calEvent.id!=null) {
                location.replace('/drive/show/' + calEvent.id);
            }
            else{
                location.replace('/schedule/showlatest' )
            }
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
        selectable : true,
        select: function(start, end) {
            var title = prompt('Rit Naam:');
            var eventData;
            if (title) {
                eventData = {
                    title: title,
                    start: start,
                    end: end
                };

                $.ajax(
                    {

                        url: '/schedule/event',
                        type: 'post',
                        traditional: true,
                        data: JSON.stringify(eventData),
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function (response) {
                          eventData = response
                        },
                        error: function (xhr) {
                            debugger;
                            alert(xhr);
                        }

                    });
                $('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true

            }
            $('#calendar').fullCalendar('unselect');

        },
        events : '/schedule/events/'
    });

}

