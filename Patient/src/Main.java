import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            Model model = new Model();
            model.readFile();
            String testStrPatientID = model.getAllIDs();
            String testStrPatientName = model.getAllNames();
            String testStrJsonAll = model.getAllPatient();
            String testStrJsonSingle = model.getSinglePatient(52);
            System.out.println(testStrPatientID);
            System.out.println(testStrPatientName);
            /*ReadCSV csvInput = new ReadCSV();
            JSONFormatter Json = new JSONFormatter();
            List<Patient> str = csvInput.ReadCSV("C:/Users/Lian/Desktop/cw2Data/patients100.csv");
            System.out.println("----------------JSON TEST ALL--------------------------");
            String output = Json.JSONFormatterAll(str);
            System.out.println(output);
            System.out.println("------------------JSON TEST SINGLE---------------------");
            String outSingle = Json.JSONFormatterSingle(str, 99);
            System.out.println(outSingle);*/

        }
        catch (Exception e)
        {
            System.out.println("Exception thrown");
        }
    }
}
