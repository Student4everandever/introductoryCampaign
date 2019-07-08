<%@ include file="./WEB-INF/views/header_login.jsp"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle var="link" basename="lang" scope="session" />


<h2><fmt:message key="login_page" bundle="${link}"/></h2>
<br/>


    <div class=" text-center col-lg-3 my-5  ml-5">

        <form action="${pageContext.request.contextPath}/campaign/login" method="post" class="form-signin">
            <div class="form-group">
                <input type="text" class="form-control" name="login" id="login" placeholder="<fmt:message key="login_login" bundle="${link}"/>" required autofocus>
            </div>

            <div class="form-group">
                <input type="password" class="form-control" name="pass" id="pass" placeholder="<fmt:message key="login_password" bundle="${link}"/>" required autofocus>
            </div>

            <div class="form-group">
                <input type="submit" class="form-control btn-submit" id="submitLogin" value="<fmt:message key="login_log_in" bundle="${link}"/>">
            </div>

            <a href="${pageContext.request.contextPath}/campaign/registration"><fmt:message key="login_registration" bundle="${link}"/></a><br>
        </form>
    </div>




<jsp:include page="./WEB-INF/views/footer.jsp"/>
