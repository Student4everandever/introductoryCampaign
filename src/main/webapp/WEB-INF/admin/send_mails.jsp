<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mails page</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message key="admin_mails_send_to_admin_main" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">

    <form action="${pageContext.request.contextPath}/campaign/admin/send_mails" method="post">
        <input type="text" id="send_mail" name="send_mail" value="true" hidden>

        <input type="submit" class="form-control btn-submit" id="submit"
               value="<fmt:message key="admin_mails_send_mails" bundle="${link}"/>">
    </form>
    <br/>

    <h2><fmt:message key="admin_mails_users" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="admin_mails_user_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="admin_mails_rating" bundle="${link}"/></th>
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
                    <c:out value="${user.rating}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>

