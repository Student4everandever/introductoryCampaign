<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Applicant choose specialty page</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/campaign/applicant/applicant_base"><fmt:message
        key="choose_specialty_to_main" bundle="${link}"/></a>
<br/>


<h1><fmt:message key="choose_specialty_choose" bundle="${link}"/></h1>
<br/>
<h5 style="color: red"><c:out value="${requestScope.error}"/></h5>
<h5 style="color: limegreen"><c:out value="${requestScope.message}"/></h5>

<div class="container" style="vertical-align: auto">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/applicant/choose_specialty" method="post"
                  class="needs-validation">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="university"><fmt:message key="choose_specialty_choose_university"
                                                             bundle="${link}"/></label>
                        <select class="custom-select" name="university" id="university" required>
                            <c:forEach var="university" items="${requestScope.universities}">
                                <c:if test="${language != 'en'}">
                                    <option value="${university.name_ukr}">${university.name_ukr}</option>
                                </c:if>
                                <c:if test="${language == 'en'}">
                                    <option value="${university.name}">${university.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test="${requestScope.specialties != null}">
                        <div class="col-md-6 mb-3">
                            <label for="specialty"><fmt:message key="choose_specialty_choose_specialty"
                                                                bundle="${link}"/></label>
                            <select class="custom-select" name="specialty" id="specialty" required>
                                <c:forEach var="specialty" items="${requestScope.specialties}">
                                    <c:if test="${language != 'en'}">
                                        <option value="${specialty.title_ukr}">${specialty.title_ukr}</option>
                                    </c:if>
                                    <c:if test="${language == 'en'}">
                                        <option value="${specialty.title}">${specialty.title}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                </div>
                <c:if test="${requestScope.subject2 != null}">
                    <div class="row">
                        <div class="col-md-4 mb-2">
                            <label for="subject_1"><fmt:message key="edit_specialty_subject_label_subject1"
                                                                bundle="${link}"/></label>
                            <input type="text" class="form-control" name="subject_1" id="subject_1" value="" hidden>
                            <p><fmt:message key="choose_specialty_subject_subject1" bundle="${link}"/></p>
                        </div>

                        <div class="col-md-4 mb-2">
                            <label for="subject_2"><fmt:message key="edit_specialty_subject_choose_subject2"
                                                                bundle="${link}"/></label>
                            <select class="custom-select" multiple name="subject_2" id="subject_2" required>
                                <c:forEach var="subject" items="${requestScope.subjects2}">
                                    <c:if test="${language != 'en'}">
                                        <option value="${subject.name_ukr}">${subject.name_ukr}</option>
                                    </c:if>
                                    <c:if test="${language == 'en'}">
                                        <option value="${subject.name}">${subject.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-4 mb-2">
                            <label for="subject_3"><fmt:message key="edit_specialty_subject_choose_subject3"
                                                                bundle="${link}"/></label>
                            <select class="custom-select" multiple name="subject_3" id="subject_3" required>
                                <c:forEach var="subject" items="${requestScope.subjects3}">
                                    <c:if test="${language != 'en'}">
                                        <option value="${subject.name_ukr}">${subject.name_ukr}</option>
                                    </c:if>
                                    <c:if test="${language == 'en'}">
                                        <option value="${subject.name}">${subject.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:if>

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="chooseSpecialty"
                       value="<fmt:message key="choose_specialty_apply" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
