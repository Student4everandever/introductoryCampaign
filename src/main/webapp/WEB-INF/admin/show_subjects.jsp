<html>
<head>
    <title>Admin page to show subjects</title>
</head>

<%@ include file="../views/header_logout.jsp" %>


<body>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>

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
        <tr style="text-align: center">
            <th scope="col"><fmt:message key="show_specialties_subjects_name" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="subject" items="${requestScope.subjects}">
            <tr style="text-align: center">
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

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
