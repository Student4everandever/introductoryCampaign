<html>
<header>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page isELIgnored="false" %>

    <fmt:setLocale value="${applicationScope.language}"/>

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

    <div>
    <a class="dropdown-item" href="?locale=en"><fmt:message key="header_lang_eng" bundle="${link}"/></a>
    <a class="dropdown-item" href="?locale=uk"><fmt:message key="header_lang_ukr" bundle="${link}"/></a>
    </div>

</nav>

</body>
</html>