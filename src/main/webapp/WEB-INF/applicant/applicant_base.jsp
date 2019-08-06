<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant Page</title>
</head>
<body>
<h2><fmt:message key="applicant_base_hello" bundle="${link}"/></h2>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <c:if test="${requestScope.applied == null}">
            <a href="${pageContext.request.contextPath}/campaign/applicant/choose_specialty"><fmt:message key="applicant_base_apply_for_exams" bundle="${link}"/></a>
            </c:if>
            <a href="${pageContext.request.contextPath}/campaign/applicant/form_applicant_rating"><fmt:message key="applicant_base_form_rating" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<h5 style="color: red"><c:out value="${error}"/></h5>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
