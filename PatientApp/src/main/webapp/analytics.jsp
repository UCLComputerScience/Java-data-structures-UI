<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patient Analysis:</h2>
    <u1>
        <%
            String oldestPatient = (String) request.getAttribute("oldestPatient");
            String youngestPatient = (String) request.getAttribute("youngestPatient");
            int averageAge = (int) request.getAttribute("averageAge");
            List<Integer> patientAgeRange = (List<Integer>) request.getAttribute("patientAgeRange");
        %>
        Oldest Patient: <%=oldestPatient%><br/>
        Youngest Patient: <%=youngestPatient%><br/>
        Average Age: <%=averageAge%><br/>
        No. of patients aged between 0 to 20: <%=patientAgeRange.get(0)%><br/>
        No. of patients aged between 20 to 40: <%=patientAgeRange.get(1)%><br/>
        No. of patients aged between 40 to 60: <%=patientAgeRange.get(2)%><br/>
        No. of patients aged between 60 to 80: <%=patientAgeRange.get(3)%><br/>
        No. of patients aged between 80 to 100: <%=patientAgeRange.get(4)%><br/>
        No. of patients aged more than age 100: <%=patientAgeRange.get(5)%><br/>
<jsp:include page="/footer.jsp"/>
</body>
</html>
