import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model {
    ReadCSV files = new ReadCSV();
    JSONFormatter json = new JSONFormatter();
    List<Patient> str;
    private int index = 0;


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

    public String convertJson(String input) throws FileNotFoundException{
        String tmpStr = new String();
        Scanner sc =  new Scanner(new File(input));
        sc.useDelimiter(",");
        while (sc.hasNext()){
            tmpStr += sc.next();
        }
        String removeTitlesAndBrackets = tmpStr.replaceAll("Zip|BirthPlace|Drivers|Address|Marital|Prefix|Gender|City|DeathDate|SSN|" +
                                                                     "Passport|Last|Ethnicity|Suffix|Maiden|State|Race|ID|First|BirthDate|\"|}|\\{|]|\\[|patients", "");
        String removeColon = removeTitlesAndBrackets.replaceAll(":",",");
        String removeFirstComma = removeColon.replaceFirst(",","");
        String removeWhiteSpaces = removeFirstComma.replaceAll("\\n|\\t","");
        String finalStr = removeWhiteSpaces.replaceFirst(",","");
        return finalStr;
    }

    public List readJson(String input) throws FileNotFoundException{
        List<Patient> tmpList = new ArrayList<>();
        String tmpStore = convertJson(input);
        List<String> values = new ArrayList<>(Arrays.asList(tmpStore.split(",")));
        Iterator<String> valueIterator = values.iterator();
        while (valueIterator.hasNext()){
            Patient patient = new Patient();
            for(int i =0;i< 20;i++){
                String curStr = valueIterator.next();
                chooseMethods(curStr, patient);
                ++index;
            }
            index = 0;
            tmpList.add(patient);
        }

        str = tmpList;
        return str;
    }

    private void chooseMethods(String fileInput, Patient patient)
    {
        switch (index){
            case 0:
                patient.setZip(fileInput);
                break;
            case 1:
                patient.setBirthPlace(fileInput);
                break;
            case 2:
                patient.setDrivers(fileInput);
                break;
            case 3:
                patient.setAddress(fileInput);
                break;
            case 4:
                patient.setMarital(fileInput);
                break;
            case 5:
                patient.setPrefix(fileInput);
                break;
            case 6:
                patient.setGender(fileInput);
                break;
            case 7:
                patient.setCity(fileInput);
                break;
            case 8:
                patient.setDeathDate(fileInput);
                break;
            case 9:
                patient.setSSN(fileInput);
                break;
            case 10:
                patient.setPassport(fileInput);
                break;
            case 11:
                patient.setLast(fileInput);
                break;
            case 12:
                patient.setEthnicity(fileInput);
                break;
            case 13:
                patient.setSuffix(fileInput);
                break;
            case 14:
                patient.setMaiden(fileInput);
                break;
            case 15:
                patient.setState(fileInput);
                break;
            case 16:
                patient.setRace(fileInput);
                break;
            case 17:
                patient.setID(fileInput);
                break;
            case 18:
                patient.setFirst(fileInput);
                break;
            case 19:
                patient.setBirthDate(fileInput);
                break;
        }
    }


}
