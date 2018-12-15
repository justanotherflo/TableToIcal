
/**
 * Write a description of class Vorlesung here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vorlesung
{
    private String titel;
    private String raum;
    private java.util.Date datum;
    private String anfang = "00:00";
    private String ende = "00:00";
    
    public Vorlesung()
    {
        // initialise instance variables
        //x = 0;
    }
    
    public Vorlesung(String pTitel, String pDatum)
    {
        titel = pTitel;
        //datum = pDatum;
    }
    
    public Vorlesung(String pTitel, java.util.Date pDatum)
    {
        titel = pTitel;
        datum = pDatum;
    }

    public void setEndTime(String time)
    {
        ende = time;
    }
    
    public void setStartTime(String time)
    {
        anfang = time;
    }
    public String getTitle()
    {
        return titel;
    }
    
    public String getStartDate()
    {
        String start = getYear() + "-" + getMonth() + "-" + getDayOfMonth() + " " + anfang;
        return start;
    }
    
    public String getEndDate()
    {
        String end = getYear() + "-" + getMonth() + "-" + getDayOfMonth() + " " + ende;
        return end;
    }
    
    private String getDayOfMonth()
    {
        return datum.getDate() + "";
    }
    
    private int getYear()
    {
        return 2019;
    }
    private int getMonth()
    {
        return datum.getMonth() + 1;
    }
    
    public String toString()
    {
        return getTitle() + ": " + getStartDate() + " - " + getEndDate();
    }
}
