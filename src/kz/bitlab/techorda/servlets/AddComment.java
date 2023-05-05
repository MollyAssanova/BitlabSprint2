package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.Comment;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.News;
import kz.bitlab.techorda.db.User;

import java.io.IOException;


@WebServlet(value = "/addcomment")
public class AddComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/login";

        request.setCharacterEncoding("utf8");

        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");

        if(currentUser!=null) {

            String commentText = request.getParameter("comment");

            Long blogId = Long.parseLong(request.getParameter("blog_id"));

            News news = DBConnection.getNews1(blogId);

            if(news!=null){

                Comment comment = new Comment();

                comment.setComment(commentText);

                comment.setUser(currentUser);

                comment.setNews(news);

                if(DBConnection.addComment(comment)){

                    redirect = "/readnews?id="+blogId;

                }

            }

        }

        response.sendRedirect(redirect);


    }
}
