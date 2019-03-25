import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            GUI gui = new GUI();
            gui.panelContainer();
//            Model model = new Model();
//            model.readFile("C:/Users/Lian/Desktop/cw2Data/patients100.csv");
//            float test = model.getAgeDistribution();
//            System.out.println(test);


            /*String testStrPatientID = model.getAllIDs();*/
            /*System.out.println(model.getAllIDs());*/
            /*String testStrPatientName = model.getAllNames();
            System.out.println(testStrPatientName);*/


            /*String testStrJsonAll = model.getAllPatient();
            System.out.println(testStrJsonAll);
            String testStrJsonSingle = model.getSinglePatient(52);
            System.out.println(testStrJsonSingle);*/


      /*      ReadCSV csvInput = new ReadCSV();
            JSONFormatter Json = new JSONFormatter();
            List<Patient> str = csvInput.ReadCSV("C:/Users/Lian/Desktop/cw2Data/patients100.csv");
            System.out.println("----------------JSON TEST ALL--------------------------");
            String output = Json.JSONFormatterAll(str);
            System.out.println(output);
            System.out.println("------------------JSON TEST SINGLE---------------------");
            String outSingle = Json.JSONFormatterSingle(str, 99);
            System.out.println(outSingle);*/

//            List<Patient> list = model.readJson("C:/Users/USER/Desktop/cw2Data/patients1000.csv.json");
//            System.out.println(list);




        }
        catch (Exception e)
        {
            System.out.println("Exception thrown");
        }


    }
}
