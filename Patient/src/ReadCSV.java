import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ReadCSV {
    Patient patient = new Patient();
    List<Patient> patientList = new ArrayList<>();
    private int index = 0;

    public List<Patient> readCSV() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/Lian/Desktop/cw2Data/patients100.csv"));
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            for (i = 0;i<19;i++){
                String fileInput = sc.next();
                fileInput = readNull(fileInput);
                chooseMethods(fileInput);
                ++index;
            }index = 0;
        }patientList.add(patient);
        return patientList;
    }

    private void chooseMethods(String fileinput)
    {
        switch(index){
            case 0:
                patient.setID(fileinput);
                break;
            case 1:
                patient.setBirthDate(fileinput);
                break;
            case 2:
                patient.setDeathDate(fileinput);
                break;
            case 3:
                patient.setSSN(fileinput);
                break;
            case 4:
                patient.setDrivers(fileinput);
                break;
            case 5:
                patient.setPassport(fileinput);
                break;
            case 6:
                patient.setPrefix(fileinput);
                break;
            case 7:
                patient.setFirst(fileinput);
                break;
            case 8:
                patient.setLast(fileinput);
                break;
            case 9:
                patient.setSuffix(fileinput);
                break;
            case 10:
                patient.setMaiden(fileinput);
                break;
            case 11:
                patient.setMarital(fileinput);
                break;
            case 12:
                patient.setRace(fileinput);
                break;
            case 13:
                patient.setEthnicity(fileinput);
                break;
            case 14:
                patient.setGender(fileinput);
                break;
            case 15:
                patient.setBirthPlace(fileinput);
                break;
            case 16:
                patient.setAddress(fileinput);
                break;
            case 17:
                patient.setCity(fileinput);
                break;
            case 18:
                patient.setState(fileinput);
                break;
            case 19:
                patient.setZip(fileinput);
                break;
        }

    }


    private String readNull(String fileInput) {
        if (fileInput == "") {
            fileInput = "null";
            return fileInput;
        } else {
            return fileInput;
        }
    }
}
