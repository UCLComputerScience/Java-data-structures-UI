import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Model {
    ReadCSV files = new ReadCSV();
    Scanner sc = new Scanner(System.in);
    JSONFormatter json = new JSONFormatter();
    List<Patient> str;
    List<String> patientListID;
    List<String> patientFullNames;


    public void readFile() throws FileNotFoundException {
        System.out.println("Please input filepath");
        String input = sc.nextLine();
        sc.close();
        str = files.ReadCSV(input);
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

    public String getAllIDs() {
        StringBuilder stringBuild = new StringBuilder();
        for (int i = 0; i < str.size(); i++) {
            Object patientReference = str.get(i);
            String patientID = ((Patient) patientReference).getID();
            patientListID.add(patientID);
            stringBuild.append("{\n");
            stringBuild.append("\t" + "\"" + "patients IDs" + "\"" + ": [");
            int listSize = patientListID.size();
            for (int x = 0; x < listSize; x++) {
                stringBuild.append(patientListID.get(x));
                stringBuild.append("\n");
            }
        }
        String output = stringBuild.toString();
        return output;
    }

    public String getAllNames()
    {
        StringBuilder stringBuild = new StringBuilder();
        for (int i = 0; i < str.size(); i++) {
            Object patientReference = str.get(i);
            String patientFirstName = ((Patient) patientReference).getFirst();
            String patientLastName = ((Patient) patientReference).getLast();
            String fullName = patientFirstName + patientLastName;
            patientFullNames.add(fullName);
            stringBuild.append("{\n");
            stringBuild.append("\t" + "\"" + "patients Full Names" + "\"" + ": [");
            int listSize = patientFullNames.size();
            for (int x = 0; x < listSize; x++) {
                stringBuild.append(patientFullNames.get(x));
                stringBuild.append("\n");
            }
        }String ouput = stringBuild.toString();
        return ouput;
    }
}
