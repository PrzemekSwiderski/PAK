<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="main-exploration">
    <main class="col-9">
        <div class="new-post">
            <img class="user-image" src='<c:url value="${user.getImageNameAddress()}"/>' alt="user image">
            <form action='<c:url value="/new-post"/>' method="post" enctype="multipart/form-data">
                <textarea class="col-10" name="content" rows="3"
                          placeholder="Powiedz, co ci chodzi po głowie ${user.getUsername()}" required></textarea>
                <div class="form-group mt-3">
                    <label>Możesz dodać zdjęcie</label>
                    <input type="file" class="form-control" name="image" id="post-image-address"
                           accept="image/png, image/jpeg">
                </div>
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
                <c:if test="${post.getPostImageAddress() != null}">
                <img class="post-image" src='<c:url value="${post.getPostImageAddress()}"/>'
                     alt="image of post id ${post.getPostId()}">
                </c:if>
                <button class="reply-button" type="button" id="reply-button-${post.getPostId()}">Odpowiedz</button>
                <div class="new-comment" id="reply-${post.getPostId()}" hidden>
                    <img class="user-image" src='<c:url value="${user.getImageNameAddress()}"/>' alt="user image">
                    <form action='<c:url value="/comment"/>' method="post">
                        <input type="number" name="postId" value="${post.getPostId()}" hidden>
                        <textarea class="col-10" name="content" rows="3"
                                  placeholder="Powiedz, co o tym myślisz ${user.getUsername()}"></textarea>
                        <button type="submit">Wyślij</button>
                    </form>
                </div>
                <c:forEach items="${post.getComments()}" var="comment">
                    <div class="comment">
                        <img class="user-image" src='<c:url value="${comment.getUserImageAddress()}"/>'
                             alt="${comment.getUsername()} image">
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

<script src='<c:url value="/resources/js/wall.js"/>'></script>
</body>
</html>
