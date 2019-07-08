<%@ include file="../views/header_logout.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant choose specialty page</title>
</head>
<body>
<a href="/campaign/applicant/applicant_base">Back</a>
<br/>


<h1><fmt:message key="choose_specialty_choose" bundle="${link}"/></h1>
<br/>
<h5 style="color: red"><c:out value="${error}"/></h5>
<h5 style="color: limegreen"><c:out value="${message}"/></h5>

<div class="container" style="vertical-align: auto">


    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/applicant/choose_specialty" method="post"
                  class="needs-validation">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="university"><fmt:message key="choose_specialty_choose_university" bundle="${link}"/></label>
                        <select class="custom-select" name="university" id="university" required>
                            <c:forEach var="speaker" items="${requestScope.speakers}">
                                <c:if test="${language != 'en'}">
                                    <option value="${speaker.lastName_ukr}" selected>${speaker.name_ukr} ${speaker.lastName_ukr}</option>
                                </c:if>
                                <c:if test="${language == 'en'}">
                                    <option value="${speaker.lastName}" selected>${speaker.name} ${speaker.lastName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="specialty"><fmt:message key="choose_specialty_choose_specialty" bundle="${link}"/></label>
                        <select class="custom-select" name="specialty" id="specialty" required>
                            <c:forEach var="speaker" items="${requestScope.speakers}">
                                <c:if test="${language != 'en'}">
                                    <option value="${speaker.lastName_ukr}" selected>${speaker.name_ukr} ${speaker.lastName_ukr}</option>
                                </c:if>
                                <c:if test="${language == 'en'}">
                                    <option value="${speaker.lastName}" selected>${speaker.name} ${speaker.lastName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <input type="text" id="id" name="id" value="${specialty.id}" hidden>
                <input type="hidden" name="submitted" value="true">


                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="chooseSpecialty"
                       value="<fmt:message key="choose_specialty_apply" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>


<%@ include file="../views/footer.jsp"%>
</body>
</html>
