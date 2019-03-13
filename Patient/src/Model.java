import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Model {
    ReadCSV files = new ReadCSV();
    Scanner sc = new Scanner(System.in);
    JSONFormatter json = new JSONFormatter();
    List<Patient> str;
    List<String> patientFullNames = new ArrayList<>();


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

    public String getAllNames()
    {
        StringBuilder stringBuild = new StringBuilder();
        stringBuild.append("{\n");
        stringBuild.append("\t" + "\"" + "patients Full Names" + "\"" + ": [\n");
        for (int i = 0; i < str.size(); i++) {
            Object patientReference = str.get(i);
            String patientFirstName = ((Patient) patientReference).getFirst();
            String patientLastName = ((Patient) patientReference).getLast();
            String fullName = patientFirstName + " " + patientLastName;
            patientFullNames.add(fullName);
            for (int x = 0; x < patientFullNames.size(); x++) {
                stringBuild.append("\t\t" + patientFullNames.get(x));
                stringBuild.append("\n");
            }
        }
        stringBuild.append("\t]");
        String ouput = stringBuild.toString();
        return ouput;
    }

}
