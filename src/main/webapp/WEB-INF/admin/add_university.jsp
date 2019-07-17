<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle var="link" basename="lang" scope="session" />

<html>
<head>
    <title>Admin page to add university</title>
</head>
<body>
<a href="/campaign/admin/show_universities">back</a>
<br/>

<h2><fmt:message key="add_university_enter_data" bundle="${link}"/></h2>

<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<div class="container" style="vertical-align: auto">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/admin/add_university" method="post"
                  class="needs-validation">

                <div class="mb-3">
                    <label for="name"><fmt:message key="add_university_university_name" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="<fmt:message key="add_university_enter_university_name" bundle="${link}"/>"
                           value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="name_ukr"><fmt:message key="add_university_university_name_ukr" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name_ukr" id="name_ukr"
                           placeholder="<fmt:message key="add_university_enter_university_name_ukr" bundle="${link}"/>" value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name in ukrainian.
                    </div>
                </div>

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="addUniversity" value="<fmt:message key="add_university_add_university" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>


<%@ include file="../views/footer.jsp" %>
</body>
</html>
