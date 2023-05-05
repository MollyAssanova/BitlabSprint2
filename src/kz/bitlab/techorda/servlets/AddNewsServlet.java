package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.News;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/addnews")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/addnews.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");


            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Integer category_id= Integer.valueOf(request.getParameter("category_id"));

            News news = new News();

            news.setTitle(title);

            news.setContent(content);
            news.setCategory_id(category_id);
            DBConnection.addNews(news);

        response.sendRedirect("/home.html");

    }

}
