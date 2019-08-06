<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page to show users who had exams</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message key="users_with_exams_to_admin_main" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="users_with_exams_users" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="users_with_exams_user_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="users_with_exams_put_users_marks" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>
            <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>
                    <c:if test="${language != 'en'}">
                        <c:out value="${user.name_ukr} ${user.lastName_ukr}"/>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:out value="${user.name} ${user.lastName}"/>
                    </c:if>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/campaign/admin/put_marks" method="post">

                        <input type="text" id="login" name="login" value="${user.login}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submit"
                               value="<fmt:message key="users_with_exams_put_marks" bundle="${link}"/>">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
