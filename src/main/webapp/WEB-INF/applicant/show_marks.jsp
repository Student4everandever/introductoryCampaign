<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant marks and rating page</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<a href="/campaign/applicant/form_applicant_rating"><fmt:message key="show_marks_back" bundle="${link}"/></a>
<br/>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="show_marks_rating" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="show_marks_subject_1" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_marks_subject_2" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_marks_subject_3" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_marks_overall" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <c:forEach var="mark" items="${requestScope.marks}">
                <td>
                    <c:out value="${mark}"/>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>

<div style="position: absolute; bottom: 70px; width: 100%; height: 60px;">
    <h6><fmt:message key="show_marks_formula" bundle="${link}"/></h6>
    <h6><fmt:message key="show_marks_indices" bundle="${link}"/></h6>
</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
