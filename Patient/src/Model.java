import java.util.List;
import java.util.

public class Model {
    ReadCSV files = new ReadCSV();

    public List readFile(){
        List<Patient> str = files.ReadCSV();
    }
}
