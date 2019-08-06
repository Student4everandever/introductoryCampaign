<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Applicants rating page</title>
</head>
<body>

<h5 style="color: limegreen"><c:out value="${requestScope.message}"/></h5>
<h5 style="color: red"><c:out value="${requestScope.error}"/></h5>

<a href="${pageContext.request.contextPath}/campaign/applicant/applicant_base"><fmt:message key="applicants_rating_back"
                                                                                            bundle="${link}"/></a>
<br/>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="applicants_rating_list" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="applicants_rating_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="applicants_rating_rating" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="applicants_rating_show_marks" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${requestScope.allUsersByPage}">
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
                <td>
                    <form action="${pageContext.request.contextPath}/campaign/applicant/show_marks" method="post">

                        <input type="text" id="login" name="login" value="${user.login}" hidden>

                        <input type="submit" class="form-control btn-submit" id="submit"
                               value="<fmt:message key="applicants_rating_show_marks" bundle="${link}"/>">
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
                                         href="${pageContext.request.contextPath}/campaign/applicant/form_applicants_rating?page=${number}">${number}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
