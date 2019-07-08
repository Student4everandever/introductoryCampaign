<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="link" basename="lang" scope="session"/>

<html>
<head>
    <title>Admin edit university page</title>
</head>
<body>
<a href="/campaign/admin/admin_base">Back</a>
<br/>

<h2><fmt:message key="edit_university_enter_new_data" bundle="${link}"/></h2>

<h5 style="color: limegreen"><c:out value="${message}"/></h5>
<h5 style="color: red"><c:out value="${error}"/></h5>

<div class="container" style="vertical-align: auto">


    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/admin/edit_university" method="post"
                  class="needs-validation">

                <div class="mb-3">
                    <label for="name"><fmt:message key="edit_university_university_name" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name" id="name"
                           placeholder="${university.name}" value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="name_ukr"><fmt:message key="edit_university_university_name_ukr"
                                                        bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name_ukr" id="name_ukr"
                           placeholder="${university.name_ukr}" value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name in ukrainian.
                    </div>
                </div>

                <input type="text" id="id" name="id" value="${university.id}" hidden>
                <input type="hidden" name="submitted" value="true">


                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="editUniversity"
                       value="<fmt:message key="edit_university_edit" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>


<%@ include file="../views/footer.jsp" %>
</body>
</html>

