<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="main-page">
    <main class="col-9 profile row">
        <div class="col-6">
            <p>Cześć, ${user.getUsername()}</p>
            <span>Adres emial: </span>
            <p>${user.getEmail()}</p>
            <span>Konto zostało utworzone:</span>
            <p>${user.getRegisterDate()}</p>
            <form class="is-readonly" id="edit-portfolio" enctype="multipart/form-data" action='<c:url value="/my-profile"/>' method="post">
                <div class="form-group">
                    <label for="profile-password">Twój pseudonim</label>
                    <input type="text" class="form-control is-disabled" name="username" id="profile-username" placeholder="${user.getUsername()}" disabled>
                </div>
                <div class="form-group">
                    <label for="profile-password">Hasło</label>
                    <input type="password" class="form-control is-disabled" name="password" id="profile-password" placeholder="**********" disabled>
                </div>
                <div class="form-group new-image" hidden>
                    <br>
                    <label for="profile-image" class="is-hidden" >Wgraj nowe zdjecie</label>
                    <input type="file" class="form-control is-hidden" name="image" id="profile-image" accept="image/png, image/jpeg">
                </div>
                <button type="button" class="btn btn-default btn-edit js-edit">Edit</button>
                <button type="button" class="btn btn-default btn-save js-save" id="js-save">Save</button>
                <button type="button" class="btn btn-default btn-save js-cancel">Cancel</button>
            </form>
        </div>
        <div class="col-6">
            <img class="profile-iamge" src='<c:url value="${user.getImageNameAddress()}" />' alt="your profile photo">
        </div>
    </main>
    <%@include file="../dynamics/menu.jspf" %>
</div>

<script type="text/javascript" src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src='<c:url value="/resources/js/profile.js"/>'></script>
</body>
</html>
