import java.util.HashMap;


public class Patient {
    HashMap<String,String> hmap;

    public void setID(String id)
    {
        hmap.put("ID:", id);
    }

    public String getID()
    {
        return hmap.get("ID:");
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

    public void setPassport(String passport)
    {
        hmap.put("Passport:",passport);
    }

    public String getPassport()
    {
        return hmap.get("Passport:");
    }

    public void setPrefix(String prefix)
    {
        hmap.put("Prefix:",prefix);
    }

    public String getPrefix()
    {
        return hmap.get("Prefix:");
    }

    public void setFirst(String first)
    {
        hmap.put("First:",first);
    }

    public String getFirst()
    {
        return hmap.get("First:");
    }

    public void setLast(String last)
    {
        hmap.put("Last:",last);
    }

    public String getLast()
    {
        return hmap.get("Last:");
    }

    public void setSuffix(String suffix)
    {
        hmap.put("Suffix:",suffix);
    }

    public String getSuffix()
    {
        return hmap.get("Suffix:");
    }

    public void setMaiden(String maiden)
    {
        hmap.put("Maiden",maiden);
    }

    public String getMaiden()
    {
        return hmap.get("Maiden:");
    }

    public void setMarital(String marital)
    {
        hmap.put("Marital:",marital);
    }

    public String getMarital()
    {
        return hmap.get("Marital:");
    }

    public void setRace(String race)
    {
        hmap.put("Race:",race);
    }

    public String getRace()
    {
        return hmap.get("Race:");
    }

    public void setEthnicity(String ethnicity)
    {
        hmap.put("Ethnicity:",ethnicity);
    }

    public String getEthnicity()
    {
        return hmap.get("Ethnicity:");
    }

    public void setGender(String gender)
    {
        hmap.put("Gender:",gender);
    }

    public String getGender()
    {
        return hmap.get("Gender:");
    }

    public void setBirthPlace(String birthPlace)
    {
        hmap.put("BirthPlace:",birthPlace);
    }

    public String getBirthPlace()
    {
        return hmap.get("BirthPlace:");
    }

    public void setAddress(String address)
    {
        hmap.put("Address:",address);
    }

    public String getAddress()
    {
        return hmap.get("Address:");
    }

    public void setCity(String city)
    {
        hmap.put("City:",city);
    }

    public String getCity()
    {
        return hmap.get("City:");
    }

    public void setState(String state)
    {
        hmap.put("State:",state);
    }

    public String getState()
    {
        return hmap.get("State:");
    }

    public void setZip(String zip)
    {
        hmap.put("Zip:",zip);
    }

    public String getZip()
    {
        return hmap.get("Zip:");
    }
}
