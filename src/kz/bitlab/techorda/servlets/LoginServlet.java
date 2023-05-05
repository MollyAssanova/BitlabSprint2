package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.User;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/login?emailerror";

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        User user = DBConnection.getUser(email);

        if(user!=null){

            redirect = "/login?passworderror";

            if(user.getPassword().equals(password)){

                redirect = "/profile";

                request.getSession().setAttribute("CURRENT_USER", user);

            }

        }

        response.sendRedirect(redirect);

    }
}
