import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ReadCSV {
    List<Patient> patientList = new ArrayList<>();
    private int index = 0;

    public List<Patient> readCSV() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/Lian/Desktop/cw2Data/patients100.csv"));
        sc.useDelimiter(",|\n");
        while (sc.hasNext()) {
            Patient patient = new Patient();
            for (int i = 0;i<20;i++){
                String fileInput = sc.next();
                fileInput = readNull(fileInput);
                chooseMethods(fileInput, patient);
                ++index;
            }index = 0;
            patientList.add(patient);
        }
        patientList.remove(0);
        return patientList;
    }

    private void chooseMethods(String fileInput, Patient patient)
    {
            switch (index){
                case 0:
                    patient.setID(fileInput);
                    break;
                case 1:
                    patient.setBirthDate(fileInput);
                    break;
                case 2:
                    patient.setDeathDate(fileInput);
                    break;
                case 3:
                    patient.setSSN(fileInput);
                    break;
                case 4:
                    patient.setDrivers(fileInput);
                    break;
                case 5:
                    patient.setPassport(fileInput);
                    break;
                case 6:
                    patient.setPrefix(fileInput);
                    break;
                case 7:
                    patient.setFirst(fileInput);
                    break;
                case 8:
                    patient.setLast(fileInput);
                    break;
                case 9:
                    patient.setSuffix(fileInput);
                    break;
                case 10:
                    patient.setMaiden(fileInput);
                    break;
                case 11:
                    patient.setMarital(fileInput);
                    break;
                case 12:
                    patient.setRace(fileInput);
                    break;
                case 13:
                    patient.setEthnicity(fileInput);
                    break;
                case 14:
                    patient.setGender(fileInput);
                    break;
                case 15:
                    patient.setBirthPlace(fileInput);
                    break;
                case 16:
                    patient.setAddress(fileInput);
                    break;
                case 17:
                    patient.setCity(fileInput);
                    break;
                case 18:
                    patient.setState(fileInput);
                    break;
                case 19:
                    patient.setZip(fileInput);
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
