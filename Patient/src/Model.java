import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model {
    ReadCSV files = new ReadCSV();
    Scanner sc = new Scanner(System.in);
    JSONFormatter json = new JSONFormatter();
    List<Patient> str;


    public List readFile(String input) throws FileNotFoundException {
        str = files.ReadCSV(input);
        return str;
    }

    public String getAllPatient()
    {
        String output = json.JSONFormatterAll(str);
        return output;
    }

    public String getSinglePatient(int inputInt)
    {
        String output = json.JSONFormatterSingle(str,inputInt);
        return output;
    }

    public List getAllIDs() {
        List<String> patientListID = new ArrayList<>();
        for (int i = 0; i < str.size(); i++) {
            Object patientReference = str.get(i);
            String patientID = ((Patient) patientReference).getID();
            patientListID.add(patientID);
        }
        return patientListID;
    }

    public List getAllNames()
    {
        List<String> patientFullNames = new ArrayList<>();
        for (int i = 0; i < str.size(); i ++)
        {
            Object curPatient = str.get(i);
            String curPatientFirstName = ((Patient) curPatient).getFirst();
            String curPatientLastName = ((Patient) curPatient).getLast();
            String curPatitentFullName = curPatientFirstName + "  " + curPatientLastName;
            patientFullNames.add(curPatitentFullName);
        }return patientFullNames;
    }

    public int getAverageBirthYear() throws ParseException
    {
        int avgYearTmp = 0;
        for(int i = 0; i < str.size(); i++ )
        {
            Object patientReference = str.get(i);
            String patientAgeString = ((Patient) patientReference).getBirthDate();
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(patientAgeString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthDate);
            avgYearTmp += cal.get(Calendar.YEAR);
        }
        int avgYear = avgYearTmp / str.size();
        return avgYear;
    }

    public int getAverageAge() throws ParseException
    {
        int averageYear = getAverageBirthYear();
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int avgAge = curYear - averageYear;
        return avgAge;
    }

    public float getAgeDistribution() throws ParseException
    {
        int numberChildren = 0;
        int numberParent = 0;
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 0; i < str.size(); i++ ) {
            Object patientReference = str.get(i);
            String patientAgeString = ((Patient) patientReference).getBirthDate();
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(patientAgeString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthDate);
            int patientBirthYear = cal.get(Calendar.YEAR);
            int patientAge = curYear - patientBirthYear;
            if ( 0 < patientAge && patientAge < 9 )
            {
                ++numberChildren;
            }++numberParent;
        }
        float ageDistribution = ((float) numberChildren / (float) numberParent);
        return ageDistribution * 1000;
    }
}
