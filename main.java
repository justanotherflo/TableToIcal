import java.util.ArrayList;

/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    // instance variables - replace the example below with your own
    public static ArrayList<Vorlesung> liste_vorlesungen;

    public static void main(String[] args){
        //Read liste
        ExcelReader reader = new ExcelReader();
        
        try
        {
            liste_vorlesungen = reader.getFromExcel("table_old.xls");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        System.out.println("Found " + liste_vorlesungen.size() + " events");
        
        iCalendar ical = new iCalendar();
        try
        {
            ical.makeIcalFromList(liste_vorlesungen);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
    }
}

