<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle var="link" basename="lang" scope="session" />

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

</head>
<body class="d-flex flex-column">
<!-- Header -->
<nav class="navbar navbar-expand-lg sticky-top navbar-default">

    <div class="collapse navbar-collapse ml-4">
        <ul class="navbar-nav">

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/conference/home"><fmt:message key="header_home" bundle="${link}"/></a>
                <a href="${pageContext.request.contextPath}/conference/logout"><fmt:message key="header_log_out" bundle="${link}"/></a>
            </li>

        </ul>
    </div>
    <form class="form-inline">
        <select class="custom-select my-1 mr-sm-6" id="language" name="language" onchange="submit()">
            <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message key="header_lang_ukr" bundle="${link}"/></option>
            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="header_lang_eng" bundle="${link}"/></option>
        </select>
    </form>
</nav>
