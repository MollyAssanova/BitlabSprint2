<%@ page import="kz.bitlab.techorda.db.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    User currentUser = (User) session.getAttribute("CURRENT_USER");

%>

<div class="container">

    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(35,67,105,0.85);">

        <div class="container-fluid">

            <a class="navbar-brand" href="/">BITLAB NEWS</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"

                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"

                    aria-expanded="false" aria-label="Toggle navigation">

                <span class="navbar-toggler-icon"></span>

            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">



                    <%

                        if(currentUser!=null){

                    %>

                    <li class="nav-item dropdown">

                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"

                           data-bs-toggle="dropdown" aria-expanded="false">

                            <%=currentUser.getFullName()%>

                        </a>

                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <li><a class="dropdown-item" href="/profile">Profile</a></li>

                            <li><a class="dropdown-item" href="/settings">Settings</a></li>

                            <li><hr class="dropdown-divider"></li>

                            <li><a class="dropdown-item" href="/logout">Logout</a></li>




                        </ul>

                    </li>

                    <%

                    }else{

                    %>



                    <li class="nav-item">

                        <a class="nav-link" href="/login">Login</a>

                    </li>
                    <li class="nav-item">

                        <a class="nav-link" href="/register">Register</a>

                    </li>


                </ul>
                <%

                    }

                %>

                <form class="form-inline my-2 my-lg-0">
                    <div style="display: flex">
                        <div> <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"></div>
                        <div><button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button></div>
                    </div>
                </form>

            </div>

        </div>

    </nav>

</div>


