<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="main-page">
    <main class="col-9 profile row">
        <c:forEach items="${subscribers}" var="subscriber">
            <div>
                <img class="user-image" src='<c:url value="${subscriber.getImageNameAddress()}"/>' alt="user image">
                <div class="user-name">
                    <a href='<c:url value="/profile/${subscriber.getId()}"/>'>
                        ${subscriber.getUsername()}
                    </a>
                </div>
            </div>
        </c:forEach>
    </main>
    <%@include file="../dynamics/menu.jspf" %>
</div>

</body>
</html>
