<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle var="link" basename="lang" scope="session" />

<html>
<head>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>


</head>
<body>
<div class="myfooter" style="position: fixed;
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
