import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            ReadCSV csvInput = new ReadCSV();
            List<Patient> str = csvInput.readCSV();
            System.out.println(str.toString());
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown");
        }
    }
}
