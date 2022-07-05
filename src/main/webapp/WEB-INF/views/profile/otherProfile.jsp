<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="main-page">
    <main class="col-9 profile row">
        <div class="col-6">
            <p>${user.getUsername()}</p>
            <form method="post" action='<c:url value="/subscriptions"/>'>
                <input type="number" name="id" value="${user.getId()}" class="visually-hidden"/>
                <button type="submit">
                    <c:choose>
                        <c:when test='${!user.getIsSubscribed()}'>
                            Obserwuj
                        </c:when>
                        <c:otherwise>
                            Przestań obserwować
                        </c:otherwise>
                    </c:choose>
                </button>
            </form>
        </div>
        <div class="col-6">
            <img class="profile-iamge" src='<c:url value="${user.getImageNameAddress()}" />' alt="your profile photo">
        </div>
    </main>
    <%@include file="../dynamics/menu.jspf" %>
</div>

</body>
</html>
