<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin edit university page</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/campaign/admin/show_universities?page=${requestScope.page}"><fmt:message key="edit_university_back" bundle="${link}"/></a>
<br/>

<h2><fmt:message key="edit_university_enter_new_data" bundle="${link}"/></h2>

<h5 style="color: limegreen"><c:out value="${requestScope.message}"/></h5>
<h5 style="color: red"><c:out value="${requestScope.error}"/></h5>

<div class="container" style="vertical-align: auto">


    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/admin/edit_university" method="post"
                  class="needs-validation">

                <div class="mb-3">
                    <label for="name"><fmt:message key="edit_university_university_name" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name" id="name"
                           placeholder="${requestScope.university.name}" value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="name_ukr"><fmt:message key="edit_university_university_name_ukr"
                                                       bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name_ukr" id="name_ukr"
                           placeholder="${requestScope.university.name_ukr}" value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name in ukrainian.
                    </div>
                </div>

                <label for="id"></label>
                <input type="text" id="id" name="id" value="${requestScope.university.id}" hidden>
                <label for="page"></label>
                <input type="text" id="page" name="page" value="${requestScope.page}" hidden>
                <input type="hidden" name="submitted" value="true">

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="editUniversityName"
                       value="<fmt:message key="edit_university_edit_name" bundle="${link}"/>">
            </form>

        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>

