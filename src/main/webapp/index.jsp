<html>
<head>
    <title>Main</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<%@ include file="./WEB-INF/views/header_login.jsp" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="link" basename="lang" scope="session"/>

<body>
<header class="main">
    <div class=" text-center col-lg-11 my-5  ml-5">
        <h2><fmt:message key="index_welcome" bundle="${link}"/></h2>
        <br/>
    </div>
</header>

<jsp:include page="./WEB-INF/views/footer.jsp"/>

</body>
</html>
