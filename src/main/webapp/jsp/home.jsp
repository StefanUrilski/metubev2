<%@ page import="metube.domain.models.view.UserTubesViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% List<UserTubesViewModel> allTubes = (List<UserTubesViewModel>) request.getSession().getAttribute("allTubes"); %>
<div class="container-fluid">
    <header>
        <c:import url="templates/navbar.jsp"/>
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="h2 text-info">Welcome, ${username}</h4>
        </div>
        <hr class="my-4">
        <div class="text-center mt-3">
            <h4 class="h4 text-info">All Tubes</h4>
        </div>
        <div class="container-fluid">
            <div class="row mb-4 d-flex justify-content-around">
                <% for (UserTubesViewModel tube : allTubes) { %>
                <div class="col-md-3 d-flex flex-column text-center">
                    <a href="/tube/details/<%= tube.getYoutubeId() %>">
                        <img src="https://cdn1.iconfinder.com/data/icons/social-media-set-2/96/Youtube-512.png"
                             alt="<%= tube.getTitle() %>" width="250" height="250" style="padding-left: 15px">
                    </a>
                    <h5 class="text-center"><%= tube.getTitle() %></h5>
                    <h5 class="text-center"><%= tube.getAuthor() %></h5>
                </div>
                <% } %>
                <hr class="my-3"/>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
