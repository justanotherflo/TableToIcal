/* ICalendarExample.java */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.model.DateTime;

import java.util.ArrayList;

public class iCalendar {

    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void makeIcalFromList(ArrayList<Vorlesung> vorlesungen, String path) throws IOException, ValidationException, java.text.ParseException
    {
        //Creating a new calendar
        net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        for(Vorlesung v : vorlesungen){
            //Creating an event
            //
            DateTime start = new DateTime(v.getStartDate(), "yyyy-MM-dd HH:mm", false);
            DateTime end = new DateTime(v.getEndDate(), "yyyy-MM-dd HH:mm", false);
            VEvent meeting = new VEvent(start, end, v.getTitle());

            // generate unique identifier..
            UidGenerator ug = new UidGenerator("uidGen");
            //meeting.getProperties().add(ug.generateUid());
            meeting.getProperties().add(new Location(v.getRoom()));


            // Add the event and print
            calendar.getComponents().add(meeting);
        }

        //Saving an iCalendar file
        FileOutputStream fout = new FileOutputStream(path);
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.setValidating(false);
        outputter.output(calendar, fout);
    }

}