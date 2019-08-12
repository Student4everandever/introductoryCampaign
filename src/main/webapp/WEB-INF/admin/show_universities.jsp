<html>
<head>
    <title>Admin page to show universities</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <form action="${pageContext.request.contextPath}/campaign/admin/add_university" method="post">
                <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message key="show_universities_to_admin_main" bundle="${link}"/></a>
                <input type="hidden" name="page" value="${requestScope.page}"/>
                <button   style="border: 0; padding: 0; display: inline; background: none; color: #0080ff">
                    <fmt:message key="show_universities_add_university" bundle="${link}"/></button>
            </form>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="show_universities_universities" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="show_universities_university_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_universities_edit_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_universities_edit_specialty" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_universities_delete" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="university" items="${requestScope.allUniversitiesByPage}">
            <tr>
                <td>
                    <c:if test="${sessionScope.language != 'en'}">
                        <c:out value="${university.name_ukr}"/>
                    </c:if>
                    <c:if test="${sessionScope.language == 'en'}">
                        <c:out value="${university.name}"/>
                    </c:if>
                </td>
                <td>

                    <form action="${pageContext.request.contextPath}/campaign/admin/edit_university" method="post">

                        <input type="text" id="id" name="id" value="${university.id}" hidden>
                        <input type="text" id="name" name="name" value="${university.name}" hidden>
                        <input type="text" id="name_ukr" name="name_ukr" value="${university.name_ukr}" hidden>
                        <input type="text" id="page" name="page" value="${requestScope.page}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitEdit"
                               value="<fmt:message key="show_universities_edit_name" bundle="${link}"/>">
                    </form>
                </td>

                <td>
                    <form action="${pageContext.request.contextPath}/campaign/admin/edit_university_specialties"
                          method="post">

                        <input type="text" id="id1" name="id" value="${university.id}" hidden>
                        <input type="text" id="name1" name="name" value="${university.name}" hidden>
                        <input type="text" id="name_ukr1" name="name_ukr" value="${university.name_ukr}" hidden>
                        <input type="text" id="page_num" name="page" value="${requestScope.page}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitEditSpecialty"
                               value="<fmt:message key="show_universities_edit_specialty" bundle="${link}"/>">
                    </form>
                </td>

                <td>
                    <form action="${pageContext.request.contextPath}/campaign/admin/delete_university" method="post">

                        <input type="text" id="university_id" name="university_id" value="${university.id}" hidden>
                        <input type="text" id="page_del" name="page" value="${requestScope.page}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submitDelete"
                               value="<fmt:message key="show_universities_delete" bundle="${link}"/>">
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
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/campaign/admin/show_universities?page=${number}">${number}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
