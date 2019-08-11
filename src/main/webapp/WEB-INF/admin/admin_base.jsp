<html>
<head>
    <title>Admin Page</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>
<h2><fmt:message key="admin_base_hello" bundle="${link}"/></h2>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/show_universities"><fmt:message key="admin_base_universities" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/show_specialties"><fmt:message key="admin_base_specialties" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/show_subjects"><fmt:message key="admin_base_subjects" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/users_with_exams"><fmt:message key="admin_base_put_marks" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/send_mails"><fmt:message key="admin_base_send_mails" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
