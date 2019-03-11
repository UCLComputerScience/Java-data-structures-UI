import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            ReadCSV csvInput = new ReadCSV();
            JSONFormatter Json = new JSONFormatter();
            List<Patient> str = csvInput.ReadCSV();
           /* System.out.println(str.toString());*/
            System.out.println("----------------JSON TEST--------------------------");
            String output = Json.JSONFormatter(str);
            System.out.println(output);

        }
        catch (Exception e)
        {
            System.out.println("Exception thrown");
        }
    }
}
