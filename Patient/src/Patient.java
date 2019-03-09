
import java.util.HashMap;


public class Patient {
    HashMap<String,String> hmap;
    private String id;



    private String birthDate;
    private String deathDate;
    private String ssn;
    private String drivers;
    private String passport;
    private String prefix;
    private String first;
    private String last;
    private String suffix;
    private String maiden;
    private String marital;
    private String race;
    private String ethnicity;
    private String gender;
    private String birthPlace;
    private String address;
    private String city;
    private String state;
    private String zip;

    public void setID(String id)
    {
        hmap.put("ID:", id);
    }

    public String getID()
    {
        String id = hmap.get("ID:");
        return id;
    }

    public void setBirthDate(String date)
    {
        hmap.put("BirthDate:", date);
    }

    public String getBirthDate()
    {
        return hmap.get("BirthDate:");
    }

    public void setDeathDate(String date)
    {
        hmap.put("DeathDate:",date);
    }

    public String getDeathDate()
    {
        return hmap.get("DeathDate:");
    }

    public void setSSN(String ssn)
    {
        hmap.put("SSN:",ssn);
    }

    public String getSsn()
    {
        return hmap.get("SSN:");
    }

    public void setDrivers(String drivers)
    {
        hmap.put("Drivers:",drivers);
    }

    public String getDrivers()
    {
        return hmap.get("Drivers:");
    }

}
