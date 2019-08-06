<html>
<header>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page isELIgnored="false" %>

    <c:set var="language"
           value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
           scope="session"/>
    <fmt:setLocale value="${language}"/>
    <fmt:setBundle var="link" basename="lang" scope="session"/>
</header>

<body class="d-flex flex-column">
<!-- Header -->
<nav class="navbar navbar-expand-lg sticky-top navbar-default">

    <div class="collapse navbar-collapse ml-4">
        <ul class="navbar-nav">

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/campaign/login"><fmt:message key="header_log_in"
                                                                                         bundle="${link}"/></a>
                <a href="${pageContext.request.contextPath}/campaign/registration"><fmt:message
                        key="header_registration" bundle="${link}"/></a>
            </li>
        </ul>
    </div>
    <form class="form-inline">
        <label for="language"></label>
        <select class="custom-select my-1 mr-sm-6" id="language" name="language" onchange="submit()">
            <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message key="header_lang_ukr"
                                                                                  bundle="${link}"/></option>
            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="header_lang_eng"
                                                                                  bundle="${link}"/></option>
        </select>
    </form>
</nav>

</body>
</html>