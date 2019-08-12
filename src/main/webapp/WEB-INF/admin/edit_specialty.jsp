<html>
<head>
    <title>Admin edit specialty title page</title>
</head>

<%@ include file="../views/header_logout.jsp" %>

<body>

<a href="${pageContext.request.contextPath}/campaign/admin/show_specialties"><fmt:message key="edit_specialty_back"
                                                                                          bundle="${link}"/></a>
<br/>

<h2><fmt:message key="edit_specialty_enter_new_data" bundle="${link}"/></h2>

<c:if test="${not empty requestScope.message}">
    <h5 style="color: limegreen"><fmt:message key="${requestScope.message}" bundle="${link}"/></h5>
</c:if>
<c:if test="${not empty requestScope.error}">
    <h5 style="color: red"><fmt:message key="${requestScope.error}" bundle="${link}"/></h5>
</c:if>

<div class="container" style="vertical-align: auto">


    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/admin/edit_specialty" method="post"
                  class="needs-validation">

                <div class="mb-3">
                    <label for="title"><fmt:message key="edit_specialty_specialty_title" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="title" id="title"
                           placeholder="${requestScope.specialty.title}" value="">
                    <div class="invalid-feedback">
                        Please enter valid title.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="title_ukr"><fmt:message key="edit_specialty_specialty_title_ukr"
                                                        bundle="${link}"/></label>
                    <input type="text" class="form-control" name="title_ukr" id="title_ukr"
                           placeholder="${requestScope.specialty.title_ukr}" value="">
                    <div class="invalid-feedback">
                        Please enter valid title in ukrainian.
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-2">
                        <label for="subject_1"><fmt:message key="edit_specialty_subject_label_subject1"
                                                            bundle="${link}"/></label>
                        <input type="text" class="form-control" name="subject_1" id="subject_1" value="" hidden>
                        <p><fmt:message key="edit_specialty_subject_subject1" bundle="${link}"/></p>
                    </div>

                    <div class="col-md-4 mb-2">
                        <label for="subject_2"><fmt:message key="edit_specialty_subject_choose_subject2"
                                                            bundle="${link}"/>
                            <br/>
                            <c:forEach var="exam" items="${requestScope.exam2}">
                                <c:if test="${sessionScope.language != 'en'}">
                                    <c:out value="${exam.name_ukr}"/>
                                </c:if>
                                <c:if test="${sessionScope.language == 'en'}">
                                    <c:out value="${exam.name}"/>
                                </c:if>
                            </c:forEach>
                        </label>
                        <select class="custom-select" multiple name="subject_2" id="subject_2">
                            <c:forEach var="subject" items="${requestScope.subjects}">
                                <c:if test="${sessionScope.language != 'en'}">
                                    <option value="${subject.name_ukr}">${subject.name_ukr}</option>
                                </c:if>
                                <c:if test="${sessionScope.language == 'en'}">
                                    <option value="${subject.name}">${subject.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-4 mb-2">
                        <label for="subject_3"><fmt:message key="edit_specialty_subject_choose_subject3"
                                                            bundle="${link}"/>
                            <br/>
                            <c:forEach var="exams" items="${requestScope.exam3}">
                                <c:if test="${sessionScope.language != 'en'}">
                                    <c:out value="${exams.name_ukr}"/>
                                </c:if>
                                <c:if test="${sessionScope.language == 'en'}">
                                    <c:out value="${exams.name}"/>
                                </c:if>
                            </c:forEach>
                        </label>
                        <select class="custom-select" multiple name="subject_3" id="subject_3">
                            <c:forEach var="subject" items="${requestScope.subjects}">
                                <c:if test="${sessionScope.language != 'en'}">
                                    <option value="${subject.name_ukr}">${subject.name_ukr}</option>
                                </c:if>
                                <c:if test="${sessionScope.language == 'en'}">
                                    <option value="${subject.name}">${subject.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <label for="id"></label>
                <input type="text" id="id" name="id" value="${requestScope.specialty.id}" hidden>
                <input type="hidden" name="submitted" value="true">

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="editSpecialty"
                       value="<fmt:message key="edit_specialty_edit" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>

