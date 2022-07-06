<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="main-exploration">
    <main class="col-9">
        <div class="post">
            <img class="user-image" src='<c:url value="${user.getImageNameAddress()}"/>' alt="user image">
            <form action='<c:url value="/new-post"/>' method="post">
                <textarea class="col-10" name="content" rows="3"
                          placeholder="Powiedz, co ci chodzi po głowie ${user.getUsername()}"></textarea>
                <button type="submit">Wyślij</button>
            </form>
        </div>
        <c:forEach items="${posts}" var="post">
            <div class="post">
                <img class="user-image" src='<c:url value="${post.getUserImageAddress()}"/>'
                     alt="${post.getUsername()} image">
                <div class="user-name"><a href='<c:url value="/profile/${post.getUserId()}"/>'>
                        ${post.getUsername()}</a></div>
                <div class="create-date">${post.getCreateDate()}</div>
                <p class="post-content">${post.getContent()}</p>
                <c:forEach items="${post.getComments()}" var="comment">
                    <div class="post">
                        <img class="user-image" src='<c:url value="${comment.getUserImageAddress()}"/>'
                             alt=" ${comment.getUsername()} image">
                        <div class="user-name"><a href='<c:url value="/profile/${comment.getUserId()}"/>'>
                                ${comment.getUsername()}</a></div>
                        <div class="create-date">${comment.getCreateDate()}</div>
                        <p class="post-content">${comment.getContent()}</p>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </main>
    <%@include file="../dynamics/menu.jspf" %>
</div>
</body>

</html>
