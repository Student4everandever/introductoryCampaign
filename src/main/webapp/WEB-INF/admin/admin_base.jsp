<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2><fmt:message key="admin_base_hello" bundle="${link}"/></h2>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/show_universities"><fmt:message key="admin_base_universities" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/show_specialties"><fmt:message key="admin_base_specialties" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/show_subjects"><fmt:message key="admin_base_subjects" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/put_marks"><fmt:message key="admin_base_put_marks" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/form_applicants_rating"><fmt:message key="admin_base_form_ratings" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/send_mails"><fmt:message key="admin_base_send_mails" bundle="${link}"/></a>
        </li>

    </ul>
</div>


<%@ include file="../views/footer.jsp" %>
</body>
</html>
