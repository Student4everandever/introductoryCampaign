<html>
<head>
    <title>Applicant marks and rating page</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>
<c:if test="${not empty requestScope.error}">
    <h5 style="color: red"><fmt:message key="${requestScope.error}" bundle="${link}"/></h5>
</c:if>

<a href="${pageContext.request.contextPath}/campaign/applicant/form_applicant_rating"><fmt:message key="show_marks_back"
                                                                                                   bundle="${link}"/></a>
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

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
