<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant Page</title>
</head>
<body>
<h1><fmt:message key="applicant_base_hello" bundle="${link}"/></h1>
<br/>

<c:if test="${requestScope.message == null}">
    <a href="${pageContext.request.contextPath}/campaign/applicant/choose_specialty"><fmt:message
            key="applicant_base_apply_for_exams" bundle="${link}"/></a>
    <br/>
</c:if>
<h5 style="color: red"><c:out value="${error}"/></h5>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
