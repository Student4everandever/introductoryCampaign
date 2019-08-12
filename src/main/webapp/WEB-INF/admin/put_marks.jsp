<html>
<head>
    <title>Admin page to put marks</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>
<c:if test="${not empty requestScope.error}">
    <h5 style="color: red"><fmt:message key="${requestScope.error}" bundle="${link}"/></h5>
</c:if>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base"><fmt:message
                    key="put_marks_admin_main" bundle="${link}"/></a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="put_marks_enter_marks" bundle="${link}"/></h2>

    <form action="${pageContext.request.contextPath}/campaign/admin/put_marks" method="post">
        <table class="table my-5">

            <thead>
            <tr>
                <c:forEach var="subject" items="${requestScope.subjects}">
                    <th scope="col" style="text-align: left">
                        <c:if test="${sessionScope.language != 'en'}">
                            <c:out value="${subject.name_ukr}"/>
                        </c:if>
                        <c:if test="${sessionScope.language == 'en'}">
                            <c:out value="${subject.name}"/>
                        </c:if>
                    </th>
                </c:forEach>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="mark1" id="mark1"
                               placeholder="<fmt:message key="put_marks_enter_mark" bundle="${link}"/>"
                               value="" style="width: 200px" required>
                        <div class="invalid-feedback">
                            Please enter valid mark.
                        </div>
                    </div>
                </td>
                <td>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="mark2" id="mark2"
                               placeholder="<fmt:message key="put_marks_enter_mark" bundle="${link}"/>"
                               value="" style="width: 200px" required>
                        <div class="invalid-feedback">
                            Please enter valid mark.
                        </div>
                    </div>
                </td>
                <td>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="mark3" id="mark3"
                               placeholder="<fmt:message key="put_marks_enter_mark" bundle="${link}"/>"
                               value="" style="width: 200px" required>
                        <div class="invalid-feedback">
                            Please enter valid mark.
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <label for="login"></label>
        <input type="text" id="login" name="login" value="${requestScope.user.login}" hidden>

        <input type="submit" class="form-control btn-submit" id="submitMarks"
               value="<fmt:message key="put_marks_enter" bundle="${link}"/>">
    </form>

</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
