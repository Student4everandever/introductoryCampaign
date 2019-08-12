<html>
<head>
    <title>Admin page to show specialties</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message key="show_specialties_to_admin_main" bundle="${link}"/></a>
            <a href="${pageContext.request.contextPath}/campaign/admin/add_specialty"><fmt:message key="show_specialties_add_specialty" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="show_specialties_specialties" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="show_specialties_specialty_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_specialties_edit_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_specialties_delete" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="specialty" items="${requestScope.allSpecialtiesByPage}">
            <tr>
                <td>
                    <c:if test="${sessionScope.language != 'en'}">
                        <c:out value="${specialty.title_ukr}"/>
                    </c:if>
                    <c:if test="${sessionScope.language == 'en'}">
                        <c:out value="${specialty.title}"/>
                    </c:if>
                </td>
                <td>

                    <form action="${pageContext.request.contextPath}/campaign/admin/edit_specialty" method="post">

                        <input type="text" id="id" name="id" value="${specialty.id}" hidden>
                        <input type="text" id="title" name="title" value="${specialty.title}" hidden>
                        <input type="text" id="title_ukr" name="title_ukr" value="${specialty.title_ukr}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitEdit"
                               value="<fmt:message key="show_specialties_edit_name" bundle="${link}"/>">
                    </form>
                </td>

                <td>
                    <form action="${pageContext.request.contextPath}/campaign/admin/delete_specialty" method="post">

                        <input type="text" id="specialty_id" name="specialty_id" value="${specialty.id}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitDelete"
                               value="<fmt:message key="show_specialties_delete" bundle="${link}"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="position: absolute;
  bottom: 80px;
  width: 100%;
  height: 30px;">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:forEach var="number" begin="1" end="${requestScope.numberOfPages}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/campaign/admin/show_specialties?page=${number}">${number}</a></li>
            </c:forEach>
        </ul>
    </nav>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
