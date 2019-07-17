<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2>Hello Admin</h2>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/show_universities">Universities</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/show_specialties">Specialties</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/show_subjects">Subjects</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/add_specialty">Add specialty</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/edit_specialty">Edit specialty</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/add_subject">Add subject</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/put_marks">Put marks</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/form_applicants_rating">Form ratings</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/send_mails">Send mails</a>
        </li>

    </ul>
</div>


<%@ include file="../views/footer.jsp" %>
</body>
</html>
