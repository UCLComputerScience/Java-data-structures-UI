import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            ReadCSV csvInput = new ReadCSV();
            JSONFormatter Json = new JSONFormatter();
            List<Patient> str = csvInput.ReadCSV();
           /* System.out.println(str.toString());*/
            System.out.println("----------------JSON TEST ALL--------------------------");
            /*String output = Json.JSONFormatterAll(str);
            System.out.println(output);*/
            System.out.println("------------------JSON TEST SINGLE---------------------");
            String outSingle = Json.JSONFormatterSingle(str, 99);
            System.out.println(outSingle);
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown");
        }
    }
}
