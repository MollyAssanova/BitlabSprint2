<%@ page import="kz.bitlab.techorda.db.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<head>

    <%@include file="head.jsp"%>

</head>

<body>

    <%@include file="header.jsp"%>

    <br>
    <div class="container">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="https://media4.s-nbcnews.com/j/newscms/2019_01/2705191/nbc-social-default_b6fa4fef0d31ca7e8bc7ff6d117ca9f4.nbcnews-fp-1200-630.png"  style="width:100%;">
                </div>

                <div class="item">
                    <img src="https://newsinitiative.withgoogle.com/info/assets/static/images/news-youtube/hero-image-a0d28e8991ec7398d914388a83d78d56.jpg" style="width:100%;">
                </div>

                <div class="item">
                    <img src="https://resize.indiatvnews.com/en/centered/newbucket/1200_675/2021/03/breaking-1614993326.jpg"  style="width:100%;">
                </div>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>


    <br>

    <center>


        <h1>Все новости</h1>
    </center>

    <div class="container" style="min-height: 500px;">

        <div class="row mt-3">

            <div class="col-12">

                <%

                    ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");

                    if(news!=null){

                        for(News news1 : news){

                %>

                <div class="row mt-3">

                    <div class="col-11 mx-auto p-3" style="background-color: lightgrey;">

                        <h2>
                            <%=news1.getTitle()%>
                        </h2>





                        <p class="mt-2"><%=news1.getContent()%></p>

                        <a href="/readnews?id=<%=news1.getId()%>" class="text-bg-light text-decoration-none" >

                            Подробнее

                        </a>


                    </div>

                </div>

                <%

                        }

                    }

                %>

            </div>

        </div>

    </div>

    <%@include file="footer.jsp"%>

</body>

<%@include file="foot.jsp"%>

</html>

