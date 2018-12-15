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
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.model.DateTime;

import java.util.ArrayList;

public class iCalendar {

    static String calFile = "mycalendar.ics";
    public static void main(String[] args) throws IOException, ValidationException, ParserException {

  
        //Creating a new calendar
        net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        //Creating an event
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
        cal.set(java.util.Calendar.DAY_OF_MONTH, 25);

        VEvent christmas = new VEvent(new Date(cal.getTime()), "Christmas Day");
        // initialise as an all-day event..
        christmas.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);

        UidGenerator uidGenerator = new UidGenerator("1");
        christmas.getProperties().add(uidGenerator.generateUid());

        calendar.getComponents().add(christmas);

        //Saving an iCalendar file
        FileOutputStream fout = new FileOutputStream(calFile);

        CalendarOutputter outputter = new CalendarOutputter();
        outputter.setValidating(false);
        outputter.output(calendar, fout);

        //Now Parsing an iCalendar file
        FileInputStream fin = new FileInputStream(calFile);

        CalendarBuilder builder = new CalendarBuilder();

        calendar = builder.build(fin);

        //Iterating over a Calendar
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next();
            System.out.println("Component [" + component.getName() + "]");

            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
            }
        }//for
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void makeIcalFromList(ArrayList<Vorlesung> vorlesungen) throws IOException, ValidationException, java.text.ParseException
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
            meeting.getProperties().add(ug.generateUid());

            // Add the event and print
            calendar.getComponents().add(meeting);
        }

        //Saving an iCalendar file
        FileOutputStream fout = new FileOutputStream(calFile);
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.setValidating(false);
        outputter.output(calendar, fout);
    }

}