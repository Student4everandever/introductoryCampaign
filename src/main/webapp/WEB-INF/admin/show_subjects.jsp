<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page to show subjects</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message key="show_subjects_to_admin_main" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/add_subject"><fmt:message key="show_subjects_add_subject" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="show_subjects_subjects" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="show_specialties_subjects_name" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="subject" items="${requestScope.subjects}">
            <tr>
                <td>
                    <c:if test="${language != 'en'}">
                        <c:out value="${subject.name_ukr}"/>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:out value="${subject.name}"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
