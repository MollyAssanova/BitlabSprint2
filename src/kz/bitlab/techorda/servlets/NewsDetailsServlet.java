package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.Comment;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/readnews")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        News news = DBConnection.getNews1(id);
        request.setAttribute("news", news);
        if (news != null) {
            ArrayList<Comment> comments = DBConnection.getAllComments((long) news.getId()); // изменен параметр метода
            request.setAttribute("comments", comments);
        }
        request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
    }
}
