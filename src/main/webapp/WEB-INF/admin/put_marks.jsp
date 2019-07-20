<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<div>
    <ul class="navbar-nav">

        <li class="nav-item active">
            <a href="${pageContext.request.contextPath}/campaign/admin/admin_base">Admin main</a>
        </li>

    </ul>
</div>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="put_marks_enter_marks" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <c:forEach var="subject" items="${requestScope.subjects}">
                <th scope="col" style="text-align: left">
                    <c:if test="${language != 'en'}">
                        <c:out value="${subject.name_ukr}"/>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:out value="${subject.name}"/>
                    </c:if>
                </th>
            </c:forEach>
        </tr>
        </thead>

        <tbody>
        <form action="${pageContext.request.contextPath}/campaign/admin/put_marks" method="post">
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

        <input type="text" id="login" name="login" value="${user.login}" hidden>

        <input type="submit" class="form-control btn-submit" id="submitMarks"
               value="<fmt:message key="put_marks_enter" bundle="${link}"/>">
    </form>

</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
