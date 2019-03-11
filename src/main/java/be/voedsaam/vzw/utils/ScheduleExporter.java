package be.voedsaam.vzw.utils;

import be.voedsaam.vzw.business.Drive;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ScheduleExporter {
    @Value("classpath:")
    Resource icsfile;

    List<Calendar> calendarList;
    static Calendar calendar = new Calendar();

    private static void setProperties() {
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
    }


    public static File createIcalfromDrive(Drive drive) throws IOException {
        setProperties();

        VEvent event = new VEvent();




        File ics = new File("drive.ics");
        FileOutputStream fout = new FileOutputStream(ics);
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(calendar, fout);
        return ics;
    }



}
