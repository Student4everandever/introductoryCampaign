<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="link" basename="lang" scope="session"/>
<html>
<head>
    <title>Add specialty page</title>
</head>
<body>
<a href="/campaign/admin/show_specialties">back</a>
<br/>

<h2><fmt:message key="add_specialty_enter_data" bundle="${link}"/></h2>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<div class="container" style="vertical-align: auto">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/admin/add_specialty" method="post"
                  class="needs-validation">

                    <div class="mb-3">
                        <label for="university"><fmt:message key="add_specialty_university_name"
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

                <div class="mb-3">
                    <label for="title"><fmt:message key="add_specialty_specialty_title" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="title" id="title"
                           placeholder="<fmt:message key="add_specialty_enter_specialty_title" bundle="${link}"/>"
                           value="" required>
                    <div class="invalid-feedback">
                        Please enter valid title.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="title_ukr"><fmt:message key="add_specialty_specialty_title_ukr"
                                                        bundle="${link}"/></label>
                    <input type="text" class="form-control" name="title_ukr" id="title_ukr"
                           placeholder="<fmt:message key="add_specialty_enter_specialty_title_ukr" bundle="${link}"/>"
                           value="" required>
                    <div class="invalid-feedback">
                        Please enter valid title in ukrainian.
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-2">
                        <label for="subject_1"><fmt:message key="add_specialty_choose_subject1"
                                                            bundle="${link}"/></label>
                        <input type="text" class="form-control" name="subject_1" id="subject_1" value="<fmt:message key="add_specialty_subject1" bundle="${link}"/>">
                    </div>

                    <div class="col-md-4 mb-2">
                        <br/>
                        <label for="subject_2"><fmt:message key="add_specialty_choose_subject2"
                                                            bundle="${link}"/></label>
                        <select class="custom-select" multiple name="subject_2" id="subject_2">
                            <c:forEach var="subject" items="${requestScope.subjects}">
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
                        <br/>
                        <label for="subject_3"><fmt:message key="add_specialty_choose_subject3"
                                                            bundle="${link}"/></label>
                        <select class="custom-select" multiple name="subject_3" id="subject_3">
                            <c:forEach var="subject" items="${requestScope.subjects}">
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
                <input type="submit" class="form-control btn-submit" name="addConference"
                       value="<fmt:message key="add_specialty_add_specialty" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>


<%@ include file="../views/footer.jsp" %>
</body>
</html>
