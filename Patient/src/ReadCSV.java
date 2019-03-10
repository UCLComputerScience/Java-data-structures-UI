import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.lang.reflect.*;


public class ReadCSV {
    Patient patient = new Patient();
    Class patientClass = Patient.class;
    Method[] allMethods = patientClass.getMethods();
    List<Method> setters = new ArrayList<>();
    List<Patient> patientList = new ArrayList<>();

    public List<Patient> readCSV() throws FileNotFoundException, InvocationTargetException, IllegalAccessException {
        Scanner sc = new Scanner(new File("User/Desktop"));
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            String fileInput = sc.next();
            fileInput = readNull(fileInput);
            getMethods();
            for(Method curMethod:setters)
            {
                Patient inputPatient = (Patient) curMethod.invoke(patient,fileInput);
                patientList.add(inputPatient);
            }
        }return patientList;
    }

    public List<Method> getMethods()
    {
        for(Method curMethod:allMethods)
        {
            if(curMethod.getName().startsWith("set"))
            {
                setters.add(curMethod);
            }
        }return setters;
    }

    public String readNull(String fileInput) {
        if (fileInput == "") {
            return "null";
        } else {
            return fileInput;
        }
    }
}
