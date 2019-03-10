import java.util.ArrayList;


public class Patient {
    ArrayList<String> contents;

    public void setID(String id)
    {
        contents.add(0,id);
    }

    public String getID()
    {
        return contents.get(0);
    }

    public void setBirthDate(String date)
    {
        contents.add(1,date);
    }

    public String getBirthDate()
    {
        return contents.get(1);
    }

    public void setDeathDate(String date)
    {
        contents.add(2,date);
    }

    public String getDeathDate()
    {
        return contents.get(2);
    }

    public void setSSN(String ssn)
    {
        contents.add(3,ssn);
    }

    public String getSsn()
    {
        return contents.get(3);
    }

    public void setDrivers(String drivers)
    {
        contents.add(4,drivers);
    }

    public String getDrivers()
    {
        return contents.get(4);
    }

    public void setPassport(String passport)
    {
        contents.add(5,passport);
    }

    public String getPassport()
    {
        return contents.get(5);
    }

    public void setPrefix(String prefix)
    {
        contents.add(6,prefix);
    }

    public String getPrefix()
    {
        return contents.get(6);
    }

    public void setFirst(String first)
    {
        contents.add(7,first);
    }

    public String getFirst()
    {
        return contents.get(7);
    }

    public void setLast(String last)
    {
        contents.add(8,last);
    }

    public String getLast()
    {
        return contents.get(8);
    }

    public void setSuffix(String suffix)
    {
        contents.add(9,suffix);
    }

    public String getSuffix()
    {
        return contents.get(9);
    }

    public void setMaiden(String maiden)
    {
        contents.add(10,maiden);
    }

    public String getMaiden()
    {
        return contents.get(10);
    }

    public void setMarital(String marital)
    {
        contents.add(11,marital);
    }

    public String getMarital()
    {
        return contents.get(11);
    }

    public void setRace(String race)
    {
        contents.add(12,race);
    }

    public String getRace()
    {
        return contents.get(12);
    }

    public void setEthnicity(String ethnicity)
    {
        contents.add(13,ethnicity);
    }

    public String getEthnicity()
    {
        return contents.get(13);
    }

    public void setGender(String gender)
    {
        contents.add(14,gender);
    }

    public String getGender()
    {
        return contents.get(14);
    }

    public void setBirthPlace(String birthPlace)
    {
        contents.add(15,birthPlace);
    }

    public String getBirthPlace()
    {
        return contents.get(15);
    }

    public void setAddress(String address)
    {
        contents.add(16,address);
    }

    public String getAddress()
    {
        return contents.get(16);
    }

    public void setCity(String city)
    {
        contents.add(17,city);
    }

    public String getCity()
    {
        return contents.get(17);
    }

    public void setState(String state)
    {
        contents.add(18,state);
    }

    public String getState()
    {
        return contents.get(18);
    }

    public void setZip(String zip)
    {
        contents.add(19,zip);
    }

    public String getZip()
    {
        return contents.get(19);
    }


}
