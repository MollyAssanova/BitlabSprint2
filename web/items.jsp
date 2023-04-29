<%@ page import="kz.bitlab.techorda.db.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>
</head>
<style>
    .card{
        margin-left: 25px;
    }
</style>
<body>
    <center><h1>Welcome to Bitlab Shop</h1></center>
    <center>Electronic devices with high quality and service</center>
    <br><br>
    <center>
        <div style="display: flex; flex-wrap: nowrap; overflow-x: auto;"  >


                    <%
                        ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("veshy");
                        if(items!=null){
                            for(Item item : items){
                    %>
                        <div class="card " style="width: 18rem;">
                            <center>
                            <div class="card-body ">
                                <h5 class="card-title"><b><%=item.getName()%></b></h5>
                                <p class="card-text"><%=item.getDescription()%></p>
                                <p class="card-text" style="color: green"><%=item.getPrice()%>$</p>
                                <a href="#" class="btn btn-success">Buy Now</a>
                            </div>

                            </center>
                        </div>
                    <%
                            }
                        }
                    %>
            </div>
    </center>
</body>
</html>
