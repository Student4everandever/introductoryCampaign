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
            <a href="${pageContext.request.contextPath}/campaign/admin/add_university">Add university</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/edit_university">Edit university</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/add_specialty">Add specialty</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/edit_specialty">Edit specialty</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/add_subject">Add subject</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/put_marks">Put marks</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/form_applicants_rating">Form ratings</a>
            <a href="${pageContext.request.contextPath}/campaign/admin/send_mails">Send mails</a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="admin_base_universities" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="admin_base_university_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="admin_base_edit_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="admin_base_edit_specialty" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="admin_base_delete" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="university" items="${requestScope.universities}">
            <tr>
                <td>
                    <c:if test="${language != 'en'}">
                        <c:out value="${university.name_ukr}"/>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:out value="${university.name}"/>
                    </c:if>
                </td>
                <td>

                    <form action="${pageContext.request.contextPath}/campaign/admin/edit_university" method="post">

                        <input type="text" id="id" name="id" value="${university.id}" hidden>
                        <input type="text" id="name" name="name" value="${university.name}" hidden>
                        <input type="text" id="name_ukr" name="name_ukr" value="${university.name_ukr}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitEdit"
                               value="<fmt:message key="admin_base_edit_name" bundle="${link}"/>">
                    </form>
                </td>

                <td>

                    <form action="${pageContext.request.contextPath}/campaign/admin/edit_university_specialties" method="post">

                        <input type="text" id="id1" name="id" value="${university.id}" hidden>
                        <input type="text" id="name1" name="name" value="${university.name}" hidden>
                        <input type="text" id="name_ukr1" name="name_ukr" value="${university.name_ukr}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitEditSpecialty"
                               value="<fmt:message key="admin_base_edit_specialty" bundle="${link}"/>">
                    </form>
                </td>

                <td>
                    <form action="${pageContext.request.contextPath}/campaign/admin/delete_university" method="post">

                        <input type="text" id="university_id" name="university_id" value="${university.id}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitDelete"
                               value="<fmt:message key="admin_base_delete" bundle="${link}"/>">
                    </form>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
