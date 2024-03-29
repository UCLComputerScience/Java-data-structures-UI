import java.util.List;
import java.util.ArrayList;

public class JSONFormatter  {
    public String JSONFormatterAll(List<Patient> inputList)
    {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        str.append("\t" + "\"" + "patients" + "\"" + ": [");
        int patientListSize = inputList.size();
        for (int i = 0; i < patientListSize; i++){
            str.append("\n\t\t{\n");
            Object curPatient = inputList.get(i);
            ArrayList<String> keys = new ArrayList<>(((Patient) curPatient).contents.keySet());
            int size = ((Patient) curPatient).contents.size();
            for (int x = 0; x < size ; x ++) {
                if(x<size -1) {
                    str.append("\t\t" + "\"" + keys.get(x) + "\"");
                    str.append(": ");
                    String curValue = ((Patient) curPatient).contents.get(keys.get(x));
                    str.append("\"" + curValue + "\"");
                    str.append(",");
                    str.append("\n");
                }
                else{
                    str.append("\t\t" + "\"" + keys.get(x) + "\"");
                    str.append(": ");
                    String curValue = ((Patient) curPatient).contents.get(keys.get(x));
                    str.append("\"" + curValue + "\"");
                    str.append("\n");
                }
            }if (i < patientListSize - 1){ str.append("\t\t},");}
            else{str.append("\t\t}");}
        }
        str.append("\n");
        str.append("\t]");
        str.append("\n");
        str.append("}");
        String returnStr = str.toString();
        return returnStr;
    }


    public String JSONFormatterSingle(List<Patient> inputList, int index) {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        str.append("\t" + "\"" + "patients" + "\"" + ": [");
        str.append("\n\t\t{\n");
        Object curPatient = inputList.get(index);
        ArrayList<String> keys = new ArrayList<>(((Patient) curPatient).contents.keySet());
        int size = ((Patient) curPatient).contents.size();
        for (int x = 0; x < size ; x ++) {
            if ( x < size - 1) {
                str.append("\t\t");
                str.append("\"" + keys.get(x) + "\"");
                str.append(": ");
                String curValue = ((Patient) curPatient).contents.get(keys.get(x));
                str.append("\"" + curValue + "\"");
                str.append(",");
                str.append("\n");
            }
            else {
                str.append("\t\t");
                str.append("\"" + keys.get(x) + "\"");
                str.append(": ");
                String curValue = ((Patient) curPatient).contents.get(keys.get(x));
                str.append("\"" + curValue + "\"");
                str.append("\n");
            }
        }
        str.append("\t\t}");
        str.append("\n");
        str.append("\t]");
        str.append("\n");
        str.append("}");
        String returnStr = str.toString();
        return returnStr;
    }


}
