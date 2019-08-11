<html>
<head>
    <title>Applicant Page</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

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

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>
<c:if test="${not empty requestScope.error}">
    <h5 style="color: red"><fmt:message key="${requestScope.error}" bundle="${link}"/></h5>
</c:if>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
