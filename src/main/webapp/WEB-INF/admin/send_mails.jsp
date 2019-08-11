<html>
<head>
    <title>Mails page</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message
                    key="admin_mails_send_to_admin_main" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">

    <form action="${pageContext.request.contextPath}/campaign/admin/send_mails" method="post">
        <label for="send_mail"></label>
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
                        <c:if test="${user.rating >= 100}">
                            <c:out value="${user.name_ukr} ${user.lastName_ukr}"/>
                        </c:if>
                        <c:if test="${user.rating < 100}">
                            <p style="color: red"><c:out value="${user.name_ukr} ${user.lastName_ukr}"/></p>
                        </c:if>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:if test="${user.rating >= 100}">
                            <c:out value="${user.name} ${user.lastName}"/>
                        </c:if>
                        <c:if test="${user.rating < 100}">
                            <p style="color: red"><c:out value="${user.name} ${user.lastName}"/></p>
                        </c:if>
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

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>