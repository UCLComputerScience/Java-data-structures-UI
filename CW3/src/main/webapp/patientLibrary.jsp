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
    <h2>Patient Library:</h2>
    <%
        List<String> alphabets = (List<String>) request.getAttribute("alphabets");
        for(String curAlp : alphabets){
            String href = "patientLibrary.html?lib=" + curAlp;
            String libAlp = curAlp;
    %>
    <li><a href="<%=href%>"><%=libAlp%></a>
    </li>
        <%}%>
<div/>
<jsp:include page="/footer.jsp"/>
</body>
</html>
