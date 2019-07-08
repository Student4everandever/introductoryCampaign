<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<a href="/campaign/admin/admin_base">Back</a>
<br/>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="put_marks_enter_marks" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="put_marks_subject_name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="put_marks_mark" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="report" items="${requestScope.reportRequests}">
            <tr>
                <td>
                    <c:if test="${language != 'en'}">
                        <c:out value="${report.topic_ukr}"/>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:out value="${report.topic}"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${language != 'en'}">
                        <c:out value="${report.conferenceTitle_ukr}"/>
                    </c:if>
                    <c:if test="${language == 'en'}">
                        <c:out value="${report.conferenceTitle}"/>
                    </c:if>
                </td>
                <td>
                    <div class="mb-3">
                        <%--<label for="mark${report.number}"><fmt:message key="add_specialty_specialty_title" bundle="${link}"/></label>--%>
                        <input type="number" min="0" max="200" step="1" class="form-control" name="mark" id="mark"
                               placeholder="<fmt:message key="put_marks_enter_mark" bundle="${link}"/>"
                               value="" required>
                        <div class="invalid-feedback">
                            Please enter valid mark.
                        </div>
                    </div>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/campaign/admin/put_marks" method="post">

        <input type="text" id="applicant_id" name="applicant_id" value="${applicant.id}" hidden>
        <input type="text" id="specialty_id" name="specialty_id" value="${specialty.id}" hidden>

        <input type="submit" class="form-control btn-submit" id="submitApprove"
               value="<fmt:message key="put_marks_enter" bundle="${link}"/>">
    </form>

</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
