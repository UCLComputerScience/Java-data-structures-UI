import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            ReadCSV csvInput = new ReadCSV();
            List<Patient> str = csvInput.readCSV();
            System.out.println(str.toString());
            System.out.println(str.get(0));
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown");
        }
    }
}
