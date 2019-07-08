<%@ include file="./WEB-INF/views/header_login.jsp"%>
<h2><fmt:message key="registration_page" bundle="${link}"/></h2>
<br/>

<div class="container" style="vertical-align: auto">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">

            <form action="${pageContext.request.contextPath}/campaign/registration" method="post" class="needs-validation">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="name"><fmt:message key="registration_first_name" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="<fmt:message key="registration_enter_first_name" bundle="${link}"/>" value="" required>
                        <div class="invalid-feedback">
                            Please enter valid first name.
                        </div>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="last_name"><fmt:message key="registration_last_name" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="last_name" id="last_name" placeholder="<fmt:message key="registration_enter_last_name" bundle="${link}"/>" value="" required>
                        <div class="invalid-feedback">
                            Please enter valid last name.
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="name_ukr"><fmt:message key="registration_first_name_ukr" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="name_ukr" id="name_ukr" placeholder="<fmt:message key="registration_enter_first_name_ukr" bundle="${link}"/>" value="" required>
                        <div class="invalid-feedback">
                            Please enter valid first name in ukrainian.
                        </div>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="last_name_ukr"><fmt:message key="registration_last_name_ukr" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="last_name_ukr" id="last_name_ukr" placeholder="<fmt:message key="registration_enter_last_name_ukr" bundle="${link}"/>" value="" required>
                        <div class="invalid-feedback">
                            Please enter valid last name in ukrainian.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email"><fmt:message key="registration_email" bundle="${link}"/></label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="<fmt:message key="registration_email_example" bundle="${link}"/>" required>
                    <div class="invalid-feedback">
                        Email is invalid.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="login"><fmt:message key="registration_login" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="login" id="login" placeholder="<fmt:message key="registration_choose_login" bundle="${link}"/>" required>
                    <div class="invalid-feedback">
                        Login is taken.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="password"><fmt:message key="registration_password" bundle="${link}"/></label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="<fmt:message key="registration_choose_password" bundle="${link}"/>" required>
                    <div class="invalid-feedback">
                        Password is invalid.
                    </div>
                </div>

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="submitRegistration" value="<fmt:message key="registration_registration" bundle="${link}"/>">
            </form>
        </div>
    </div>

</div>

<jsp:include page="./WEB-INF/views/footer.jsp"/>