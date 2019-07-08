<%@ include file="../views/header_logout.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant Page</title>
</head>
<body>
<h1>Hello Applicant</h1>
<br/>
<a href="${pageContext.request.contextPath}/campaign/applicant/choose_specialty">Choose specialty</a>

<%@ include file="../views/footer.jsp"%>
</body>
</html>
