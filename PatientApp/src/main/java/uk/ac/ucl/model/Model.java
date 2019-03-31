package uk.ac.ucl.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model
{
  List<Patient> patientList = new ArrayList<>();
  private int index = 0;


  public List<Patient> readFile(String input) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(input));
    sc.useDelimiter(",|\n");
    while (sc.hasNext()) {
      Patient patient = new Patient();
      for (int i = 0;i<20;i++){
        String fileInput = sc.next();
        chooseMethods(fileInput, patient);
        ++index;
      }index = 0;
      patientList.add(patient);
    }
    patientList.remove(0);
    return patientList;
  }

  public List<Patient> getPatientList(){
    return patientList;
  }

  private void chooseMethods(String fileInput, Patient patient)
  {
    switch (index){
      case 0:
        patient.setID(fileInput);
        break;
      case 1:
        patient.setBirthDate(fileInput);
        break;
      case 2:
        patient.setDeathDate(fileInput);
        break;
      case 3:
        patient.setSSN(fileInput);
        break;
      case 4:
        patient.setDrivers(fileInput);
        break;
      case 5:
        patient.setPassport(fileInput);
        break;
      case 6:
        patient.setPrefix(fileInput);
        break;
      case 7:
        patient.setFirst(fileInput);
        break;
      case 8:
        patient.setLast(fileInput);
        break;
      case 9:
        patient.setSuffix(fileInput);
        break;
      case 10:
        patient.setMaiden(fileInput);
        break;
      case 11:
        patient.setMarital(fileInput);
        break;
      case 12:
        patient.setRace(fileInput);
        break;
      case 13:
        patient.setEthnicity(fileInput);
        break;
      case 14:
        patient.setGender(fileInput);
        break;
      case 15:
        patient.setBirthPlace(fileInput);
        break;
      case 16:
        patient.setAddress(fileInput);
        break;
      case 17:
        patient.setCity(fileInput);
        break;
      case 18:
        patient.setState(fileInput);
        break;
      case 19:
        patient.setZip(fileInput);
        break;
    }
  }

  public List<String> alphabetLib(String alphabet){
    List<String> outputPatient = new ArrayList<>();
    String[] libOrder = alphabet.split(",|\\.|-");
    String alp1 = libOrder[0];
    String alp2 = libOrder[1];
    for (Patient curPatient: patientList){
      String patientFirstName = curPatient.getFirst();
      if(patientFirstName.startsWith(alp1) || patientFirstName.startsWith(alp2)){
        String patientName = curPatient.getFirst() + "  " + curPatient.getLast();
        outputPatient.add(patientName);
      }
    }
    return outputPatient;
  }

  public List<String> alphabetList(){
    List<String> alphabets = new ArrayList<>();
    alphabets.add("A-B");
    alphabets.add("C-D");
    alphabets.add("E-F");
    alphabets.add("G-H");
    alphabets.add("I-J");
    alphabets.add("K-L");
    alphabets.add("M-N");
    alphabets.add("O-P");
    alphabets.add("Q-R");
    alphabets.add("S-T");
    alphabets.add("U-V");
    alphabets.add("W-X");
    alphabets.add("Y-Z");
    return alphabets;
  }


  public List getAllIDs() {
    List<String> patientListID = new ArrayList<>();
    for (int i = 0; i < patientList.size(); i++) {
      Object patientReference = patientList.get(i);
      String patientID = ((Patient) patientReference).getID();
      patientListID.add(patientID);
    }
    return patientListID;
  }

  public List getAllNames()
  {
    List<String> patientFullNames = new ArrayList<>();
    for (int i = 0; i < patientList.size(); i ++)
    {
      Object curPatient = patientList.get(i);
      String curPatientFirstName = ((Patient) curPatient).getFirst();
      String curPatientLastName = ((Patient) curPatient).getLast();
      String curPatientFullName = curPatientFirstName + "  " + curPatientLastName;
      patientFullNames.add(curPatientFullName);
    }
    return patientFullNames;
  }

  public int getAverageBirthYear() throws ParseException
  {
    int avgYearTmp = 0;
    for(int i = 0; i < patientList.size(); i++ )
    {
      Object patientReference = patientList.get(i);
      String patientAgeString = ((Patient) patientReference).getBirthDate();
      Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(patientAgeString);
      Calendar cal = Calendar.getInstance();
      cal.setTime(birthDate);
      avgYearTmp += cal.get(Calendar.YEAR);
    }
    int avgYear = avgYearTmp / patientList.size();
    return avgYear;
  }

  public int getAverageAge() throws ParseException
  {
    int averageYear = getAverageBirthYear();
    int curYear = Calendar.getInstance().get(Calendar.YEAR);
    int avgAge = curYear - averageYear;
    return avgAge;
  }

  public float getAgeDistribution() throws ParseException
  {
    int numberChildren = 0;
    int numberParent = 0;
    int curYear = Calendar.getInstance().get(Calendar.YEAR);
    for(int i = 0; i < patientList.size(); i++ ) {
      Object patientReference = patientList.get(i);
      String patientAgeString = ((Patient) patientReference).getBirthDate();
      Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(patientAgeString);
      Calendar cal = Calendar.getInstance();
      cal.setTime(birthDate);
      int patientBirthYear = cal.get(Calendar.YEAR);
      int patientAge = curYear - patientBirthYear;
      if ( 0 < patientAge && patientAge < 9 )
      {
        ++numberChildren;
      }++numberParent;
    }
    float ageDistribution = ((float) numberChildren / (float) numberParent);
    return ageDistribution * 1000;
  }

  // This also returns dummy data. The real version should use the keyword parameter to search
  // the patient data and return a list of matching items. It might return a List<Patient>
  // instead of a List<String>.
  public List<Patient> searchFor(String keyword)
  {
    List<Patient> shortList = new ArrayList<>();

    for(Patient patient: patientList){
      String fullName = patient.getFirst() + " " + patient.getLast();
      if(fullName.startsWith(keyword)){
        shortList.add(patient);
      }
      else if (fullName.endsWith(keyword)){
        shortList.add(patient);
      }
      else {}
    }
    return shortList;
  }

  public List<Patient> searchAge(String inputAgeString) throws ParseException{
    String[] ageRangeList = inputAgeString.split(",|-|\\.");
    int minAge = Integer.parseInt(ageRangeList[0]);
    int maxAge = Integer.parseInt(ageRangeList[1]);
    List<Patient> shortList = new ArrayList<>();
    for(Patient patient: patientList){
      int patientAge = getAge(patient);
      if(patientAge >= minAge && patientAge <= maxAge){
        shortList.add(patient);
      }
      else { }
    }
    return shortList;
  }

  public List<Patient> searchCity(String keyword, List<Patient> inputList){
    if(keyword!="" && !inputList.isEmpty()) {
      List<Patient> outputList = new ArrayList<>();
      for (Patient curPatient : inputList) {
        String patientCity = curPatient.getCity();
        if (patientCity.equals(keyword)) {
          outputList.add(curPatient);
        }
        else { }
      }
      return outputList;
    }
    else {
      return inputList;
    }
  }

  public List<Patient> searchGender(String inputGender, List<Patient> inputList){
    if(inputGender != "" && !inputList.isEmpty()) {
      List<Patient> outputList = new ArrayList<>();
      for (Patient curPatient : inputList) {
        String patientGender = curPatient.getGender();
        if (patientGender.equals(inputGender)) {
          outputList.add(curPatient);
        }
        else { }
      }
      return outputList;
    }
    else {
      return inputList;
    }
  }

  public List<Patient> searchState(String input,List<Patient> inputList){
    if(input!="" && !inputList.isEmpty()){
      List<Patient> outputList = new ArrayList<>();
      for(Patient curPatient : inputList){
        String patientState = curPatient.getState();
        if(patientState.equals(input)){
          outputList.add(curPatient);
        }
        else {}
      }
      return outputList;
    }
    else{
      return inputList;
    }
  }

  public List<Patient> searchRace(String input,List<Patient> inputList){
    if(input!="" && !inputList.isEmpty()){
      List<Patient> outputList = new ArrayList<>();
      for(Patient curPatient : inputList){
        String patientRace = curPatient.getRace();
        if(patientRace.equals(input)){
          outputList.add(curPatient);
        }
        else {}
      }
      return outputList;
    }
    else{
      return inputList;
    }
  }

  public List<String> processData(List<Patient> inputList){
    List<String> outputList = new ArrayList<>();
    for(Patient curPatient : inputList){
      String patientName = curPatient.getFirst() + " " + curPatient.getLast();
      outputList.add(patientName);
    }
    return outputList;
  }

  public String oldestPatient() throws ParseException{
    int oldestAge = 0;
    Patient oldestPatient = new Patient();
    for (Patient curPatient : patientList){
      int patientAge = getAge(curPatient);
      if(patientAge>oldestAge){
        oldestAge = patientAge;
        oldestPatient = curPatient;
      }
      else{}
    }
    String patientName = oldestPatient.getFirst() + " " + oldestPatient.getLast();
    return patientName;
  }

  public String youngestPatient() throws ParseException{
    int youngestAge = 100;
    Patient youngestPatient = new Patient();
    for(Patient curPatient : patientList){
      int patientAge = getAge(curPatient);
      if(patientAge<youngestAge){
        youngestAge = patientAge;
        youngestPatient = curPatient;
      }
    }
    String patientName = youngestPatient.getFirst() + " " + youngestPatient.getLast();
    return patientName;
  }

  public int getAge(Patient curPatient) throws ParseException{
    String birthDateString = curPatient.getBirthDate();
    Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateString);
    Calendar cal = Calendar.getInstance();
    cal.setTime(birthDate);
    int birthYear = cal.get(cal.YEAR);
    int curYear = Calendar.getInstance().get(Calendar.YEAR);
    int patientAge = curYear - birthYear;
    return patientAge;
  }

  public List<Integer> patientAgeRange() throws ParseException{
    int patientLessThan20 = 0;
    int patientLessThan40=0;
    int patientLessThan60=0;
    int patientLessThan80 = 0;
    int patientLessThan100= 0;
    int patientMoreThan100=0;
    List<Integer> patientAgeList = new ArrayList();
    for(Patient curPatient: patientList){
      int patientAge = getAge(curPatient);
      if(patientAge>=0 && patientAge<=20){
        ++patientLessThan20;
      }
      else if (patientAge>20 && patientAge<=40){
        ++patientLessThan40;
      }
      else if(patientAge>40 && patientAge<=60){
        ++patientLessThan60;
      }
      else if(patientAge>60 && patientAge<=80){
        ++patientLessThan80;
      }
      else if(patientAge>80 && patientAge<=100){
        ++patientLessThan100;
      }
      else {
        ++patientMoreThan100;
      }
    }
    patientAgeList.add(patientLessThan20);
    patientAgeList.add(patientLessThan40);
    patientAgeList.add(patientLessThan60);
    patientAgeList.add(patientLessThan80);
    patientAgeList.add(patientLessThan100);
    patientAgeList.add(patientMoreThan100);
    return patientAgeList;
  }


}
