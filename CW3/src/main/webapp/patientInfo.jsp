<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Patient" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.awt.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patient's Information:</h2>
    <ul>
        <%
            String patientID = request.getParameter("id");
            List<Patient> patientTest = (List<Patient>) request.getAttribute("patientList");
            List<String> allAttributes = new ArrayList<>();
            for (Patient curPatient: patientTest) {
                String patientFullName = curPatient.getFirst() + "  " + curPatient.getLast();
                if (patientFullName.equals(patientID)) {
                    allAttributes.add("Birth Date: " + curPatient.getBirthDate());
                    allAttributes.add("Race: " + curPatient.getRace());
                    allAttributes.add("State: " + curPatient.getState());
                    allAttributes.add("Birth Place: " + curPatient.getBirthPlace());
                    allAttributes.add("Death Date: " + curPatient.getDeathDate());
                    allAttributes.add("First Name: " + curPatient.getFirst());
                    allAttributes.add("Last Name: " + curPatient.getLast());
                    allAttributes.add("ID: " + curPatient.getID());
                    allAttributes.add("Address: " + curPatient.getAddress());
                    allAttributes.add("City: " + curPatient.getCity());
                    allAttributes.add("Drivers: " + curPatient.getDrivers());
                    allAttributes.add("Zip Code: " + curPatient.getZip());
                    allAttributes.add("Gender: " + curPatient.getGender());
                    allAttributes.add("Ethnicity: " + curPatient.getEthnicity());
                    allAttributes.add("Maiden: " + curPatient.getMaiden());
                    allAttributes.add("Marital: " + curPatient.getMarital());
                    allAttributes.add("Passport Number: " + curPatient.getPassport());
                    allAttributes.add("Prefix: " + curPatient.getPrefix());
                    allAttributes.add("SSN: " + curPatient.getSsn());
                    allAttributes.add("Suffix: " + curPatient.getSuffix());
                } else {
                }
            }
            for(String test: allAttributes){
                String output = test;

%>
<li><%=output%></li>
        <%}%>
    </ul>
    <br/>
    <a href="index.html">Back to Index</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
