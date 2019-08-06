<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Applicant choose subjects page</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/campaign/applicant/applicant_base"><fmt:message
        key="choose_subjects_to_main" bundle="${link}"/></a>
<br/>


<h1><fmt:message key="choose_subjects_choose" bundle="${link}"/></h1>
<br/>
<h5 style="color: red"><c:out value="${requestScope.error}"/></h5>
<h5 style="color: limegreen"><c:out value="${requestScope.message}"/></h5>

<div class="container" style="vertical-align: auto">

    <div class="row ">
        <div class="col-md-12 mb-3 mt-6">
            <c:if test="${language != 'en'}">
                <h3>${requestScope.university.name_ukr}</h3>
            </c:if>
            <c:if test="${language == 'en'}">
                <h3>${requestScope.university.name}</h3>
            </c:if>
        </div>
        <div class="col-md-12 mb-3 mt-6">
            <c:if test="${language != 'en'}">
                <h4>${requestScope.specialty.title_ukr}</h4>
            </c:if>
            <c:if test="${language == 'en'}">
                <h4>${requestScope.specialty.title}</h4>
            </c:if>
        </div>
    </div>
    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/applicant/choose_subjects" method="post"
                  class="needs-validation">

                <div class="row">
                    <div class="col-md-4 mb-2">
                        <label for="subject_1"><fmt:message key="choose_subjects_subject_label_subject1"
                                                            bundle="${link}"/></label>
                        <input type="text" class="form-control" name="subject_1" id="subject_1"
                               value="Ukrainian language and literature" hidden>
                        <p><fmt:message key="choose_subjects_subject_subject1" bundle="${link}"/></p>
                    </div>

                    <div class="col-md-4 mb-2">
                        <label for="subject_2"><fmt:message key="choose_subjects_subject_choose_subject2"
                                                            bundle="${link}"/></label>
                        <select class="custom-select" name="subject_2" id="subject_2" required>
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
                        <label for="subject_3"><fmt:message key="choose_subjects_subject_choose_subject3"
                                                            bundle="${link}"/></label>
                        <select class="custom-select" name="subject_3" id="subject_3" required>
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
                <hr class="mb-4">

                <label for="university"></label>
                <input type="text" id="university" name="university" value="${requestScope.university.name}" hidden>
                <label for="specialty"></label>
                <input type="text" id="specialty" name="specialty" value="${requestScope.specialty.title}" hidden>

                <input type="submit" class="form-control btn-submit" name="chooseSpecialty"
                       value="<fmt:message key="choose_subjects_apply" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
