<%@ page import="uk.ac.ucl.servlets.getUploadedFile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Data App</title>
</head>
<body>

<%
    getUploadedFile.fileDetail test = (getUploadedFile.fileDetail) request.getAttribute("uploadedFile");
    String fileName = test.getFileName();
    long fileSize = test.getFileSize();
    String fileStatus = test.getUploadStatus();

%>
File Name: <%=fileName%>
<br/>
File Size: <%=fileSize%>
<br/>
File Upload Status: <%=fileStatus%>
<br/>
<a href="index.html">Back to Index</a>
</body>
</html>
