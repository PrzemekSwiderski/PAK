<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="main-exploration">
    <main class="col-9">
        <c:forEach items="${allPosts}" var="post">
            <div class="post">
                <span class="post">${post.getUser().getUsername()}</span>
                <span class="create-date">${post.getCreateDate()}</span>
                <p class="post-content">${post.getContent()}</p>
            </div>
        </c:forEach>
    </main>
    <%@include file="../dynamics/menu.jspf" %>
</div>
</body>

</html>
