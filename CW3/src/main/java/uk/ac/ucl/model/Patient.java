package uk.ac.ucl.model;
import java.util.HashMap;


public class Patient {
    HashMap<String, String> contents = new HashMap<>();

    public void setID(String id)
    {
        contents.put("ID",id);
    }

    public String getID()
    {
        return contents.get("ID");
    }

    public void setBirthDate(String date)
    {
        contents.put("BirthDate",date);
    }

    public String getBirthDate()
    {
        return contents.get("BirthDate");
    }

    public void setDeathDate(String date)
    {
        contents.put("DeathDate",date);
    }

    public String getDeathDate()
    {
        return contents.get("DeathDate");
    }

    public void setSSN(String ssn)
    {
        contents.put("SSN",ssn);
    }

    public String getSsn()
    {
        return contents.get("SSN");
    }

    public void setDrivers(String drivers)
    {
        contents.put("Drivers",drivers);
    }

    public String getDrivers()
    {
        return contents.get("Drivers");
    }

    public void setPassport(String passport)
    {
        contents.put("Passport",passport);
    }

    public String getPassport()
    {
        return contents.get("Passport");
    }

    public void setPrefix(String prefix)
    {
        contents.put("Prefix",prefix);
    }

    public String getPrefix()
    {
        return contents.get("Prefix");
    }

    public void setFirst(String first)
    {
        contents.put("First",first);
    }

    public String getFirst()
    {
        return contents.get("First");
    }

    public void setLast(String last)
    {
        contents.put("Last",last);
    }

    public String getLast()
    {
        return contents.get("Last");
    }

    public void setSuffix(String suffix)
    {
        contents.put("Suffix",suffix);
    }

    public String getSuffix()
    {
        return contents.get("Suffix");
    }

    public void setMaiden(String maiden)
    {
        contents.put("Maiden",maiden);
    }

    public String getMaiden()
    {
        return contents.get("Maiden");
    }

    public void setMarital(String marital)
    {
        contents.put("Marital",marital);
    }

    public String getMarital()
    {
        return contents.get("Marital");
    }

    public void setRace(String race)
    {
        contents.put("Race",race);
    }

    public String getRace()
    {
        return contents.get("Race");
    }

    public void setEthnicity(String ethnicity)
    {
        contents.put("Ethnicity",ethnicity);
    }

    public String getEthnicity()
    {
        return contents.get("Ethnicity");
    }

    public void setGender(String gender)
    {
        contents.put("Gender",gender);
    }

    public String getGender()
    {
        return contents.get("Gender");
    }

    public void setBirthPlace(String birthPlace)
    {
        contents.put("BirthPlace",birthPlace);
    }

    public String getBirthPlace()
    {
        return contents.get("Birthplace");
    }

    public void setAddress(String Address)
    {
        contents.put("Address",Address);
    }

    public String getAddress()
    {
        return contents.get("Address");
    }

    public void setCity(String city)
    {
        contents.put("City",city);
    }

    public String getCity()
    {
        return contents.get("City");
    }

    public void setState(String state)
    {
        contents.put("State",state);
    }

    public String getState()
    {
        return contents.get("State");
    }

    public void setZip(String zip)
    {
        contents.put("Zip",zip);
    }

    public String getZip()
    {
        return contents.get("Zip");
    }


}