<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamics/css.jspf" %>
<body>

<%@include file="../dynamics/banner.jspf" %>

<div class="auth-page">
    <div class="login-controller">
        <span id="switch-register">Zarejestruj się</span>
        <span id="switch-auth">Zaloguj się</span>
    </div>
    <div class="reg-auth-forms">
        <form method="post" action='<c:url value="/auth"/>' class="auth" id="form-auth">
            <div class="form-group mt-3">
                <label for="log-mail">Email</label>
                <input type="text" name="email" class="form-control" id="log-mail" placeholder="adres email" required>
            </div>
            <div class="form-group mt-3">
                <label for="log-password">Hasło</label>
                <input type="password" class="form-control" name="password" id="log-password" placeholder="hasło"
                       required>
            </div>
            <br>
            <div class="d-flex justify-content-center">
                <label>
                    <input name="remember-me" type="checkbox">
                    Zapamiętaj mnie
                </label>
            </div>
            <br>
            <div class="text-center">
                <button type="submit">Zaloguj</button>
            </div>
        </form>
        <form method="post" action='<c:url value="/register"/>' class="registration" id="form-register"  enctype="multipart/form-data" hidden="hidden" >
            <div class="form-group mt-3">
                <label for="log-mail">Email</label>
                <input type="text" name="email" class="form-control" id="reg-mail" placeholder="adres email" required>
            </div>
            <div class="form-group mt-3">
                <label for="log-password">Hasło</label>
                <input type="password" class="form-control" name="password" id="reg-password" placeholder="hasło"
                       required>
            </div>
            <div class="form-group mt-3">
                <label for="log-password">Nick</label>
                <input type="text" class="form-control" name="username" id="reg-username" placeholder="Twój nick"
                       required>
            </div>
            <div class="form-group mt-3">
                <label for="log-password">Wgraj zdjęcie</label>
                <input type="file" class="form-control" name="image" id="reg-image-address"
                       accept="image/png, image/jpeg"
                       required>
            </div>
            <br>
            <div class="text-center">
                <button type="submit">zarejestruj się</button>
            </div>
        </form>
    </div>
</div>


<script src='<c:url value="/resources/js/auth.js"/>'></script>


</body>
</html>