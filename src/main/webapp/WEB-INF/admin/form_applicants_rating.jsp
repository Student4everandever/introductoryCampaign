<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicants rating page</title>
</head>
<body>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<a href="/campaign/admin/admin_base">Back</a>
<br/>

<div class="container-fluid col-lg-7 col-lg-offset-5 mt-3 pb-2 text-center ">
    <h2><fmt:message key="applicants_rating_rating" bundle="${link}"/></h2>

    <table class="table my-5">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="applicants_rating_applicant" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="applicants_rating_subject_1" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="applicants_rating_subject_2" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="applicants_rating_subject_3" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="applicants_rating_overall" bundle="${link}"/></th>
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

                </td>

                <td>

                </td>
                <td>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../views/footer.jsp" %>
</body>
</html>
