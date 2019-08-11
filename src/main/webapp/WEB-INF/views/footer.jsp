<html>
<header>

    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page isELIgnored="false" %>

    <fmt:setLocale value="${applicationScope.language}"/>
    <fmt:setBundle var="link" basename="lang" scope="session"/>
</header>

<body>
<div class="my_footer" style="position: fixed;
    left: 0; bottom: 0;
    padding: 20px;
    background: navy;
    color: white;
    width: 100%;">
<footer>
    <div class="container">
        <span style="align-content: center"><fmt:message key="footer_rights" bundle="${link}"/></span>
    </div>
</footer>
</div>

</body>
</html>
