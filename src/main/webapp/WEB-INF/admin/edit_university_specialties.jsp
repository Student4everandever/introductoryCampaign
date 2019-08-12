<html>
<head>
    <title>Admin edit university page</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<a href="${pageContext.request.contextPath}/campaign/admin/show_universities?page=${requestScope.page}"><fmt:message
        key="edit_university_back" bundle="${link}"/></a>
<br/>

<h2><fmt:message key="edit_university_enter_new_data" bundle="${link}"/></h2>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>
<c:if test="${not empty requestScope.error}">
    <h5 style="color: red"><fmt:message key="${requestScope.error}" bundle="${link}"/></h5>
</c:if>

<div class="container" style="vertical-align: auto">

    <c:if test="${sessionScope.language != 'en'}">
        <h3><c:out value="${requestScope.university.name_ukr}"/></h3>
    </c:if>
    <c:if test="${sessionScope.language == 'en'}">
        <h3><c:out value="${requestScope.university.name}"/></h3>
    </c:if>


    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <div class="row">

                <div class="col-md-12 mb-2 mt-4">

                    <form action="${pageContext.request.contextPath}/campaign/admin/edit_university_specialties"
                          method="post"
                          class="needs-validation">

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="add_specialty"><fmt:message key="edit_university_add_specialty"
                                                                        bundle="${link}"/></label>
                                <select class="custom-select" multiple name="add_specialty" id="add_specialty">
                                    <c:forEach var="specialty1" items="${requestScope.nonUniversitySpecialties}">
                                        <c:if test="${sessionScope.language != 'en'}">
                                            <option value="${specialty1.title_ukr}">${specialty1.title_ukr}</option>
                                        </c:if>
                                        <c:if test="${sessionScope.language == 'en'}">
                                            <option value="${specialty1.title}">${specialty1.title}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="remove_specialty"><fmt:message key="edit_university_remove_specialty"
                                                                           bundle="${link}"/></label>
                                <select class="custom-select" multiple name="remove_specialty"
                                        id="remove_specialty">
                                    <c:forEach var="specialty2" items="${requestScope.universitySpecialties}">
                                        <c:if test="${sessionScope.language != 'en'}">
                                            <option value="${specialty2.title_ukr}">${specialty2.title_ukr}</option>
                                        </c:if>
                                        <c:if test="${sessionScope.language == 'en'}">
                                            <option value="${specialty2.title}">${specialty2.title}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <label for="id"></label>
                        <input type="text" id="id" name="id" value="${requestScope.university.id}" hidden>
                        <label for="page"></label>
                        <input type="text" id="page" name="page" value="${requestScope.page}" hidden>
                        <input type="hidden" name="submitted" value="true">

                        <hr class="mb-4">
                        <input type="submit" class="form-control btn-submit" name="removeUniversitySpecialty"
                               value="<fmt:message key="edit_university_edit_university_specialty" bundle="${link}"/>">

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>

