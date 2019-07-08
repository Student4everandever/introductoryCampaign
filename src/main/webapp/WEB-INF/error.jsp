<%@ include file="./views/header_login.jsp"%>
<%@ page isErrorPage="true"  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Error Page</h2>
<br/>
<h3>
    <i>Error <%= exception %></i><br/>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

    <c:if test="${exception != null}">
        <h4>${exception}</h4>
        <c:forEach var="stackTraceElem" items="${exception.stackTrace}">
            <c:out value="${stackTraceElem}"/><br/>
        </c:forEach>
    </c:if>
</h3>
<jsp:include page="./views/footer.jsp"/>