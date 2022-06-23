<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="dynamics/css.jspf" %>
<body>
<header class="main-banner">
    <img src='<c:url value="/resources/images/pak-baner.png"/>' alt="page banner">
</header>

<div class="main-page">
    <main class="col-9">
        <div class="post">
            <span>Imię i nazwisko</span>
            <span>Data</span>
            <p>"Lorem ipsum dolor sit amet,
                consectetur adipiscing elit, sed do eiusmod
                tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation
                ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in
                voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum."
            </p>
        </div>
        <div class="post">
            <span>Imię i nazwisko</span>
            <span>Data</span>
            <p>"Lorem ipsum dolor sit amet,
                consectetur adipiscing elit, sed do eiusmod
                tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation
                ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in
                voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum."
            </p>
        </div>
    </main>
    <nav class="col-3">
        <div class="navigator">
            <ul>
                <li>Tablica</li>
                <li>Eksploruj</li>
                <li>Profil</li>
                <li>Subskypcje</li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>
