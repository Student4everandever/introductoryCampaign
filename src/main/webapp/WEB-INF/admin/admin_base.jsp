<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h1>Hello Admin</h1>
<br/>
<a href="${pageContext.request.contextPath}/campaign/admin/add_university">Add university</a>
<a href="${pageContext.request.contextPath}/campaign/admin/edit_university">Edit university</a>
<a href="${pageContext.request.contextPath}/campaign/admin/add_specialty">Add specialty</a>
<a href="${pageContext.request.contextPath}/campaign/admin/edit_specialty">Edit specialty</a>
<a href="${pageContext.request.contextPath}/campaign/admin/put_marks">Put marks</a>
<a href="${pageContext.request.contextPath}/campaign/admin/form_applicants_rating">Form ratings</a>
<a href="${pageContext.request.contextPath}/campaign/admin/send_mails">Send mails</a>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
