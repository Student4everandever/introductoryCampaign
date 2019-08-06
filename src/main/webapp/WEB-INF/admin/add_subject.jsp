<%@ include file="../views/header_logout.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin page to add subject</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/campaign/admin/show_subjects"><fmt:message key="add_subject_back"
                                                                                       bundle="${link}"/></a>
<br/>

<h2><fmt:message key="add_subject_enter_data" bundle="${link}"/></h2>

<h5 style="color: limegreen"><c:out value="${requestScope.message}"/></h5>
<h5 style="color: red"><c:out value="${requestScope.error}"/></h5>

<div class="container" style="vertical-align: auto">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/admin/add_subject" method="post"
                  class="needs-validation">

                <div class="mb-3">
                    <label for="name"><fmt:message key="add_subject_subject_name" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name" id="name"
                           placeholder="<fmt:message key="add_subject_enter_subject_name" bundle="${link}"/>"
                           value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="name_ukr"><fmt:message key="add_subject_subject_name_ukr" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="name_ukr" id="name_ukr"
                           placeholder="<fmt:message key="add_subject_enter_subject_name_ukr" bundle="${link}"/>"
                           value="" required>
                    <div class="invalid-feedback">
                        Please enter valid name in ukrainian.
                    </div>
                </div>

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="addSubject"
                       value="<fmt:message key="add_subject_add_subject" bundle="${link}"/>">
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="../views/footer.jsp" %>

</body>
</html>
